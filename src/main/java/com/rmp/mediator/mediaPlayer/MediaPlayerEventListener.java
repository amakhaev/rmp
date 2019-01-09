package com.rmp.mediator.mediaPlayer;

import com.rmp.mediator.UIWatcherContainer;
import com.rmp.mediator.service.mediaFile.MediaFileService;
import com.rmp.mediator.service.playlist.PlaylistService;
import com.rmp.mediator.service.state.StateService;
import com.rmp.mediator.taskExecutor.AsyncTaskExecutor;
import com.rmp.vlcPlayer.VlcMediaPlayer;
import com.rmp.vlcPlayer.VlcMediaPlayerEventListener;
import com.rmp.widget.readModels.UIMediaFileModel;
import uk.co.caprica.vlcj.player.MediaMeta;

import java.util.List;

/**
 * Provides the event listener of media player
 */
public class MediaPlayerEventListener implements VlcMediaPlayerEventListener {

    private final UIWatcherContainer watcherContainer;
    private final AsyncTaskExecutor asyncTaskExecutor;

    private final StateService stateService;
    private final PlaylistService playlistService;
    private final MediaFileService mediaFileService;

    // private boolean isPlaying;

    /**
     * Initialize new instance of {@link MediaPlayerEventListener}
     */
    public MediaPlayerEventListener(UIWatcherContainer watcherContainer, AsyncTaskExecutor asyncTaskExecutor) {
        this.asyncTaskExecutor = asyncTaskExecutor;
        this.watcherContainer = watcherContainer;

        this.stateService = new StateService();
        this.playlistService = new PlaylistService();
        this.mediaFileService = new MediaFileService();
    }

    /**
     * Handles the playing event. Called when media item started/proceeded playing
     */
    @Override
    public void onPlaying(VlcMediaPlayer mediaPlayer) {
        UIMediaFileModel model = this.mediaFileService.getByPlaylistIdAndPath(
                this.stateService.getCurrentState().getPlaylistId(),
                mediaPlayer.getSelectedMediaFilePath()
        );
        this.watcherContainer.emitSelectedMediaFileChanged(model == null ? null : model.getId());
    }

    /**
     * Handles the stopped event. Called when media item stopped
     */
    @Override
    public void onStopped(VlcMediaPlayer mediaPlayer) {
        this.watcherContainer.emitIsPlayingChanged(mediaPlayer.isPlaying());
        this.watcherContainer.emitSelectedMediaFileChanged(null);
        this.watcherContainer.emitTimelineLengthChanged(0L);
        this.watcherContainer.emitTimelineValueChanged(0L);
    }

    /**
     * Handles the time changing event. Called when media item play time was changed
     *
     * @param newTime - the time value
     */
    @Override
    public void onTimeChanged(long newTime) {
        // newTime stored in milliseconds. Should be converted to seconds
        this.watcherContainer.emitTimelineValueChanged(newTime / 1000);
    }

    /**
     * Handles the length changing event. Called when media item time length was changed
     *
     * @param newLength - the length time value
     */
    @Override
    public void onLengthChanged(long newLength) {
        // newLength stored in milliseconds. Should be converted to seconds
        this.watcherContainer.emitTimelineLengthChanged(newLength / 1000);
    }

    /**
     * Handles the finish event. Called when media item finished
     *
     * @param mediaPlayer - the media player
     */
    @Override
    public void onFinish(VlcMediaPlayer mediaPlayer) {
        this.asyncTaskExecutor.executeTask(() -> {
            mediaPlayer.playNext();
            this.updateAfterMediaFileChanged(mediaPlayer);
        });
    }

    /**
     * Handles the changing of media metadata
     *
     * @param metadata - the metadata of current item
     */
    @Override
    public void onMetadataChanged(MediaMeta metadata) {
        this.asyncTaskExecutor.executeTask(() -> {
            this.watcherContainer.emitMediaDetailArtChanged(metadata.getArtwork());
        });
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
}
