package com.rmp.widget.components.buttonsPanel;

import com.rmp.widget.controls.button.IconButton;
import com.rmp.widget.dataWatcher.ControlPanelDataWatcher;
import com.rmp.widget.eventHandler.ControlPanelEventHandler;
import com.rmp.widget.skins.ButtonPanelSkin;
import com.rmp.widget.skins.Colors;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

/**
 * Provides the buttons panel of widget
 */
public class ButtonPanelComponent {

    private ButtonPanelSkin skin;
    private ToggleIconButton playPauseButton;

    @Setter
    private ControlPanelDataWatcher controlPanelDataWatcher;

    @Setter
    private ControlPanelEventHandler eventHandler;

    @Getter
    private JPanel buttonPanel;

    /**
     * Initialize new instance of {@link ButtonPanelComponent}
     */
    ButtonPanelComponent(ButtonPanelSkin skin) {
        this.skin = skin;

        this.buttonPanel = new JPanel();
        this.buttonPanel.setBackground(Colors.TRANSPARENT);
    }

    /**
     * Initializes the component
     */
    void initialize() {
        this.initializeButtons();
        this.subscribeToWatcherChanges();
    }

    private void initializeButtons() {
        this.buttonPanel.add(
                this.createButton(
                        this.skin.getStopIconUrl(),
                        this.skin.getStopPressedIconUrl(),
                        () -> {
                            if (this.eventHandler != null) {
                                this.eventHandler.onStop();
                            }
                        }
                )
        );

        this.buttonPanel.add(
                this.createButton(
                        this.skin.getPreviousIconUrl(),
                        this.skin.getPreviousPressedIconUrl(),
                        () -> {
                            if (this.eventHandler != null) {
                                this.eventHandler.onPrev();
                            }
                        }
                )
        );

        this.playPauseButton = this.createPlayPauseButton(
                () -> {
                    if (this.eventHandler != null) {
                        this.eventHandler.onPlay();
                    }
                }
        );
        this.buttonPanel.add(this.playPauseButton.getIconButton());

        this.buttonPanel.add(
                this.createButton(
                        this.skin.getNextIconUrl(),
                        this.skin.getNextPressedIconUrl(),
                        () -> {
                            if (this.eventHandler != null) {
                                this.eventHandler.onNext();
                            }
                        }
                )
        );

        this.buttonPanel.add(
                this.createButton(
                        this.skin.getConfigIconUrl(),
                        this.skin.getConfigPressedIconUrl(),
                        () -> {
                            if (this.eventHandler != null) {
                                this.eventHandler.onConfigOpen();
                            }
                        }
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

    private ToggleIconButton createPlayPauseButton(Runnable clickHandler) {
        ToggleIconButton toggleIconButton = new ToggleIconButton(
                50,
                this.skin.getPlayIconUrl(),
                this.skin.getPlayPressedIconUrl(),
                this.skin.getPauseIconUrl(),
                this.skin.getPausePressedIconUrl()
        );

        toggleIconButton.getIconButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                clickHandler.run();
            }
        });

        return toggleIconButton;
    }

    private void subscribeToWatcherChanges() {
        if (this.controlPanelDataWatcher == null) {
            return;
        }

        if (this.controlPanelDataWatcher.getIsPlayingObserver() != null) {
            this.controlPanelDataWatcher.getIsPlayingObserver().subscribe(isPlaying -> {
                playPauseButton.change(!isPlaying);
            });
        }
    }
}
