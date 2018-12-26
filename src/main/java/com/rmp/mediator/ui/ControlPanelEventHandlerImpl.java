package com.rmp.mediator.ui;

import com.rmp.mediator.service.mediaFile.MediaFileService;
import com.rmp.mediator.service.playlist.PlaylistService;
import com.rmp.mediator.service.state.StateService;
import com.rmp.mediator.taskExecutor.AsyncTaskExecutor;
import com.rmp.vlcPlayer.VlcMediaPlayer;
import com.rmp.widget.dataWatcher.ControlPanelDataWatcher;
import com.rmp.widget.dataWatcher.PlaylistDataWatcher;
import com.rmp.widget.eventHandler.ControlPanelEventHandler;
import com.rmp.widget.readModels.UIMediaFileModel;
import lombok.Setter;

import java.util.List;

/**
 * Provides base implementation of {@link ControlPanelEventHandler}
 */
public class ControlPanelEventHandlerImpl implements ControlPanelEventHandler {

    @Setter
    private VlcMediaPlayer mediaPlayer;
    private final ControlPanelDataWatcher controlPanelDataWatcher;
    private final PlaylistDataWatcher playlistDataWatcher;
    private final AsyncTaskExecutor asyncTaskExecutor;

    private final StateService stateService;
    private final PlaylistService playlistService;
    private final MediaFileService mediaFileService;

    /**
     * Initialize new instance of {@link ControlPanelEventHandler}
     */
    public ControlPanelEventHandlerImpl(ControlPanelDataWatcher controlPanelDataWatcher,
                                        AsyncTaskExecutor asyncTaskExecutor,
                                        PlaylistDataWatcher playlistDataWatcher) {
        this.controlPanelDataWatcher = controlPanelDataWatcher;
        this.playlistDataWatcher = playlistDataWatcher;
        this.asyncTaskExecutor = asyncTaskExecutor;
        this.stateService = new StateService();
        this.playlistService = new PlaylistService();
        this.mediaFileService = new MediaFileService();
    }

    /**
     * Handles the stop event
     */
    @Override
    public void onStop() {
        this.asyncTaskExecutor.executeTask(() -> {
            this.mediaPlayer.stop();
            this.controlPanelDataWatcher.getIsPlayingObserver().emit(this.mediaPlayer.isPlaying());
            this.stateService.updatePlaylistFile(null);
            this.playlistDataWatcher.getSelectedMediaFileIdObserver().emit(null);
        });
    }

    /**
     * Handles the prev event
     */
    @Override
    public void onPrev() {
        this.asyncTaskExecutor.executeTask(() -> {
            this.mediaPlayer.playPrev();
            this.updateAfterMediaFileChange();
        });
    }

    /**
     * Handles the play event
     */
    @Override
    public void onPlay() {
        this.asyncTaskExecutor.executeTask(() -> {
            if (this.mediaPlayer.isPlaying()) {
                this.mediaPlayer.pause();
            } else {
                this.mediaPlayer.play();
            }
            this.updateAfterMediaFileChange();
        });
    }

    /**
     * Handles the next event
     */
    @Override
    public void onNext() {
        this.asyncTaskExecutor.executeTask(() -> {
            this.mediaPlayer.playNext();
            this.updateAfterMediaFileChange();
        });
    }

    /**
     * Handles the open config event
     */
    @Override
    public void onConfigOpen() {
        System.out.println("Not implemented yet");
    }

    private void updateSelectedMediaFileState(int selectedMediaFileIndex) {
        List<UIMediaFileModel> mediaFileModels = this.mediaFileService.getByPlaylistId(
                this.playlistService.getById(this.stateService.getCurrentState().getPlaylistId()).getId()
        );

        this.stateService.updatePlaylistFile(mediaFileModels.get(selectedMediaFileIndex).getId());
    }

    private void updateAfterMediaFileChange() {
        this.updateSelectedMediaFileState(this.mediaPlayer.getSelectedMediaFileIndex());
        this.playlistDataWatcher.getSelectedMediaFileIdObserver().emit(this.stateService.getCurrentState().getPlaylistFileId());
        this.controlPanelDataWatcher.getIsPlayingObserver().emit(this.mediaPlayer.isPlaying());
    }
}
