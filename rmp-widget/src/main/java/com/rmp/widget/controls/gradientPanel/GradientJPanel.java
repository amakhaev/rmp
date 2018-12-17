package com.rmp.widget.controls.gradientPanel;

import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.*;

/**
 * Provides the gradient panel
 */
@AllArgsConstructor
public class GradientJPanel extends JPanel {

    private Color startColor;
    private Color endColor;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        GradientPaint gp = new GradientPaint(0, 0, this.startColor, 0, h, this.endColor);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }

}
