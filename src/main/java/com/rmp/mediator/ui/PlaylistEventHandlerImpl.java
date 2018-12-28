package com.rmp.mediator.ui;

import com.rmp.dao.domain.state.StateModel;
import com.rmp.mediator.UIWatcherContainer;
import com.rmp.mediator.mediaPlayer.MediaPlaylistFactory;
import com.rmp.mediator.service.mediaFile.MediaFileService;
import com.rmp.mediator.service.playlist.PlaylistService;
import com.rmp.mediator.service.state.StateService;
import com.rmp.mediator.taskExecutor.AsyncTaskExecutor;
import com.rmp.vlcPlayer.VlcMediaPlayer;
import com.rmp.widget.eventHandler.PlaylistEventHandler;
import com.rmp.widget.readModels.UIMediaFileModel;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Provides the implementation of event handler from UI
 */
@Slf4j
public class PlaylistEventHandlerImpl implements PlaylistEventHandler {

    @Setter
    private VlcMediaPlayer mediaPlayer;
    private final AsyncTaskExecutor asyncTaskExecutor;
    private final UIWatcherContainer watcherContainer;
    private final PlaylistService playlistService;
    private final StateService stateService;
    private final MediaFileService mediaFileService;

    /**
     * Initialize new instance of {@link PlaylistEventHandlerImpl}
     */
    public PlaylistEventHandlerImpl(AsyncTaskExecutor asyncTaskExecutor, UIWatcherContainer watcherContainer) {
        this.watcherContainer = watcherContainer;
        this.asyncTaskExecutor = asyncTaskExecutor;
        this.playlistService = new PlaylistService();
        this.stateService = new StateService();
        this.mediaFileService = new MediaFileService();
    }

    /**
     * Handles the create playlist event
     *
     * @param title - the name of new playlist
     */
    @Override
    public void onPlaylistCreate(String title) {
        this.asyncTaskExecutor.executeTask(() -> {
            this.playlistService.createPlaylist(title);
            this.watcherContainer.getPlaylistDataWatcher().getPlaylistModelObserver().emit(this.playlistService.getAllPlaylists());
            this.onPlaylistSelected(this.playlistService.getByTitle(title).getId());
        });
    }

    /**
     * Handles the selecting of playlist from list
     *
     * @param id - the identifier of selected playlist
     */
    @Override
    public void onPlaylistSelected(int id) {
        this.asyncTaskExecutor.executeTask(() -> {
            StateModel currentState = this.stateService.updatePlaylistId(id);

            this.mediaPlayer.setMediaPlaylist(
                    MediaPlaylistFactory.create(this.stateService.getCurrentState(), this.mediaFileService)
            );

            this.watcherContainer.getPlaylistDataWatcher().getSelectedPlaylistObserver().emit(
                    this.playlistService.getById(currentState.getPlaylistId())
            );

            this.watcherContainer.getPlaylistDataWatcher().getReplaceMediaFilesObserver().emit(
                    this.mediaFileService.getByPlaylistId(currentState.getPlaylistId())
            );

            this.watcherContainer.getControlPanelDataWatcher().getIsPlayingObserver().emit(this.mediaPlayer.isPlaying());
        });
    }

    /**
     * Handles the selecting of files
     *
     * @param files - the files to be handled
     */
    @Override
    public void onFilesSelected(List<File> files) {
        this.asyncTaskExecutor.executeTask(() -> {
            List<String> filePaths = this.getFilePaths(files);
            StateModel currentState = this.stateService.getCurrentState();

            filePaths.forEach(filePath -> {
                UIMediaFileModel mediaFile = new UIMediaFileModel();
                mediaFile.setPath(filePath);
                mediaFile.setPlaylistId(currentState.getPlaylistId());

                mediaFile = this.mediaFileService.createMediaFile(mediaFile);
                this.watcherContainer.getPlaylistDataWatcher().getAddMediaFileObserver().emit(mediaFile);
            });

            this.mediaPlayer.setMediaPlaylist(
                    MediaPlaylistFactory.create(this.stateService.getCurrentState(), this.mediaFileService)
            );
        });
    }

    /**
     * Handles the deleting of media files
     *
     * @param mediaFileIds - the media files ids
     */
    @Override
    public void onMediaFilesDeleted(List<Integer> mediaFileIds) {
        this.asyncTaskExecutor.executeTask(() -> {
            StateModel currentState = this.stateService.getCurrentState();

            this.mediaFileService.deleteMediaFiles(mediaFileIds);
            this.watcherContainer.getPlaylistDataWatcher().getReplaceMediaFilesObserver().emit(
                    this.mediaFileService.getByPlaylistId(currentState.getPlaylistId())
            );

            this.mediaPlayer.setMediaPlaylist(
                    MediaPlaylistFactory.create(this.stateService.getCurrentState(), this.mediaFileService)
            );
        });
    }

    /**
     * Handles double click by media file item in playlist
     *
     * @param mediaFileId - the media file id
     */
    @Override
    public void onMediaItemDoubleClick(int mediaFileId) {
        this.asyncTaskExecutor.executeTask(() -> {
            UIMediaFileModel mediaFile = this.mediaFileService.getById(mediaFileId);
            this.mediaPlayer.play(mediaFile.getPath());
            this.updateAfterMediaFileChange();
        });
    }

    private synchronized List<String> getFilePaths(List<File> files) {
        if (files == null || files.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> result = new ArrayList<>();
        files.forEach(file -> {
            if (file.isFile()) {
                String fileExtension = FilenameUtils.getExtension(file.getName());

                if (fileExtension.equalsIgnoreCase("mp3") || fileExtension.equalsIgnoreCase("wav")) {
                    result.add(file.getAbsolutePath());
                }
            } else {
                try {
                    List<File> filesFromFolder = Files.list(file.toPath())
                            .map(Path::toFile)
                            .collect(Collectors.toList());
                    result.addAll(this.getFilePaths(filesFromFolder));
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        });

        return result;
    }

    private void updateAfterMediaFileChange() {
        List<UIMediaFileModel> mediaFileModels = this.mediaFileService.getByPlaylistId(
                this.playlistService.getById(this.stateService.getCurrentState().getPlaylistId()).getId()
        );

        if (mediaFileModels != null && !mediaFileModels.isEmpty()) {
            this.stateService.updatePlaylistFile(mediaFileModels.get(this.mediaPlayer.getSelectedMediaFileIndex()).getId());
        }

        this.watcherContainer.getControlPanelDataWatcher().getIsPlayingObserver().emit(this.mediaPlayer.isPlaying());
    }
}
