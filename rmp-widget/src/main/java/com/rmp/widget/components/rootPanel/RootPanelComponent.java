package com.rmp.widget.components.rootPanel;

import com.rmp.widget.components.controlPanel.ControlPanelComponent;
import com.rmp.widget.components.controlPanel.ControlPanelBuilder;
import com.rmp.widget.components.mediaDetailPanel.MediaDetailBuilder;
import com.rmp.widget.components.mediaDetailPanel.MediaDetailComponent;
import com.rmp.widget.components.playlistPanel.PlaylistPanelBuilder;
import com.rmp.widget.components.playlistPanel.PlaylistPanelComponent;
import com.rmp.widget.controls.panels.GradientJPanel;
import com.rmp.widget.skins.PairColor;
import com.rmp.widget.skins.RMPSkin;
import com.rmp.widget.skins.SkinFactory;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

/**
 * Provides the root panel of widget
 */
public class RootPanelComponent {

    @Getter
    private JPanel rootPanel;

    private final RMPSkin skin;

    /**
     * Initialize new instance of {@link RootPanelComponent}
     */
    RootPanelComponent() {
        this.skin = SkinFactory.getRMPSkin();
        this.rootPanel = new GradientJPanel(
                new PairColor(this.skin.getBackgroundStartColor(), this.skin.getBackgroundEndColor())
        );
    }

    /**
     * Initializes the root panel
     */
    void initialize(PlaylistPanelBuilder playlistPanelBuilder,
                    ControlPanelBuilder controlPanelBuilder,
                    MediaDetailBuilder mediaDetailBuilder) {
        this.rootPanel.setLayout(new BorderLayout());

        PlaylistPanelComponent playlistPanelComponent = playlistPanelBuilder
                .setPlaylistPanelSize(
                        new Dimension(
                                (int)skin.getSize().getWidth() / 100 * 35,
                                (int)skin.getSize().getHeight()
                        )
                )
                .build();

        ControlPanelComponent controlPanelComponent = controlPanelBuilder.build();
        MediaDetailComponent mediaDetailComponent = mediaDetailBuilder.build();

        this.rootPanel.add(mediaDetailComponent.getMediaDetailPanel(), BorderLayout.CENTER);
        this.rootPanel.add(playlistPanelComponent.getPlaylistPanel(), BorderLayout.LINE_END);
        this.rootPanel.add(controlPanelComponent.getControlPanel(), BorderLayout.PAGE_END);
    }

}
