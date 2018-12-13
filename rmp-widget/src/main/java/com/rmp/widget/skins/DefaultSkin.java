package com.rmp.widget.skins;

import java.awt.*;

/**
 * Provides the default skin of widget
 */
public class DefaultSkin implements RMPSkin {

    private static final Dimension SIZE = new Dimension(600, 400);

    /**
     * Gets the size of widget
     *
     * @return the {@link Dimension} of widget
     */
    @Override
    public Dimension getSize() {
        return SIZE;
    }
}
