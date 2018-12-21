package com.rmp.widget.components.buttonsPanel;

import com.rmp.widget.controls.button.IconButton;
import com.rmp.widget.skins.ButtonPanelSkin;
import com.rmp.widget.skins.Colors;
import lombok.Getter;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

/**
 * Provides the buttons panel of widget
 */
public class ButtonPanelComponent {

    private ButtonPanelSkin skin;

    @Getter
    private JPanel buttonPanel;

    /**
     * Initialize new instance of {@link ButtonPanelComponent}
     */
    ButtonPanelComponent(ButtonPanelSkin skin) {
        this.skin = skin;

        this.buttonPanel = new JPanel();
        this.buttonPanel.setBackground(Colors.TRANSPARENT);

        this.initializeButtons();
    }

    private void initializeButtons() {
        this.buttonPanel.add(
                this.createButton(
                        this.skin.getStopIconUrl(),
                        this.skin.getStopPressedIconUrl(),
                        () -> System.out.println("Stop")
                )
        );

        this.buttonPanel.add(
                this.createButton(
                        this.skin.getPreviousIconUrl(),
                        this.skin.getPreviousPressedIconUrl(),
                        () -> System.out.println("Prev")
                )
        );

        this.buttonPanel.add(
                this.createButton(
                        this.skin.getPlayIconUrl(),
                        this.skin.getPlayPressedIconUrl(),
                        () -> System.out.println("Play")
                )
        );

        this.buttonPanel.add(
                this.createButton(
                        this.skin.getNextIconUrl(),
                        this.skin.getNextPressedIconUrl(),
                        () -> System.out.println("Next")
                )
        );

        this.buttonPanel.add(
                this.createButton(
                        this.skin.getConfigIconUrl(),
                        this.skin.getConfigPressedIconUrl(),
                        () -> System.out.println("Config")
                )
        );
    }

    private JButton createButton(URL iconUrl, URL iconPressedUrl, Runnable clickHandler) {
        JButton button = new IconButton(50, iconUrl, iconPressedUrl);
        button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                clickHandler.run();
            }
        });

        return button;
    }
}
