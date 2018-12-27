package com.rmp.mediator;

import com.rmp.mediator.ui.ControlPanelWatcher;
import com.rmp.mediator.ui.PlaylistWatcher;
import com.rmp.widget.dataWatcher.ControlPanelDataWatcher;
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

    /**
     * Initialize new instance of {@link UIWatcherContainer}
     */
    public UIWatcherContainer() {
        this.controlPanelDataWatcher = new ControlPanelWatcher();
        this.playlistDataWatcher = new PlaylistWatcher();
    }
}
