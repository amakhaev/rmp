package com.rmp.widget;

import com.rmp.widget.components.rootPanel.RootPanelComponent;
import com.rmp.widget.components.rootPanel.RootPanelBuilder;
import com.rmp.widget.skins.RMPSkin;
import com.rmp.widget.utilities.LocalizationUtils;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Provides the UI widget of Remote Media Player
 */
public class RMPWidget {

    private JFrame widget;

    private final RMPSkin skin;

    /**
     * Initialize new instance of {@link RMPWidget}
     *
     * @param skin - the skin that should be used when UI is shows
     */
    RMPWidget(RMPSkin skin) {
        this.widget = new JFrame(LocalizationUtils.getString("rmp_title"));
        this.skin = skin;
        this.initialize();
    }

    /**
     * Shows the widget
     */
    public void showWidget() {
        this.widget.pack();
        this.widget.setVisible(true);
    }

    private void initialize() {
        this.widget.setResizable(false);
        this.widget.setPreferredSize(this.skin.getSize());
        this.widget.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        RootPanelComponent rootPanelComponent = new RootPanelBuilder()
                .setSkin(this.skin)
                .build();
        this.widget.setContentPane(rootPanelComponent.getRootPanel());
    }

}
