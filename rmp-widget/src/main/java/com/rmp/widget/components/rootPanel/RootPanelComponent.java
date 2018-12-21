package com.rmp.widget.components.rootPanel;

import com.rmp.widget.components.buttonsPanel.ButtonPanelComponent;
import com.rmp.widget.components.buttonsPanel.ButtonPanelBuilder;
import com.rmp.widget.components.playlistPanel.PlaylistPanelBuilder;
import com.rmp.widget.components.playlistPanel.PlaylistPanelComponent;
import com.rmp.widget.controls.gradientPanel.GradientJPanel;
import com.rmp.widget.dataWatcher.PlaylistDataWatcher;
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
     * @param playlistPanelBuilder - the playlist panel builder
     */
    RootPanelComponent(RMPSkin skin, PlaylistPanelBuilder playlistPanelBuilder) {
        this.skin = skin;
        this.rootPanel = new GradientJPanel(this.skin.getBackgroundStartColor(), this.skin.getBackgroundEndColor());
        this.initialize(playlistPanelBuilder);
    }

    private void initialize(PlaylistPanelBuilder playlistPanelBuilder) {
        this.rootPanel.setLayout(new BorderLayout());

        PlaylistPanelComponent playlistPanelComponent = playlistPanelBuilder
                .setSkin(this.skin.getPlaylistSkin())
                .setPlaylistPanelSize(
                        new Dimension(
                                (int)skin.getSize().getWidth() / 100 * 30,
                                (int)skin.getSize().getHeight()
                        )
                )
                .build();

        ButtonPanelComponent buttonPanelComponent = new ButtonPanelBuilder()
                .setSkin(this.skin.getButtonPanelSkin())
                .build();

        this.rootPanel.add(playlistPanelComponent.getPlaylistPanel(), BorderLayout.LINE_END);
        this.rootPanel.add(buttonPanelComponent.getButtonPanel(), BorderLayout.PAGE_END);
    }

}
