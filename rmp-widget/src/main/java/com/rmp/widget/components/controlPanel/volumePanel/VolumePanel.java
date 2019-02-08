package com.rmp.widget.components.controlPanel.volumePanel;

import com.rmp.widget.controls.button.IconButton;
import com.rmp.widget.controls.slider.SliderControl;
import com.rmp.widget.skins.Colors;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

/**
 * Provides the volume panel
 */
public class VolumePanel extends JPanel {

    private final URL iconUrl;
    private final URL iconDisabledUrl;
    private SliderControl sliderControl;
    private IconButton volumeIcon;

    @Setter
    private VolumeChangeListener changeListener;

    @Setter
    private ToggleMuteListener toggleMuteListener;

    /**
     * Initialize new instance of {@link VolumePanel}
     */
    public VolumePanel(URL iconUrl, URL iconDisabledUrl) {
        this.iconUrl = iconUrl;
        this.iconDisabledUrl = iconDisabledUrl;
        this.setOpaque(false);
    }

    /**
     * Initialize the component
     */
    public void initialize() {
        this.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));

        this.sliderControl = this.createVolumeControl();
        this.volumeIcon = new IconButton(32, this.iconUrl, this.iconUrl);
        this.volumeIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if (toggleMuteListener != null) {
                    toggleMuteListener.toggle();
                }
            }
        });

        this.add(this.volumeIcon);
        this.add(this.sliderControl);
    }

    /**
     * Sets the volume value
     *
     * @param value - the value of volume
     */
    public void setVolumeValue(int value) {
        if (value == 0) {
            this.volumeIcon.changeIcon(this.iconDisabledUrl, this.iconDisabledUrl);
        } else {
            this.volumeIcon.changeIcon(this.iconUrl, this.iconUrl);
        }

        this.sliderControl.setDelimiterValue(value);
    }

    /**
     * Sets the mute state
     *
     * @param isMuted - indicates when volume is muted
     */
    public void setMute(boolean isMuted) {
        if (isMuted) {
            this.volumeIcon.changeIcon(this.iconDisabledUrl, this.iconDisabledUrl);
        } else {
            this.volumeIcon.changeIcon(this.iconUrl, this.iconUrl);
        }
    }

    private SliderControl createVolumeControl() {
        SliderControl sliderControl = new SliderControl();
        sliderControl.setBackgroundHeight(6);
        sliderControl.setCursorSize(new Dimension(12, 8));
        sliderControl.setPreferredSize(new Dimension(80, 0));
        sliderControl.setCursorGradientColor(new Color[] {Colors.PRIMARY, Colors.PRIMARY_CANCEL });
        sliderControl.setSliderShadowColor(Colors.BLACK);
        sliderControl.setDelimiterMax(100);
        sliderControl.setChangeValueListener(value -> {
            if (this.changeListener != null) {
                this.changeListener.onChanged(value);
            }
            return null;
        });

        return sliderControl;
    }
}
