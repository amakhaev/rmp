package com.rmp.mediator;

import com.rmp.mediator.ui.ControlPanelWatcher;
import com.rmp.mediator.ui.MediaDetailWatcher;
import com.rmp.mediator.ui.PlaylistWatcher;
import com.rmp.widget.dataWatcher.ControlPanelDataWatcher;
import com.rmp.widget.dataWatcher.MediaDetailDataWatcher;
import com.rmp.widget.dataWatcher.PlaylistDataWatcher;
import lombok.Getter;

/**
 * Provides the container that stored all UI watchers
 */
public class UIWatcherContainer {

    @Getter
    private final ControlPanelDataWatcher controlPanelDataWatcher;

    @Getter
    private final PlaylistDataWatcher playlistDataWatcher;

    @Getter
    private final MediaDetailDataWatcher mediaDetailDataWatcher;

    /**
     * Initialize new instance of {@link UIWatcherContainer}
     */
    UIWatcherContainer() {
        this.controlPanelDataWatcher = new ControlPanelWatcher();
        this.playlistDataWatcher = new PlaylistWatcher();
        this.mediaDetailDataWatcher = new MediaDetailWatcher();
    }
}
