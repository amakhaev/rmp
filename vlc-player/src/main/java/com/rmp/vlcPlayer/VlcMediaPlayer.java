package com.rmp.vlcPlayer;

import com.rmp.vlcPlayer.mediaData.MediaPlaylist;
import lombok.Getter;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

/**
 * Provides the wrapper on VLC player.
 */
public class VlcMediaPlayer {

    private MediaPlaylist mediaPlaylist;
    private EmbeddedMediaPlayer mediaPlayer;

    @Getter
    private boolean isPlaying;

    /**
     * Initialize new instance of {@link VlcMediaPlayer}
     */
    public VlcMediaPlayer(MediaPlaylist mediaPlaylist) {
        this.mediaPlayer = new MediaPlayerFactory().newEmbeddedMediaPlayer();
        this.mediaPlaylist = mediaPlaylist;

        if (this.mediaPlaylist.getCurrentMedia() != null) {
            this.mediaPlayer.prepareMedia(this.mediaPlaylist.getCurrentMedia());
        }
        this.isPlaying = false;
    }

    /**
     * Plays the selected media item.
     */
    public void play() {
        if (this.mediaPlaylist.getCurrentMedia() != null) {
            this.mediaPlayer.play();
            this.isPlaying = true;
        }
    }

    /**
     * Plays the selected media item.
     */
    public void play(String path) {
        this.mediaPlayer.stop();
        this.mediaPlaylist.setCurrentMedia(path);
        this.mediaPlayer.prepareMedia(this.mediaPlaylist.getCurrentMedia());
        this.play();
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
        this.mediaPlayer.stop();
        this.isPlaying = false;
    }

    /**
     * Plays the next media object
     */
    public void playNext() {
        this.stop();
        this.mediaPlayer.prepareMedia(this.mediaPlaylist.getNextMedia());
        this.play();
    }

    /**
     * Plays the prev media object
     */
    public void playPrev() {
        this.stop();
        this.mediaPlayer.prepareMedia(this.mediaPlaylist.getPrevMedia());
        this.play();
    }

    /**
     * Gets the index of media file that is selected in current moment
     */
    public int getSelectedMediaFileIndex() {
        return this.mediaPlaylist.getCurrentIndex();
    }
}
