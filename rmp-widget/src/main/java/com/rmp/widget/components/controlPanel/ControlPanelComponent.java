package com.rmp.widget.components.controlPanel;

import com.rmp.widget.components.controlPanel.timelinePanel.TimelinePanel;
import com.rmp.widget.components.controlPanel.volumePanel.ToggleMuteListener;
import com.rmp.widget.components.controlPanel.volumePanel.VolumePanel;
import com.rmp.widget.controls.button.IconButton;
import com.rmp.widget.dataWatcher.ControlPanelDataWatcher;
import com.rmp.widget.eventHandler.ControlPanelEventHandler;
import com.rmp.widget.skins.ControlPanelSkin;
import com.rmp.widget.skins.Colors;
import com.rmp.widget.skins.SkinFactory;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

/**
 * Provides the buttons panel of widget
 */
@Slf4j
public class ControlPanelComponent {

    private final ControlPanelSkin skin;
    private ToggleIconButton playPauseButton;
    private TimelinePanel timelinePanel;
    private VolumePanel volumePanel;

    @Setter
    private ControlPanelDataWatcher controlPanelDataWatcher;

    @Setter
    private ControlPanelEventHandler eventHandler;

    @Getter
    private JPanel controlPanel;

    /**
     * Initialize new instance of {@link ControlPanelComponent}
     */
    ControlPanelComponent() {
        this.skin = SkinFactory.getRMPSkin().getControlPanelSkin();

        this.controlPanel = new JPanel();
        this.controlPanel.setBackground(Colors.TRANSPARENT);
    }

    /**
     * Initializes the component
     */
    void initialize() {
        this.controlPanel.setLayout(new BorderLayout());

        this.controlPanel.add(this.createTimeSliderPanel(), BorderLayout.PAGE_START);
        this.controlPanel.add(this.createButtonsPanel(), BorderLayout.CENTER);

        this.subscribeToWatcherChanges();
    }

    private JPanel createTimeSliderPanel() {
        this.timelinePanel = new TimelinePanel(new Dimension(this.skin.getWidgetSize().width - 20, 30));
        this.timelinePanel.initialize();

        this.timelinePanel.setTimeLineLabelShadowColor(this.skin.getTimeLineLabelShadowColor());
        this.timelinePanel.setTimeLineLabelForegroundColor(this.skin.getTimeLineLabelForegroundColor());
        this.timelinePanel.setTimelineBackgroundColor(this.skin.getTimelineBackgroundColor());
        this.timelinePanel.setTimelineBorderColor(this.skin.getTimelineBorderColor());
        this.timelinePanel.setTimelineCursorShadowColor(this.skin.getTimelineCursorShadowColor());
        this.timelinePanel.setTimelineShadowColor(this.skin.getTimelineShadowColor());

        this.timelinePanel.setChangeListener((Integer value) -> {
            // Value in seconds. Convert to millisecond
            this.eventHandler.onTimelineValueChanged(value == null ? 0 : value * 1000);
            return null;
        });

        this.timelinePanel.setTimeLabelOrderListener(order -> {
            this.eventHandler.onTimeLabelOrderChanged(order);
        });

        return this.timelinePanel;
    }

    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Colors.TRANSPARENT);

        buttonsPanel.add(
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

        buttonsPanel.add(
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
        buttonsPanel.add(this.playPauseButton.getIconButton());

        buttonsPanel.add(
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

        buttonsPanel.add(
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


        this.volumePanel = this.createVolumePanel();
        buttonsPanel.add(this.volumePanel);

        return buttonsPanel;
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

    private VolumePanel createVolumePanel() {
        VolumePanel volumeControl = new VolumePanel(this.skin.getVolumeIconUrl(), this.skin.getVolumePressedIconUrl());
        volumeControl.initialize();
        volumeControl.setChangeListener((volumeValue) -> this.eventHandler.onVolumeChanged(volumeValue));
        volumeControl.setToggleMuteListener(() -> this.eventHandler.onMuteToggle());

        return volumeControl;
    }

    private void subscribeToWatcherChanges() {
        if (this.controlPanelDataWatcher == null) {
            return;
        }

        if (this.controlPanelDataWatcher.getIsPlayingObserver() != null) {
            this.controlPanelDataWatcher.getIsPlayingObserver().subscribe(isPlaying -> playPauseButton.change(!isPlaying));
        }

        if (this.controlPanelDataWatcher.getTimelineLengthChangedObserver() != null) {
            this.controlPanelDataWatcher.getTimelineLengthChangedObserver().subscribe(
                    length -> this.timelinePanel.setEndTime(length.intValue())
            );
        }

        if (this.controlPanelDataWatcher.getTimelineValueChangedObserver() != null) {
            this.controlPanelDataWatcher.getTimelineValueChangedObserver().subscribe(
                    value -> this.timelinePanel.setTimeValue(value.intValue())
            );
        }

        if (this.controlPanelDataWatcher.getTimeLabelOrderChangedObserver() != null) {
            this.controlPanelDataWatcher.getTimeLabelOrderChangedObserver().subscribe(
                    order -> this.timelinePanel.setTimeLabelOrder(order)
            );
        }

        if (this.controlPanelDataWatcher.getMuteChangedObserver() != null) {
            this.controlPanelDataWatcher.getMuteChangedObserver().subscribe(isMute -> {
                this.volumePanel.setMute(isMute);
            });
        }
    }
}
