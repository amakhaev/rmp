package com.rmp.vlcPlayer;

import com.rmp.vlcPlayer.mediaData.MediaPlaylist;
import lombok.Getter;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

/**
 * Provides the wrapper on VLC player.
 */
public class VlcMediaPlayer {

    private MediaPlaylist mediaPlaylist;
    private EmbeddedMediaPlayer mediaPlayer;
    private InternalMediaPlayerEventListener eventListener;

    @Getter
    private boolean isPlaying;

    private boolean isMuted;

    /**
     * Initialize new instance of {@link VlcMediaPlayer}
     */
    public VlcMediaPlayer(MediaPlaylist mediaPlaylist, VlcMediaPlayerEventListener eventListener) {
        this.mediaPlayer = new MediaPlayerFactory().newEmbeddedMediaPlayer();
        this.eventListener = this.createEventListener(eventListener);
        this.mediaPlayer.addMediaPlayerEventListener(this.eventListener);
        this.isMuted = this.mediaPlayer.isMute();

        this.setMediaPlaylist(mediaPlaylist);

        if (this.mediaPlaylist.getCurrentMedia() != null) {
            this.mediaPlayer.prepareMedia(this.mediaPlaylist.getCurrentMedia());
            this.mediaPlayer.requestParseMedia();
            this.eventListener.playlistSelectedItemChanged(this.mediaPlaylist.getCurrentIndex());
        }
    }

    /**
     * Plays the selected media item.
     */
    public void play() {
        this.mediaPlayer.play();
        this.isPlaying = true;
    }

    /**
     * Plays the selected media item.
     */
    public void play(String path) {
        this.mediaPlayer.stop();
        this.mediaPlaylist.setCurrentMedia(path);
        this.mediaPlayer.prepareMedia(this.mediaPlaylist.getCurrentMedia());
        this.mediaPlayer.requestParseMedia();
        this.play();
        this.eventListener.playlistSelectedItemChanged(this.mediaPlaylist.getCurrentIndex());
    }

    /**
     * Pauses the selected media item.
     */
    public void pause() {
        this.mediaPlayer.pause();
        this.isPlaying = false;
    }

    /**
     * Stops the playing of media object
     */
    public void stop() {
        this.isPlaying = false;
        this.mediaPlayer.stop();
    }

    /**
     * Mutes the sound of media player
     */
    public boolean mute() {
        this.mediaPlayer.mute();
        this.isMuted = !this.isMuted;
        this.eventListener.muted(this.mediaPlayer, this.isMuted);
        return this.isMuted;
    }

    /**
     * Mutes the sound of media player
     */
    public void mute(boolean isMute) {
        this.isMuted = isMute;
        this.mediaPlayer.mute(isMute);
        this.eventListener.muted(this.mediaPlayer, this.isMuted);
    }

    /**
     * Plays the next media object
     */
    public void playNext() {
        this.stop();

        if (!this.mediaPlaylist.hasNext()) {
            return;
        }

        this.mediaPlayer.prepareMedia(this.mediaPlaylist.getNextMedia());
        this.mediaPlayer.requestParseMedia();
        this.play();
        this.eventListener.playlistSelectedItemChanged(this.mediaPlaylist.getCurrentIndex());
    }

    /**
     * Plays the prev media object
     */
    public void playPrev() {
        this.stop();

        if (!this.mediaPlaylist.hasPrev()) {
            return;
        }

        this.mediaPlayer.prepareMedia(this.mediaPlaylist.getPrevMedia());
        this.mediaPlayer.requestParseMedia();
        this.play();
        this.eventListener.playlistSelectedItemChanged(this.mediaPlaylist.getCurrentIndex());
    }

    /**
     * Sets the time of current media item
     *
     * @param time - the time value
     */
    public void setTime(int time) {
        this.mediaPlayer.setTime(time);
    }

    /**
     * Gets the index of media file that is selected in current moment
     */
    public int getSelectedMediaFileIndex() {
        return this.mediaPlaylist.getCurrentIndex();
    }

    /**
     * Gets the path of media file that is selected in current moment
     */
    public String getSelectedMediaFilePath() {
        return this.mediaPlaylist.getCurrentMedia();
    }

    /**
     * Sets the media playlist
     *
     * @param mediaPlaylist - the media playlist ot use
     */
    public void setMediaPlaylist(MediaPlaylist mediaPlaylist) {
        this.mediaPlaylist = mediaPlaylist;
        this.eventListener.playlistItemCountChanged(
                this.mediaPlaylist == null ? 0 : this.mediaPlaylist.getMediaCount()
        );
    }

    private InternalMediaPlayerEventListener createEventListener(VlcMediaPlayerEventListener eventListener) {
        return new InternalMediaPlayerEventListener(eventListener) {
            @Override
            public void finished(MediaPlayer mediaPlayer) {
                if (this.eventListener == null) {
                    return;
                }

                this.eventListener.onFinish(VlcMediaPlayer.this);
            }

            @Override
            public void playing(MediaPlayer mediaPlayer) {
                if (this.eventListener == null) {
                    return;
                }

                this.eventListener.onPlaying(VlcMediaPlayer.this);
            }

            @Override
            public void stopped(MediaPlayer mediaPlayer) {
                if (this.eventListener == null) {
                    return;
                }

                this.eventListener.onStopped(VlcMediaPlayer.this);
            }
        };
    }
}
