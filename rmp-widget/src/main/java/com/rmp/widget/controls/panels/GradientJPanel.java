package com.rmp.widget.controls.panels;

import com.rmp.widget.skins.PairColor;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.*;

/**
 * Provides the gradient panel
 */
@AllArgsConstructor
public class GradientJPanel extends JPanel {

    private PairColor pairColor;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        GradientPaint gp = new GradientPaint(
                0,
                0,
                this.pairColor.getFirstColor(),
                0,
                h,
                this.pairColor.getSecondColor()
        );
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }

}
