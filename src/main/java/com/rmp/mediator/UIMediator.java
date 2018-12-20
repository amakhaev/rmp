package com.rmp.mediator;

import com.rmp.mediator.service.PlaylistService;
import com.rmp.mediator.taskExecutor.AsyncTaskExecutor;
import com.rmp.mediator.watcher.PlaylistWatcher;
import com.rmp.widget.RMPWidget;
import com.rmp.widget.RMPWidgetBuilder;
import com.rmp.widget.dataWatcher.PlaylistDataWatcher;
import com.rmp.widget.skins.Skin;

import javax.swing.*;

/**
 * Provides the com.rmp.mediator.mediator that working with UI
 */
public class UIMediator {

    private AsyncTaskExecutor asyncTaskExecutor;
    private PlaylistService playlistService;

    private PlaylistDataWatcher playlistDataWatcher;
    private RMPWidget widget;

    /**
     * Initialize new instance of {@link UIMediator}
     */
    public UIMediator() {
        this.playlistDataWatcher = new PlaylistWatcher();
        this.asyncTaskExecutor = new AsyncTaskExecutor();
        // this.playlistService = new PlaylistService();

        // this.initializeUI();
    }

    /**
     * Shows the UI widget
     */
    public void showUI() {
        if (this.widget == null) {
            this.widget = new RMPWidgetBuilder()
                    .setSkin(Skin.DEFAULT)
                    .setDataWatcher(this.playlistDataWatcher)
                    .build();
        }

        SwingUtilities.invokeLater(() -> this.widget.showWidget());
    }

    private void initializeUI() {
        System.out.println("start init: " + Thread.currentThread().getName());
        this.asyncTaskExecutor.executeTask(() -> {
            this.playlistDataWatcher.getPlaylistModelObserver().emit(this.playlistService.getAllPlaylists());
            System.out.println("complete init: " + Thread.currentThread().getName());
        });
    }
}
