package com.rmp.widget.skins;

import java.awt.*;

/**
 * Provides the skin of {@link com.rmp.widget.RMPWidget}
 */
public interface RMPSkin {

    /**
     * Gets the size of widget
     *
     * @return the {@link Dimension} of widget
     */
    Dimension getSize();

    /**
     * Gets the background start color of widget
     */
    Color getBackgroundStartColor();

    /**
     * Gets the background end color of widget
     */
    Color getBackgroundEndColor();

    /**
     * Gets the button panel skin
     */
    ButtonPanelSkin getButtonPanelSkin();
}
