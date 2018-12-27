package com.rmp.widget.components.rootPanel;

import com.rmp.widget.components.controlPanel.ControlPanelComponent;
import com.rmp.widget.components.controlPanel.ControlPanelBuilder;
import com.rmp.widget.components.playlistPanel.PlaylistPanelBuilder;
import com.rmp.widget.components.playlistPanel.PlaylistPanelComponent;
import com.rmp.widget.controls.gradientPanel.GradientJPanel;
import com.rmp.widget.skins.RMPSkin;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

/**
 * Provides the root panel of widget
 */
public class RootPanelComponent {

    @Getter
    private JPanel rootPanel;

    private RMPSkin skin;

    /**
     * Initialize new instance of {@link RootPanelComponent}
     *
     * @param skin - the skin of root panel
     */
    RootPanelComponent(RMPSkin skin) {
        this.skin = skin;
        this.rootPanel = new GradientJPanel(this.skin.getBackgroundStartColor(), this.skin.getBackgroundEndColor());
    }

    /**
     * Initializes the root panel
     */
    void initialize(PlaylistPanelBuilder playlistPanelBuilder, ControlPanelBuilder controlPanelBuilder) {
        this.rootPanel.setLayout(new BorderLayout());

        PlaylistPanelComponent playlistPanelComponent = playlistPanelBuilder
                .setSkin(this.skin.getPlaylistSkin())
                .setPlaylistPanelSize(
                        new Dimension(
                                (int)skin.getSize().getWidth() / 100 * 35,
                                (int)skin.getSize().getHeight()
                        )
                )
                .build();

        ControlPanelComponent controlPanelComponent = controlPanelBuilder
                .setSkin(this.skin.getButtonPanelSkin())
                .build();

        this.rootPanel.add(playlistPanelComponent.getPlaylistPanel(), BorderLayout.LINE_END);
        this.rootPanel.add(controlPanelComponent.getButtonPanel(), BorderLayout.PAGE_END);
    }

}
