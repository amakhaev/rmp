package com.rmp.mediator;

import com.rmp.widget.components.controlPanel.TimeLabelOrder;
import com.rmp.widget.dataWatcher.ControlPanelDataWatcher;
import com.rmp.widget.dataWatcher.MediaDetailDataWatcher;
import com.rmp.widget.dataWatcher.PlaylistDataWatcher;
import com.rmp.widget.readModels.UIMediaFileModel;
import com.rmp.widget.readModels.UIPlaylistModel;

import java.awt.*;
import java.util.List;

/**
 * Provides the container that stored all UI watchers
 */
public class UIWatcherContainer {

    private final ControlPanelDataWatcher controlPanelDataWatcher;
    private final PlaylistDataWatcher playlistDataWatcher;
    private final MediaDetailDataWatcher mediaDetailDataWatcher;

    /**
     * Initialize new instance of {@link UIWatcherContainer}
     */
    UIWatcherContainer(ControlPanelDataWatcher controlPanelDataWatcher,
                       PlaylistDataWatcher playlistDataWatcher,
                       MediaDetailDataWatcher mediaDetailDataWatcher) {
        this.controlPanelDataWatcher = controlPanelDataWatcher;
        this.playlistDataWatcher = playlistDataWatcher;
        this.mediaDetailDataWatcher = mediaDetailDataWatcher;
    }

    /**
     * Emits the media file detail message
     *
     * @param mediaFileModel - the new media file
     */
    public void emitMediaDetailChanged(UIMediaFileModel mediaFileModel) {
        this.mediaDetailDataWatcher.getMediaFileObserver().emit(mediaFileModel);
    }

    /**
     * Emits the art of media file
     *
     * @param mediaFileArt - the image art of media file
     */
    public void emitMediaDetailArtChanged(Image mediaFileArt) {
        this.mediaDetailDataWatcher.getMediaFileArtObserver().emit(mediaFileArt);
    }

    /**
     * Emits the order of timeline label after changes
     *
     * @param order - the new order of timeline label
     */
    public void emitTimeLabelOrderChanged(TimeLabelOrder order) {
        this.controlPanelDataWatcher.getTimeLabelOrderChangedObserver().emit(order);
    }

    /**
     * Emits the value of timeline after changes
     *
     * @param value - the new value of time line
     */
    public void emitTimelineValueChanged(long value) {
        this.controlPanelDataWatcher.getTimelineValueChangedObserver().emit(value);
    }

    /**
     * Emits the length of timeline after changes
     *
     * @param value - the new length of time line
     */
    public void emitTimelineLengthChanged(long value) {
        this.controlPanelDataWatcher.getTimelineLengthChangedObserver().emit(value);
    }

    /**
     * Emits the playing state
     *
     * @param isPlaying - indicates when media file is playing
     */
    public void emitIsPlayingChanged(boolean isPlaying) {
        this.controlPanelDataWatcher.getIsPlayingObserver().emit(isPlaying);
    }

    /**
     * Emits the changing of playlists
     *
     * @param playlists - the new playlists
     */
    public void emitPlaylistsChanged(List<UIPlaylistModel> playlists) {
        this.playlistDataWatcher.getPlaylistModelObserver().emit(playlists);
    }

    /**
     * Emits the adding of media file
     *
     * @param mediaFileModel - the media file that was added
     */
    public void emitMediaFileAdded(UIMediaFileModel mediaFileModel) {
        this.playlistDataWatcher.getAddMediaFileObserver().emit(mediaFileModel);
    }

    /**
     * Emits the replacing of media file
     *
     * @param mediaFiles - the new medi file list
     */
    public void emitMediaFileReplaced(List<UIMediaFileModel> mediaFiles) {
        this.playlistDataWatcher.getReplaceMediaFilesObserver().emit(mediaFiles);
    }

    /**
     * Emits the select playlist
     *
     * @param playlistModel - the new playlist
     */
    public void emitSelectedPlaylistChanged(UIPlaylistModel playlistModel) {
        this.playlistDataWatcher.getSelectedPlaylistObserver().emit(playlistModel);
    }

    /**
     * Emits the select media file
     *
     * @param mediaFileId - the id of new media file
     */
    public void emitSelectedMediaFileChanged(Integer mediaFileId) {
        this.playlistDataWatcher.getSelectedMediaFileIdObserver().emit(mediaFileId);
    }

    /**
     * Emits the media file after changes
     *
     * @param mediaFile - the new media file
     * @param isPlaying - indicates when media file is playing
     */
    public void emitMediaFileChanged(UIMediaFileModel mediaFile, boolean isPlaying) {
        this.emitSelectedMediaFileChanged(mediaFile == null ? null : mediaFile.getId());
        this.emitIsPlayingChanged(isPlaying);
        this.emitMediaDetailChanged(mediaFile);
    }

    /**
     * Emits the data after playlist changing
     *
     * @param playlistModel - the new playlist
     * @param mediaFiles - the new media files
     * @param isPlaying - indicates when media file is playing
     */
    public void emitPlaylistChanged(UIPlaylistModel playlistModel, List<UIMediaFileModel> mediaFiles, boolean isPlaying) {
        this.emitSelectedPlaylistChanged(playlistModel);
        this.emitMediaFileReplaced(mediaFiles);
        this.emitIsPlayingChanged(isPlaying);
    }
}
