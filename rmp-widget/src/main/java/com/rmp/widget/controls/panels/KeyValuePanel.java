package com.rmp.widget.controls.panels;

import com.rmp.widget.skins.Colors;

import javax.swing.*;
import java.awt.*;

/**
 * Provides the panel that shown two labels key-value pair.
 */
public class KeyValuePanel extends JPanel {

    private Color DEFAULT_BACKGROUND_COLOR = Colors.BLACK;
    private Color DEFAULT_KEY_LABEL_FOREGROUND_COLOR = Colors.PRIMARY;
    private Color DEFAULT_VALUE_LABEL_FOREGROUND_COLOR = Colors.PRIMARY;

    private JLabel keyLabel;
    private JLabel valueLabel;



    /**
     * Initialize new instance of {@link KeyValuePanel}
     */
    public KeyValuePanel() {
        this.initialize();
    }

    /**
     * Sets the value of key label
     */
    public void setKeyValue(String value) {
        this.keyLabel.setText(value);
    }

    /**
     * Sets the value label
     */
    public void setValue(String value) {
        this.valueLabel.setText(value);
    }

    /**
     * Sets the color of key label
     *
     * @param color - the foreground color to set
     */
    public void setKeyLabelForegroundColor(Color color) {
        this.keyLabel.setForeground(color);
    }

    /**
     * Sets the color of value label
     *
     * @param color - the foreground color to set
     */
    public void setValueLabelForegroundColor(Color color) {
        this.valueLabel.setForeground(color);
    }

    private void initialize() {
        this.setBackground(DEFAULT_BACKGROUND_COLOR);
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(5, 5,5, 5));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.keyLabel = new JLabel();
        this.keyLabel.setForeground(DEFAULT_KEY_LABEL_FOREGROUND_COLOR);
        this.add(this.keyLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.valueLabel = new JLabel();
        this.valueLabel.setForeground(DEFAULT_VALUE_LABEL_FOREGROUND_COLOR);
        this.add(this.valueLabel, gbc);
    }

}
