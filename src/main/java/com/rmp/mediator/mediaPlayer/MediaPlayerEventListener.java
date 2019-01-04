package com.rmp.mediator.mediaPlayer;

import com.rmp.mediator.UIWatcherContainer;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;

/**
 * Provides the event listener of media player
 */
public class MediaPlayerEventListener extends MediaPlayerEventAdapter {

    private final UIWatcherContainer watcherContainer;

    private boolean isPlaying;

    /**
     * Initialize new instance of {@link MediaPlayerEventListener}
     */
    public MediaPlayerEventListener(UIWatcherContainer watcherContainer) {
        this.isPlaying = false;
        this.watcherContainer = watcherContainer;
    }

    @Override
    public void playing(MediaPlayer mediaPlayer) {
        this.isPlaying = true;
    }

    @Override
    public void paused(MediaPlayer mediaPlayer) {
        this.isPlaying = false;
    }

    @Override
    public void stopped(MediaPlayer mediaPlayer) {
        this.isPlaying = false;
        this.watcherContainer.getControlPanelDataWatcher().getIsPlayingObserver().emit(this.isPlaying);
        this.watcherContainer.getPlaylistDataWatcher().getSelectedMediaFileIdObserver().emit(null);
        this.watcherContainer.getControlPanelDataWatcher().getTimelineLengthChangedObserver().emit(0L);
        this.watcherContainer.getControlPanelDataWatcher().getTimelineValueChangedObserver().emit(0L);
    }

    @Override
    public void timeChanged(MediaPlayer mediaPlayer, long newTime) {
        // newTime stored in milliseconds. Should be converted to seconds
        this.watcherContainer.getControlPanelDataWatcher().getTimelineValueChangedObserver().emit(newTime / 1000);
    }

    @Override
    public void lengthChanged(MediaPlayer mediaPlayer, long newLength) {
        // newLength stored in milliseconds. Should be converted to seconds
        this.watcherContainer.getControlPanelDataWatcher().getTimelineLengthChangedObserver().emit(newLength / 1000);
    }
}
