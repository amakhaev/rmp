package com.rmp.mediator.mediaPlayer;

import com.rmp.vlcPlayer.VlcMediaPlayer;
import com.rmp.vlcPlayer.VlcMediaPlayerEventListener;
import uk.co.caprica.vlcj.player.MediaMeta;

/**
 * Provides the event listener of media player
 */
public class MediaPlayerEventListener implements VlcMediaPlayerEventListener {

    private final PlayerMediator playerMediator;

    /**
     * Initialize new instance of {@link MediaPlayerEventListener}
     */
    MediaPlayerEventListener(PlayerMediator playerMediator) {
        this.playerMediator = playerMediator;
    }

    /**
     * Handles the playing event. Called when media item started/proceeded playing
     */
    @Override
    public void onPlaying(VlcMediaPlayer mediaPlayer) {
        this.playerMediator.play(mediaPlayer.getSelectedMediaFilePath());
    }

    /**
     * Handles the stopped event. Called when media item stopped
     */
    @Override
    public void onStopped(VlcMediaPlayer mediaPlayer) {
        this.playerMediator.emitStop();
    }

    /**
     * Handles the time changing event. Called when media item play time was changed
     *
     * @param newTime - the time value
     */
    @Override
    public void onTimeChanged(long newTime) {
        this.playerMediator.emitTimeChanged(newTime / 1000);
    }

    /**
     * Handles the length changing event. Called when media item time length was changed
     *
     * @param newLength - the length time value
     */
    @Override
    public void onLengthChanged(long newLength) {
        // newLength stored in milliseconds. Should be converted to seconds
        this.playerMediator.emitTimeLengthChanged(newLength / 1000);
    }

    /**
     * Handles the finish event. Called when media item finished
     *
     * @param mediaPlayer - the media player
     */
    @Override
    public void onFinish(VlcMediaPlayer mediaPlayer) {
        this.playerMediator.playNext();
    }

    /**
     * Handles the changing of media metadata
     *
     * @param metadata - the metadata of current item
     */
    @Override
    public void onMetadataChanged(MediaMeta metadata) {
        this.playerMediator.emitMediaArtChanged(metadata.getArtwork());
    }

    /**
     * Handles the changing of items count in playlist
     *
     * @param totalCount - the total count of items
     */
    @Override
    public void onMediaItemTotalCountChanged(int totalCount) {
        this.playerMediator.emitTotalCountChanged(totalCount);
    }

    /**
     * Handles the changing of selected item in playlist
     *
     * @param selectedItemIndex - the index of selected item
     */
    @Override
    public void onSelectedMediaItemChanged(int selectedItemIndex) {
        this.playerMediator.emitSelectedMediaIndex(selectedItemIndex + 1);
    }

    /**
     * Handles the changing of mute state
     *
     * @param isMuted - the mute state
     */
    @Override
    public void onMuteChanged(boolean isMuted) {
        this.playerMediator.emitMuteState(isMuted);
    }
}
