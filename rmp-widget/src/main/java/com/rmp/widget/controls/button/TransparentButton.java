package com.rmp.widget.controls.button;

import com.rmp.widget.skins.Colors;

import javax.swing.*;
import java.awt.*;

/**
 * Provides the transparent button
 */
public class TransparentButton extends JButton {

    public TransparentButton(String title, Color foreground, Color hover) {
        super(title);
        this.setBackground(Colors.TRANSPARENT);
        this.setForeground(foreground);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setFocusable(false);

        this.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                setForeground(hover);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setForeground(foreground);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

}

