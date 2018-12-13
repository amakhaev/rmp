package com.rmp.widget;

import com.rmp.widget.skins.RMPSkin;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Provides the UI widget of Remote Media Player
 */
public class RMPWidget extends JFrame {

    private final RMPSkin skin;

    /**
     * Initialize new instance of {@link RMPWidget}
     *
     * @param skin - the skin that should be used when UI is shows
     */
    RMPWidget(RMPSkin skin) {
        super("R-MP");
        this.skin = skin;
        this.initialize();
    }

    /**
     * Shows the widget
     */
    public void showWidget() {
        this.pack();
        this.setVisible(true);
    }

    private void initialize() {
        this.setResizable(false);
        this.setPreferredSize(this.skin.getSize());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

}
