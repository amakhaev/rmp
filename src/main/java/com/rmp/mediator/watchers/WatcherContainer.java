package com.rmp.mediator.watchers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.rmp.widget.components.controlPanel.timelinePanel.TimeLabelOrder;
import com.rmp.widget.dataWatcher.ControlPanelDataWatcher;
import com.rmp.widget.dataWatcher.MediaDetailDataWatcher;
import com.rmp.widget.dataWatcher.PlaylistDataWatcher;
import com.rmp.widget.readModels.UIMediaFileModel;
import com.rmp.widget.readModels.UIPlaylistModel;
import lombok.Getter;

import java.awt.*;
import java.util.List;

/**
 * Provides the container that stored all UI watchers
 */
@Singleton
public class WatcherContainer {

    @Getter
    private final ControlPanelDataWatcher controlPanelDataWatcher;

    @Getter
    private final PlaylistDataWatcher playlistDataWatcher;

    @Getter
    private final MediaDetailDataWatcher mediaDetailDataWatcher;

    /**
     * Initialize new instance of {@link WatcherContainer}
     */
    @Inject
    public WatcherContainer(ControlPanelDataWatcher controlPanelDataWatcher,
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
     * Emits the total count changed
     *
     * @param totalCount - the new total count value
     */
    public void emitTotalCountChanged(int totalCount) {
        this.mediaDetailDataWatcher.getTotalCountObserver().emit(totalCount);
    }

    /**
     * Emits the selected media item index changed
     *
     * @param selectedFileIndex - the new index value
     */
    public void emitSelectedMediaItemIndexChanged(int selectedFileIndex) {
        this.mediaDetailDataWatcher.getSelectedMediaIndexObserver().emit(selectedFileIndex);
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
    public void emitMediaFilesAdded(List<UIMediaFileModel> mediaFiles) {
        this.playlistDataWatcher.getAddMediaFilesObserver().emit(mediaFiles);
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
     * Emits the mute state
     *
     * @param isMute - indicates when volume is mute
     */
    public void emitMuteStateChanged(boolean isMute) {
        this.controlPanelDataWatcher.getMuteChangedObserver().emit(isMute);
    }

    /**
     * Emits the changing of volume value
     *
     * @param value - the volume value
     */
    public void emitVolumeChanged(int value) {
        this.controlPanelDataWatcher.getVolumeValueChangedObserver().emit(value);
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
        this.emitMediaFilesAdded(mediaFiles);
        this.emitIsPlayingChanged(isPlaying);
    }
}
