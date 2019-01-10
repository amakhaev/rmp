package com.rmp.widget.components.mediaDetailPanel;

import com.rmp.widget.controls.roundPanel.RoundPanel;
import com.rmp.widget.utilities.LocalizationUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Provides the panel that displayed title of media
 */
class MediaTitlePanel extends RoundPanel {

    private JLabel label;

    /**
     * Initialize new instance of {@link MediaTitlePanel}
     */
    MediaTitlePanel() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.label = new JLabel();
        this.add(this.label, FlowLayout.LEFT);
    }

    /**
     * Sets the foreground color of label
     *
     * @param foregroundColor - the foreground color
     */
    void setLabelForegroundColor(Color foregroundColor) {
        this.label.setForeground(foregroundColor);
    }

    /**
     * Sets the value of title label
     *
     * @param title - the title value
     */
    void setTitle(String title) {
        this.label.setText(
                title == null || title.isEmpty() ? LocalizationUtils.getString("not_selected") : title
        );
        this.repaint();
    }
}
