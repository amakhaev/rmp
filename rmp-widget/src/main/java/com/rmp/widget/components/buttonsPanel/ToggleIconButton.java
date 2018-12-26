package com.rmp.widget.components.buttonsPanel;

import com.rmp.widget.controls.button.IconButton;
import lombok.Getter;

import java.net.URL;

/**
 * Provides toggle icon button that changes icons by toggle
 */
public final class ToggleIconButton {

    private final URL firstIconName;
    private final URL firstPressedIconName;
    private final URL secondIconName;
    private final URL secondPressedIconName;

    @Getter
    private final IconButton iconButton;

    /**
     * Initialize new instance of {@link ToggleIconButton}
     */
    ToggleIconButton(int radius,
                     URL firstIconName,
                     URL firstPressedIconName,
                     URL secondIconName,
                     URL secondPressedIconName) {
        this.firstIconName = firstIconName;
        this.firstPressedIconName = firstPressedIconName;
        this.secondIconName = secondIconName;
        this.secondPressedIconName = secondPressedIconName;
        this.iconButton = new IconButton(radius, firstIconName, firstPressedIconName);
    }

    /**
     * Toggles the state of icon button
     */
    void change(boolean isFirstIconSelected) {
        if (isFirstIconSelected) {
            this.iconButton.changeIcon(this.firstIconName, this.firstPressedIconName);
        } else {
            this.iconButton.changeIcon(this.secondIconName, this.secondPressedIconName);
        }
    }
}
