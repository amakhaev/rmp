package com.rmp.widget.components.controlPanel;

import com.rmp.widget.controls.panels.RoundPanel;
import com.rmp.widget.controls.slider.SliderControl;
import com.rmp.widget.skins.Colors;
import com.rmp.widget.utilities.LocalizationUtils;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Function;

/**
 * Provides the panel that working with time line of media item
 */
class TimelinePanel extends JPanel {

    private static final Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);
    private static final Cursor HAND_CURSOR = new Cursor(Cursor.HAND_CURSOR);

    private final Dimension timelineSize;
    private SliderControl sliderControl;
    private JLabel timeLabel;
    private JPanel timeLabelPanel;
    private JPopupMenu timeLabelContextMenu;

    @Setter
    private TimeLabelOrder labelTimeOrder;

    private Color timeLineLabelShadowColor = Colors.BLACK;

    private Color timeLineLabelForegroundColor = Colors.PRIMARY_GRAY;

    @Setter
    private TimeLabelOrderListener timeLabelOrderListener;

    /**
     * Initialize new instance of {@link TimelinePanel}
     */
    TimelinePanel(Dimension timelineSize) {
        this.timelineSize = timelineSize;
        this.setBackground(Colors.TRANSPARENT);
        this.labelTimeOrder = TimeLabelOrder.ASC;
    }

    /**
     * Initializes the panel
     */
    void initialize() {
        this.sliderControl = new SliderControl();
        this.sliderControl.setPreferredSize(new Dimension(timelineSize.width - 50, 30));

        this.timeLabelPanel = this.createTimeLabelPanel();

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
            this.updateTimeLabelValue();
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

    /**
     * Sets the order of time label
     *
     * @param timeLabelOrder - the value of order
     */
    void setTimeLabelOrder(TimeLabelOrder timeLabelOrder) {
        this.labelTimeOrder = timeLabelOrder;
        updateTimeLabelValue();
    }

    private JPanel createTimeLabelPanel() {
        this.timeLabel = new JLabel("");
        this.timeLabel.setForeground(this.timeLineLabelForegroundColor);

        JPanel timeLabelPanel = new RoundPanel();
        timeLabelPanel.setBackground(this.timeLineLabelShadowColor);
        timeLabelPanel.setPreferredSize(new Dimension(50, 25));

        timeLabelPanel.add(this.timeLabel);
        this.timeLabelContextMenu = this.createTimeLabelContextMenu();

        timeLabelPanel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent var1) {
                setCursor(HAND_CURSOR);
            }

            @Override
            public void mouseExited(MouseEvent var1) {
                setCursor(DEFAULT_CURSOR);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                timeLabelContextMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });

        return timeLabelPanel;
    }

    private JPopupMenu createTimeLabelContextMenu() {
        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.add(new AbstractAction(LocalizationUtils.getString("ascending")) {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (timeLabelOrderListener != null) {
                    timeLabelOrderListener.onChanged(TimeLabelOrder.ASC);
                }
            }
        });

        popupMenu.add(new AbstractAction(LocalizationUtils.getString("descending")) {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (timeLabelOrderListener != null) {
                    timeLabelOrderListener.onChanged(TimeLabelOrder.DESC);
                }
            }
        });

        return popupMenu;
    }

    private void updateTimeLabelValue() {
        int currentTime;
        String result = "";
        if (this.labelTimeOrder == TimeLabelOrder.ASC) {
            currentTime = this.sliderControl.getDelimiterValue();
        } else {
            currentTime = this.sliderControl.getDelimiterMax() - this.sliderControl.getDelimiterValue();
            result = "-";
        }

        int minutes = currentTime / 60;
        int seconds = currentTime % 60;

        this.timeLabel.setText(String.format("%s%02d:%02d", result, minutes, seconds));
    }
}
