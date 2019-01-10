package com.rmp.widget.controls.roundPanel;

import com.rmp.widget.skins.GradientPalette;

import javax.swing.*;
import java.awt.*;

public class RoundGradientPanel extends JPanel {

    private GradientPalette gradientPalette;

    /**
     * Initialize new instance of {@link RoundGradientPanel}
     */
    public RoundGradientPanel(GradientPalette gradientPalette) {
        this.gradientPalette = gradientPalette;
        this.setOpaque(false);
    }

    /**
     * Sets the colors of background
     */
    public void setGradient(GradientPalette gradient) {
        this.gradientPalette = gradient;
        this.repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(15,15);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        // graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        GradientPaint gp = new GradientPaint(
                0,
                0,
                this.gradientPalette.getStartColor(),
                0,
                height,
                this.gradientPalette.getEndColor()
        );
        graphics.setPaint(gp);
        //Draws the rounded opaque panel with borders.
        // graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
        graphics.setColor(getForeground());
        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
    }
}
