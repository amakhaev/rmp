package com.rmp.widget.components.controlPanel;

import com.rmp.widget.controls.roundPanel.RoundPanel;
import com.rmp.widget.controls.slider.SliderControl;
import com.rmp.widget.skins.Colors;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.function.Function;

/**
 * Provides the panel that working with time line of media item
 */
class TimelinePanel extends JPanel {

    private final Dimension timelineSize;
    private SliderControl sliderControl;
    private JLabel timeLabel;
    private JPanel timeLabelPanel;

    private Color timeLineLabelShadowColor = Colors.BLACK;

    @Setter
    private Color timeLineLabelForegroundColor = Colors.PRIMARY_GRAY;

    /**
     * Initialize new instance of {@link TimelinePanel}
     */
    TimelinePanel(Dimension timelineSize) {
        this.timelineSize = timelineSize;
        this.setBackground(Colors.TRANSPARENT);
    }

    /**
     * Initializes the panel
     */
    void initialize() {
        this.sliderControl = new SliderControl();
        this.sliderControl.setPreferredSize(new Dimension(timelineSize.width - 50, 30));

        this.timeLabel = new JLabel("");
        this.timeLabel.setForeground(this.timeLineLabelForegroundColor);

        this.timeLabelPanel = new RoundPanel();
        this.timeLabelPanel.setBackground(this.timeLineLabelShadowColor);
        this.timeLabelPanel.add(this.timeLabel);

        this.add(this.sliderControl);
        this.add(this.timeLabelPanel);
    }

    /**
     * Sets the end time of timeline
     *
     * @param endTime - the end time
     */
    void setEndTime(int endTime) {
        if (this.sliderControl != null) {
            this.sliderControl.setDelimiterMax(endTime);
        }
    }

    /**
     * Sets the current time of timeline
     *
     * @param timeValue - the time value in seconds
     */
    void setTimeValue(int timeValue) {
        if (this.sliderControl != null) {
            this.sliderControl.setDelimiterValue(timeValue);
            int minutes = timeValue / 60;
            int seconds = timeValue % 60;

            this.timeLabel.setText(
                    (minutes >= 10 ? minutes : "0" + minutes) + ":" + (seconds >= 10 ? seconds : "0" + seconds)
            );
        }
    }

    /**
     * Sets the listener of value changing
     *
     * @param changeValueListener - the callback function
     */
    void setChangeListener(Function<Integer, Void> changeValueListener) {
        this.sliderControl.setChangeValueListener(changeValueListener);
    }

    /**
     * Sets the timeline background color
     *
     * @param color - the color of timeline
     */
    void setTimelineBackgroundColor(Color color) {
        this.sliderControl.setSliderBackgroundColor(color);
    }

    /**
     * Sets the timeline border color
     *
     * @param color - the color of border
     */
    void setTimelineBorderColor(Color color) {
        this.sliderControl.setSliderBorderColor(color);
    }

    /**
     * Sets the timeline border color
     *
     * @param color - the color of border
     */
    void setTimelineCursorShadowColor(Color color) {
        this.sliderControl.setCursorShadowColor(color);
    }

    /**
     * Sets the timeline border color
     *
     * @param color - the color of border
     */
    void setTimelineShadowColor(Color color) {
        this.sliderControl.setSliderShadowColor(color);
    }

    /**
     * Sets the timeline label shadow color
     *
     * @param color - the color of shadow
     */
    void setTimeLineLabelShadowColor(Color color) {
        this.timeLineLabelShadowColor = color;
        this.timeLabelPanel.setBackground(this.timeLineLabelShadowColor);
    }

    /**
     * Sets the timeline label shadow color
     *
     * @param color - the color of shadow
     */
    void setTimeLineLabelForegroundColor(Color color) {
        this.timeLineLabelForegroundColor = color;
        this.timeLabel.setForeground(this.timeLineLabelForegroundColor);
    }
}
