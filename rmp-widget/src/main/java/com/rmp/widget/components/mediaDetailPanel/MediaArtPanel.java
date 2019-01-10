package com.rmp.widget.components.mediaDetailPanel;

import com.rmp.widget.controls.roundPanel.RoundGradientPanel;
import com.rmp.widget.skins.Colors;
import com.rmp.widget.skins.GradientPalette;
import com.rmp.widget.utilities.ImageUtility;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Provides the panel that shown the art of media item
 */
@Slf4j
public class MediaArtPanel extends RoundGradientPanel {

    private static final GradientPalette DEFAULT_PALETTE = new GradientPalette(Colors.BLACK, Colors.CHARCOAL);

    private Image defaultImage;
    private Image artImage;
    private boolean isDefaultImageLoaded;

    /**
     * Initialize new instance of {@link MediaArtPanel}
     */
    MediaArtPanel(URL defaultImageUrl) {
        super(DEFAULT_PALETTE);
        try {
            this.defaultImage = ImageIO.read(defaultImageUrl);
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        this.loadDefaultImage();
    }

    /**
     * Sets the to art
     *
     * @param image - the art image
     */
    void setArt(Image image) {
        if (image == null) {
            this.loadDefaultImage();
        } else {
            this.artImage = image;
            this.isDefaultImageLoaded = false;
        }
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        if (this.isDefaultImageLoaded) {
            this.paintDefaultImage(g);
        } else {
            this.paintCustomArt(g);
        }
    }

    private void paintDefaultImage(Graphics g) {
        int minSize = this.getWidth() > this.getHeight() ? this.getHeight() : this.getWidth();
        this.artImage = ImageUtility.getScaledImage(this.artImage, minSize, minSize);
        g.drawImage(
                this.artImage,
                this.getWidth() / 2 - minSize / 2,
                this.getHeight() / 2 - minSize / 2,
                this
        );
    }

    private void paintCustomArt(Graphics g) {
        this.artImage = ImageUtility.getScaledImage(this.artImage, this.getWidth() - 20, this.getHeight() - 20);
        g.drawImage(this.artImage, 10, 10, this);
    }

    private void loadDefaultImage() {
        this.isDefaultImageLoaded = true;
        this.artImage = this.defaultImage;
    }
}
