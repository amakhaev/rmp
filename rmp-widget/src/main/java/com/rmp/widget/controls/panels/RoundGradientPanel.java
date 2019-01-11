package com.rmp.widget.controls.panels;

import com.rmp.widget.skins.PairColor;

import javax.swing.*;
import java.awt.*;

public class RoundGradientPanel extends JPanel {

    private PairColor pairColor;

    /**
     * Initialize new instance of {@link RoundGradientPanel}
     */
    public RoundGradientPanel(PairColor pairColor) {
        this.pairColor = pairColor;
        this.setOpaque(false);
    }

    /**
     * Sets the colors of background
     */
    public void setGradient(PairColor gradient) {
        this.pairColor = gradient;
        this.repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(15,15);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        GradientPaint gp = new GradientPaint(
                0,
                0,
                this.pairColor.getFirstColor(),
                0,
                height,
                this.pairColor.getSecondColor()
        );
        graphics.setPaint(gp);
        //Draws the rounded opaque panel with borders.
        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
        graphics.setColor(getForeground());
        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
    }
}
