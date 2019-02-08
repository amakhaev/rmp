package com.rmp.mediator.mediaPlayer;

import com.rmp.dao.domain.state.StateModel;
import com.rmp.mediator.watchers.WatcherContainer;
import com.rmp.mediator.service.mediaFile.MediaFileService;
import com.rmp.mediator.service.playlist.PlaylistService;
import com.rmp.mediator.service.state.StateService;
import com.rmp.mediator.taskExecutor.AsyncTaskExecutor;
import com.rmp.vlcPlayer.VlcMediaPlayer;
import com.rmp.widget.components.controlPanel.timelinePanel.TimeLabelOrder;
import com.rmp.widget.readModels.UIMediaFileModel;
import com.rmp.widget.readModels.UIPlaylistModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Provides the adapter to use vlc-player
 */
@Slf4j
public class PlayerMediator {

    private final AsyncTaskExecutor asyncTaskExecutor;
    private final WatcherContainer watcherContainer;

    private VlcMediaPlayer mediaPlayer;
    private PlaylistService playlistService;
    private StateService stateService;
    private MediaFileService mediaFileService;

    /**
     * Initialize new instance of {@link PlayerMediator}
     */
    public PlayerMediator(AsyncTaskExecutor asyncTaskExecutor, WatcherContainer watcherContainer) {
        this.asyncTaskExecutor = asyncTaskExecutor;
        this.watcherContainer = watcherContainer;
        this.playlistService = new PlaylistService();
        this.stateService = new StateService();
        this.mediaFileService = new MediaFileService();
    }

    /**
     * Initializes the mediator
     */
    public void initialize() {
        this.mediaPlayer = this.createMediaPlayer();
    }

    /**
     * Plays the media file.
     *
     * @param mediaFilePath - the path to media file
     */
    public void play(String mediaFilePath) {
        UIMediaFileModel model = this.mediaFileService.getByPlaylistIdAndPath(
                this.stateService.getCurrentState().getPlaylistId(),
                mediaFilePath
        );

        this.emitSelectedMediaFileChanged(model == null ? null : model.getId());
    }

    /**
     * Plays the media file
     *
     * @param mediaFileId - the media file id
     */
    public void play(int mediaFileId) {
        this.asyncTaskExecutor.executeTask(() -> {
            UIMediaFileModel mediaFile = this.mediaFileService.getById(mediaFileId);
            this.mediaPlayer.play(mediaFile.getPath());
            this.updateAfterMediaFileChanged(this.mediaPlayer);
        });
    }

    /**
     * Plays the media file.
     */
    public void play() {
        this.asyncTaskExecutor.executeTask(() -> {
            if (this.mediaPlayer.isPlaying()) {
                this.mediaPlayer.pause();
            } else {
                this.mediaPlayer.play();
            }
            this.updateAfterMediaFileChanged(this.mediaPlayer);
        });
    }

    /**
     * Plays the next media file.
     */
    public void playNext() {
        this.asyncTaskExecutor.executeTask(() -> {
            this.mediaPlayer.playNext();
            this.updateAfterMediaFileChanged(this.mediaPlayer);
        });
    }

    /**
     * Plays the prev media file.
     */
    public void playPrev() {
        this.asyncTaskExecutor.executeTask(() -> {
            this.mediaPlayer.playPrev();
            this.updateAfterMediaFileChanged(this.mediaPlayer);
        });
    }

    /**
     * Stops the playing of media file
     */
    public void stopPlaying() {
        this.asyncTaskExecutor.executeTask(() -> {
            this.mediaPlayer.stop();
            this.stateService.updatePlaylistFile(null);
            this.emitMediaDetailChanged(null);
        });
    }

    /**
     * Toggles the mute state
     */
    public void toggleMute() {
        this.asyncTaskExecutor.executeTask(() -> this.stateService.updateMuteState(this.mediaPlayer.mute()));
    }

    /**
     * Sets the mute state
     *
     * @param isMute - the new mute state
     */
    public void setMute(boolean isMute) {
        this.asyncTaskExecutor.executeTask(() -> this.mediaPlayer.mute(isMute));
    }

    /**
     * Sets the timeline value
     *
     * @param value - the value that was changed
     */
    public void setTimelineValue(int value) {
        this.asyncTaskExecutor.executeTask(() -> this.mediaPlayer.setTime(value));
    }

    /**
     * Sets the time label order
     *
     * @param order - the order of time label
     */
    public void setTimeLabelOrder(TimeLabelOrder order) {
        this.asyncTaskExecutor.executeTask(() -> {
            switch (order) {
                case ASC:
                    this.stateService.updateTimeLabelOrder(com.rmp.dao.domain.state.TimeLabelOrder.ASC);
                    break;
                case DESC:
                    this.stateService.updateTimeLabelOrder(com.rmp.dao.domain.state.TimeLabelOrder.DESC);
                    break;
            }
            this.watcherContainer.emitTimeLabelOrderChanged(order);
        });
    }

    /**
     * Creates new playlist
     *
     * @param playlistTitle - the title of new playlist
     */
    public void createPlaylist(String playlistTitle) {
        this.asyncTaskExecutor.executeTask(() -> {
            this.playlistService.createPlaylist(playlistTitle);
            this.emitPlaylists(this.playlistService.getAllPlaylists());
            this.setActivePlaylist(this.playlistService.getByTitle(playlistTitle).getId());
        });
    }

    /**
     * Sets the active playlist
     *
     * @param playlistId - the playlist id
     */
    public void setActivePlaylist(int playlistId) {
        this.asyncTaskExecutor.executeTask(() -> {
            StateModel currentState = this.stateService.updatePlaylistId(playlistId);

            this.mediaPlayer.setMediaPlaylist(
                    MediaPlaylistFactory.create(this.stateService.getCurrentState(), this.mediaFileService)
            );

            this.watcherContainer.emitPlaylistChanged(
                    this.playlistService.getById(currentState.getPlaylistId()),
                    this.mediaFileService.getByPlaylistId(currentState.getPlaylistId()),
                    this.mediaPlayer.isPlaying()
            );
        });
    }

    /**
     * Adds the media files to active playlist
     *
     * @param files - the files that should be added
     */
    public void addMediaFilesToActivePlaylist(List<File> files) {
        this.asyncTaskExecutor.executeTask(() -> {
            List<String> filePaths = this.getFilePaths(files);
            StateModel currentState = this.stateService.getCurrentState();

            filePaths.forEach(filePath -> {
                UIMediaFileModel mediaFile = new UIMediaFileModel();
                mediaFile.setPath(filePath);
                mediaFile.setPlaylistId(currentState.getPlaylistId());

                mediaFile = this.mediaFileService.createMediaFile(mediaFile);
                this.watcherContainer.emitMediaFileAdded(mediaFile);
            });

            this.mediaPlayer.setMediaPlaylist(
                    MediaPlaylistFactory.create(this.stateService.getCurrentState(), this.mediaFileService)
            );
        });
    }

    /**
     * Deletes the media files
     *
     * @param mediaFileIds - the identifiers of media files
     */
    public void deleteMediaFiles(List<Integer> mediaFileIds) {
        this.asyncTaskExecutor.executeTask(() -> {
            StateModel currentState = this.stateService.getCurrentState();

            this.mediaFileService.deleteMediaFiles(mediaFileIds);
            this.watcherContainer.emitMediaFilesAdded(
                    this.mediaFileService.getByPlaylistId(currentState.getPlaylistId())
            );

            this.mediaPlayer.setMediaPlaylist(
                    MediaPlaylistFactory.create(this.stateService.getCurrentState(), this.mediaFileService)
            );
        });
    }

    /**
     * Emits the stopping of playing to UI
     */
    public void emitStop() {
        this.watcherContainer.emitIsPlayingChanged(mediaPlayer.isPlaying());
        this.watcherContainer.emitSelectedMediaFileChanged(null);
        this.watcherContainer.emitTimelineLengthChanged(0L);
        this.watcherContainer.emitTimelineValueChanged(0L);
    }


    /**
     * Emits the time change event to UI
     *
     * @param newTime - the new time value
     */
    public void emitTimeChanged(long newTime) {
        this.watcherContainer.emitTimelineValueChanged(newTime);
    }

    /**
     * Emits the time length change event to UI
     *
     * @param newLength - the new length
     */
    public void emitTimeLengthChanged(long newLength) {
        this.watcherContainer.emitTimelineLengthChanged(newLength);
    }

    /**
     * Emits the changing of media file
     *
     * @param mediaArt - new media file art
     */
    public void emitMediaArtChanged(Image mediaArt) {
        this.asyncTaskExecutor.executeTask(() -> this.watcherContainer.emitMediaDetailArtChanged(mediaArt));
    }

    /**
     * Emits the changing of total count of media files
     *
     * @param totalCount - the total count value
     */
    public void emitTotalCountChanged(int totalCount) {
        this.asyncTaskExecutor.executeTask(() -> this.watcherContainer.emitTotalCountChanged(totalCount));
    }

    /**
     * Emits the index of selected media item
     *
     * @param index - the media file index
     */
    public void emitSelectedMediaIndex(int index) {
        this.asyncTaskExecutor.executeTask(() -> this.watcherContainer.emitSelectedMediaItemIndexChanged(index));
    }

    /**
     * Emits the all playlists
     */
    public void emitPlaylists(List<UIPlaylistModel> playlists) {
        this.asyncTaskExecutor.executeTask(() ->
                this.watcherContainer.emitPlaylistsChanged(playlists)
        );
    }

    /**
     * Emits the changing of selected playlist
     *
     * @param playlistModel - the selected playlist
     */
    public void emitSelectedPlaylistChanged(UIPlaylistModel playlistModel) {
        this.asyncTaskExecutor.executeTask(() -> this.watcherContainer.emitSelectedPlaylistChanged(playlistModel));
    }

    /**
     * Emits the adding of media files
     *
     * @param mediaFiles - the media files that should be emited
     */
    public void emitMediaFileAdded(List<UIMediaFileModel> mediaFiles) {
        this.asyncTaskExecutor.executeTask(() -> this.watcherContainer.emitMediaFilesAdded(mediaFiles));
    }

    /**
     * Emits the selected media file changed
     *
     * @param mediaFileId - the selected media file id
     */
    public void emitSelectedMediaFileChanged(Integer mediaFileId) {
        this.asyncTaskExecutor.executeTask(() -> this.watcherContainer.emitSelectedMediaFileChanged(mediaFileId));
    }

    /**
     * Emit changing of media detail
     *
     * @param mediaFileModel - the media detail model
     */
    public void emitMediaDetailChanged(UIMediaFileModel mediaFileModel) {
        this.asyncTaskExecutor.executeTask(() -> this.watcherContainer.emitMediaDetailChanged(mediaFileModel));
    }

    /**
     * Emits changing of mute state
     */
    public void emitMuteState(boolean isMuted) {
        this.asyncTaskExecutor.executeTask(() -> this.watcherContainer.emitMuteStateChanged(isMuted));
    }

    private VlcMediaPlayer createMediaPlayer() {
        return new VlcMediaPlayer(
                MediaPlaylistFactory.create(this.stateService.getCurrentState(), this.mediaFileService),
                new MediaPlayerEventListener(this)
        );
    }

    private void updateAfterMediaFileChanged(VlcMediaPlayer mediaPlayer) {
        List<UIMediaFileModel> mediaFileModels = this.mediaFileService.getByPlaylistId(
                this.playlistService.getById(this.stateService.getCurrentState().getPlaylistId()).getId()
        );

        if (mediaFileModels != null && !mediaFileModels.isEmpty()) {
            this.stateService.updatePlaylistFile(mediaFileModels.get(mediaPlayer.getSelectedMediaFileIndex()).getId());
        }

        this.watcherContainer.emitMediaFileChanged(
                this.mediaFileService.getById(this.stateService.getCurrentState().getPlaylistFileId()),
                mediaPlayer.isPlaying()
        );
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
