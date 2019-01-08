package com.rmp.mediator.ui;

import com.rmp.mediator.UIWatcherContainer;
import com.rmp.mediator.service.mediaFile.MediaFileService;
import com.rmp.mediator.service.playlist.PlaylistService;
import com.rmp.mediator.service.state.StateService;
import com.rmp.mediator.taskExecutor.AsyncTaskExecutor;
import com.rmp.vlcPlayer.VlcMediaPlayer;
import com.rmp.widget.components.controlPanel.TimeLabelOrder;
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
    private final UIWatcherContainer watcherContainer;
    private final AsyncTaskExecutor asyncTaskExecutor;

    private final StateService stateService;
    private final PlaylistService playlistService;
    private final MediaFileService mediaFileService;

    /**
     * Initialize new instance of {@link ControlPanelEventHandler}
     */
    public ControlPanelEventHandlerImpl(AsyncTaskExecutor asyncTaskExecutor, UIWatcherContainer watcherContainer) {
        this.watcherContainer = watcherContainer;
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
            this.stateService.updatePlaylistFile(null);
            this.watcherContainer.getMediaDetailDataWatcher().getMediaFileObserver().emit(null);
        });
    }

    /**
     * Handles the prev event
     */
    @Override
    public void onPrev() {
        this.asyncTaskExecutor.executeTask(() -> {
            this.mediaPlayer.playPrev();
            this.updateAfterMediaFileChanged();
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
            this.updateAfterMediaFileChanged();
        });
    }

    /**
     * Handles the next event
     */
    @Override
    public void onNext() {
        this.asyncTaskExecutor.executeTask(() -> {
            this.mediaPlayer.playNext();
            this.updateAfterMediaFileChanged();
        });
    }

    /**
     * Handles the open config event
     */
    @Override
    public void onConfigOpen() {
        System.out.println("Not implemented yet");
    }

    /**
     * Handles the changing value of timeline
     *
     * @param value - new value of timeline
     */
    @Override
    public void onTimelineValueChanged(int value) {
        this.asyncTaskExecutor.executeTask(() -> this.mediaPlayer.setTime(value));
    }

    /**
     * Handles the changing of time label order
     *
     * @param order - the value
     */
    @Override
    public void onTimeLabelOrderChanged(TimeLabelOrder order) {
        this.asyncTaskExecutor.executeTask(() -> {
            switch (order) {
                case ASC:
                    this.stateService.updateTimeLabelOrder(com.rmp.dao.domain.state.TimeLabelOrder.ASC);
                    break;
                case DESC:
                    this.stateService.updateTimeLabelOrder(com.rmp.dao.domain.state.TimeLabelOrder.DESC);
                    break;
            }
            this.watcherContainer.getControlPanelDataWatcher().getTimeLabelOrderChangedObserver().emit(order);
        });
    }

    private void updateAfterMediaFileChanged() {
        List<UIMediaFileModel> mediaFileModels = this.mediaFileService.getByPlaylistId(
                this.playlistService.getById(this.stateService.getCurrentState().getPlaylistId()).getId()
        );

        if (mediaFileModels != null && !mediaFileModels.isEmpty()) {
            this.stateService.updatePlaylistFile(mediaFileModels.get(this.mediaPlayer.getSelectedMediaFileIndex()).getId());
        }

        this.watcherContainer.getPlaylistDataWatcher().getSelectedMediaFileIdObserver().emit(
                this.stateService.getCurrentState().getPlaylistFileId()
        );
        this.watcherContainer.getControlPanelDataWatcher().getIsPlayingObserver().emit(this.mediaPlayer.isPlaying());

        this.watcherContainer.getMediaDetailDataWatcher().getMediaFileObserver().emit(
                this.mediaFileService.getById(this.stateService.getCurrentState().getPlaylistFileId())
        );
    }
}
