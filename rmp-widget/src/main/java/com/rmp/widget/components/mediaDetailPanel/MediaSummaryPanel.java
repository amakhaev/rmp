package com.rmp.widget.components.mediaDetailPanel;

import com.rmp.widget.controls.panels.KeyValuePanel;
import com.rmp.widget.controls.panels.RoundPanel;
import com.rmp.widget.utilities.LocalizationUtils;

import java.awt.*;

class MediaSummaryPanel extends RoundPanel {

    private KeyValuePanel totalCountPanel;
    private KeyValuePanel selectedTrackPanel;

    /**
     * Initialize new instance of {@link MediaSummaryPanel}
     */
    MediaSummaryPanel() {
        this.initialize();
    }

    private void initialize() {
        this.setBackground(Color.BLACK);

        this.totalCountPanel = this.createTotalCountPanel();
        this.selectedTrackPanel = this.createSelectedItemIndexPanel();

        this.add(this.totalCountPanel);
        this.add(this.selectedTrackPanel);
    }

    /**
     * Sets the colors of total count panel
     */
    void setTotalCountColors(Color background, Color keyLabelForeground, Color valueLabelForeground) {
        this.totalCountPanel.setBackground(background);
        this.totalCountPanel.setKeyLabelForegroundColor(keyLabelForeground);
        this.totalCountPanel.setValueLabelForegroundColor(valueLabelForeground);
    }

    /**
     * Sets the colors of selected track panel
     */
    void setSelectedTrackColors(Color background, Color keyLabelForeground, Color valueLabelForeground) {
        this.selectedTrackPanel.setBackground(background);
        this.selectedTrackPanel.setKeyLabelForegroundColor(keyLabelForeground);
        this.selectedTrackPanel.setValueLabelForegroundColor(valueLabelForeground);
    }

    /**
     * Sets the value of total count
     *
     * @param totalCount - the value
     */
    void setTotalCountValue(int totalCount) {
        this.totalCountPanel.setValue(String.format("%02d", totalCount));
    }

    /**
     * Sets the value of selected track
     *
     * @param selectedTrack - the value
     */
    void setSelectedTrackValue(int selectedTrack) {
        this.selectedTrackPanel.setValue(String.format("%02d", selectedTrack));
    }

    private KeyValuePanel createTotalCountPanel() {
        KeyValuePanel totalCountPanel = new KeyValuePanel();
        totalCountPanel.setKeyValue(LocalizationUtils.getString("count"));
        totalCountPanel.setValue(String.format("%02d", 0));

        return totalCountPanel;
    }

    private KeyValuePanel createSelectedItemIndexPanel() {
        KeyValuePanel selectedTrackPanel = new KeyValuePanel();
        selectedTrackPanel.setKeyValue(LocalizationUtils.getString("track"));
        selectedTrackPanel.setValue(String.format("%02d", 0));

        return selectedTrackPanel;
    }
}
