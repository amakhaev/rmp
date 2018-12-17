package com.rmp.widget.controls.iconButton;

import com.rmp.widget.utilities.ImageUtility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;

/**
 * Provides the circle button
 */
public class IconButton extends JButton {

    private int radius;
    private ImageIcon imageIcon;
    private ImageIcon imagePressed;

    /**
     * Initialize new instance of {@link IconButton}
     */
    public IconButton(int radius, URL iconName, URL pressedIconName) {
        this.radius = radius;
        this.changeIcon(iconName, pressedIconName);

        this.setIcon(imageIcon);
        this.setBorder(null);
        this.setContentAreaFilled(false);
        this.setOpaque(false);
        this.setFocusable(false);

        this.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setIcon(imageIcon);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent evt) {
                setIcon(imagePressed);
            }

            @Override
            public void mouseReleased(MouseEvent evt) {
                setIcon(imageIcon);
            }
        });
    }

    /**
     * Changes the icon set for button.
     *
     * @param iconName - the icon url.
     * @param pressedIconName - the pressed icon url.
     */
    public void changeIcon(URL iconName, URL pressedIconName) {
        this.imageIcon = new ImageIcon(ImageUtility.getScaledImage(
                new ImageIcon(iconName).getImage(), this.radius, this.radius)
        );
        this.imagePressed = new ImageIcon(ImageUtility.getScaledImage(
                new ImageIcon(pressedIconName).getImage(), this.radius, this.radius)
        );
        this.setIcon(this.imageIcon);
    }
}

