package com.rmp.mediator.ui;

import com.rmp.dao.domain.state.StateModel;
import com.rmp.mediator.service.mediaFile.MediaFileService;
import com.rmp.mediator.service.playlist.PlaylistService;
import com.rmp.mediator.service.state.StateService;
import com.rmp.mediator.taskExecutor.AsyncTaskExecutor;
import com.rmp.widget.eventHandler.PlaylistEventHandler;
import com.rmp.widget.dataWatcher.PlaylistDataWatcher;
import com.rmp.widget.readModels.UIMediaFileModel;
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

    private final AsyncTaskExecutor asyncTaskExecutor;
    private final PlaylistDataWatcher playlistDataWatcher;
    private final PlaylistService playlistService;
    private final StateService stateService;
    private final MediaFileService mediaFileService;

    /**
     * Initialize new instance of {@link PlaylistEventHandlerImpl}
     */
    public PlaylistEventHandlerImpl(PlaylistDataWatcher playlistDataWatcher,
                                    AsyncTaskExecutor asyncTaskExecutor) {
        this.playlistDataWatcher = playlistDataWatcher;
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
            this.playlistDataWatcher.getPlaylistModelObserver().emit(this.playlistService.getAllPlaylists());
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
            StateModel currentState = this.stateService.getCurrentState();
            currentState.setPlaylistId(id);
            currentState = this.stateService.updateState(currentState);

            this.playlistDataWatcher.getSelectedPlaylistObserver().emit(
                    this.playlistService.getById(currentState.getPlaylistId())
            );

            this.playlistDataWatcher.getReplaceMediaFilesObserver().emit(
                    this.mediaFileService.getByPlaylistId(currentState.getPlaylistId())
            );
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
                this.playlistDataWatcher.getAddMediaFileObserver().emit(mediaFile);
            });
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
            this.playlistDataWatcher.getReplaceMediaFilesObserver().emit(
                    this.mediaFileService.getByPlaylistId(currentState.getPlaylistId())
            );
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
}
