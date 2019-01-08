package com.rmp.vlcPlayer;

import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;

/**
 * Provides the event listener of embedded media player
 */
abstract class InternalMediaPlayerEventListener extends MediaPlayerEventAdapter {

    protected final VlcMediaPlayerEventListener eventListener;

    /**
     * Initialize new instance of {@link InternalMediaPlayerEventListener}
     */
    InternalMediaPlayerEventListener(VlcMediaPlayerEventListener eventListener) {
        this.eventListener = eventListener;
    }

    @Override
    public void paused(MediaPlayer mediaPlayer) {
        if (this.eventListener == null) {
            return;
        }

        this.eventListener.onPaused();
    }

    @Override
    public void stopped(MediaPlayer mediaPlayer) {
        if (this.eventListener == null) {
            return;
        }

        this.eventListener.onStopped();
    }

    @Override
    public void timeChanged(MediaPlayer mediaPlayer, long newTime) {
        if (this.eventListener == null) {
            return;
        }

        this.eventListener.onTimeChanged(newTime);
    }

    @Override
    public void lengthChanged(MediaPlayer mediaPlayer, long newLength) {
        if (this.eventListener == null) {
            return;
        }

        this.eventListener.onLengthChanged(newLength);
    }

}
