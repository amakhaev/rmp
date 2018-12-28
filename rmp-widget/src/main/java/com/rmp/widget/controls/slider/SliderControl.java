package com.rmp.widget.controls.slider;

import com.rmp.widget.skins.Colors;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.function.Function;

/**
 * Provides the control that extends base slider
 */
@Slf4j
public class SliderControl extends JComponent {

    private static final Dimension MIN_PREFERENCE_SIZE = new Dimension(50, 30);

    private static final Dimension CURSOR_SIZE = new Dimension(20, 15);

    private static final int SLIDER_HEIGHT = 10;

    private static final Color[] CURSOR_GRADIENT_COLORS = new Color[] { new Color(41,50,60), Color.LIGHT_GRAY };

    @Setter
    private Color sliderBackgroundColor = Colors.BLACK;

    @Setter
    private Color sliderBorderColor = Colors.PRIMARY_GRAY;

    @Setter
    private Color cursorShadowColor = Colors.IRON;

    @Setter
    private Color sliderShadowColor = Colors.PRIMARY_GRAY;

    private Point cursorPosition;
    private boolean isCursorCapturedByMouse;
    private Delimiter delimiter;

    @Setter
    private Function<Integer, Void> changeValueListener;

    /**
     * Initialize new instance of {@link SliderControl}
     */
    public SliderControl() {
        try {
            this.delimiter = new Delimiter(100, 0, 50);
        } catch (IllegalAccessException e) {
            log.error(e.getMessage());
        }

        this.setPreferredSize(MIN_PREFERENCE_SIZE);
        this.calculateCursorPosition();
        this.addMouseMotionListener(this.createMouseListener());
        this.addMouseListener(this.createMouseListener());
    }

    @Override
    public void paintComponent(Graphics graphics) {
        this.drawShadow(graphics);
        this.drawSlider(graphics);
        this.drawSliderCursor(graphics);
    }

    @Override
    public void setPreferredSize(Dimension dimension) {
        dimension.width = dimension.width < MIN_PREFERENCE_SIZE.width ? MIN_PREFERENCE_SIZE.width : dimension.width;
        dimension.height = dimension.height < MIN_PREFERENCE_SIZE.height ? MIN_PREFERENCE_SIZE.height : dimension.height;
        super.setPreferredSize(dimension);

        this.calculateCursorPosition();
    }

    /**
     * Sets the max value of delimiter
     */
    public void setDelimiterMax(int max) {
        if (this.isCursorCapturedByMouse || this.delimiter.maxValue == max) {
            return;
        }

        if (max < this.delimiter.minValue) {
            throw new IllegalArgumentException("The max value must be more than min");
        }
        this.delimiter.maxValue = max;

        this.calculateCursorPosition();
    }

    /**
     * Sets the min value of delimiter
     */
    public void setDelimiterMin(int min) {
        if (this.isCursorCapturedByMouse || this.delimiter.minValue == min) {
            return;
        }

        if (min > this.delimiter.maxValue) {
            throw new IllegalArgumentException("The min value must be less than man");
        }
        this.delimiter.minValue = min;
        this.calculateCursorPosition();
    }

    /**
     * Sets the current value of delimiter
     */
    public void setDelimiterValue(int value) {
        if (this.isCursorCapturedByMouse || this.delimiter.currentValue == value) {
            return;
        }

        if (value < this.delimiter.minValue || value > this.delimiter.maxValue) {
            throw new IllegalArgumentException("The value must be between min and max");
        }
        this.delimiter.currentValue = value;
        this.calculateCursorPosition();
    }

    private void drawShadow(Graphics graphics) {
        graphics.setColor(this.cursorShadowColor);
        graphics.fillRect(0, this.cursorPosition.y, this.getPreferredSize().width, CURSOR_SIZE.height);
    }

    private void drawSlider(Graphics graphics) {
        Dimension arcs = new Dimension(8,8);
        Graphics2D graphics2d = (Graphics2D) graphics;
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int startY = this.getPreferredSize().height / 2 - SLIDER_HEIGHT / 2;

        //paint background
        graphics2d.setColor(this.sliderBackgroundColor);
        graphics2d.fillRoundRect(
                0,
                startY,
                this.getPreferredSize().width-1,
                SLIDER_HEIGHT-1,
                arcs.width,
                arcs.height
        );

        graphics2d.setColor(this.sliderShadowColor);
        graphics2d.fillRoundRect(
                0,
                startY,
                this.cursorPosition.x + 5,
                SLIDER_HEIGHT-1,
                arcs.width,
                arcs.height
        );

        //paint border
        graphics2d.setColor(this.sliderBorderColor);
        graphics2d.drawRoundRect(
                0,
                startY,
                this.getPreferredSize().width - 1,
                SLIDER_HEIGHT - 1,
                arcs.width,
                arcs.height
        );
    }

    private void drawSliderCursor(Graphics graphics) {
        Dimension arcs = new Dimension(10,10);
        Graphics2D graphics2d = (Graphics2D) graphics;
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        RadialGradientPaint gp = new RadialGradientPaint(
                this.cursorPosition.x + CURSOR_SIZE.width / 2,
                this.cursorPosition.y + CURSOR_SIZE.height / 2,
                10,
                new float[] { 0.2f, 1.0f },
                CURSOR_GRADIENT_COLORS
        );
        graphics2d.setPaint(gp);
        //Draws the rounded opaque panel with borders.
        graphics2d.fillRoundRect(
                this.cursorPosition.x,
                this.cursorPosition.y,
                CURSOR_SIZE.width - 1,
                CURSOR_SIZE.height - 1,
                arcs.width,
                arcs.height
        );
    }

    private MouseAdapter createMouseListener() {
        return new MouseAdapter() {
            private Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
            private Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);

            private Cursor currentCursor = this.defaultCursor;

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mousePressed(mouseEvent);
                calculateCursorPositionDependsOnMousePosition(mouseEvent.getX(), mouseEvent.getY());
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                super.mousePressed(mouseEvent);
                isCursorCapturedByMouse = isMouseOverSlider(mouseEvent.getX(), mouseEvent.getY());
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                super.mousePressed(mouseEvent);
                if (isCursorCapturedByMouse) {
                    isCursorCapturedByMouse = false;
                }
            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                if (isMouseOverSlider(mouseEvent.getX(), mouseEvent.getY())) {
                    if (this.currentCursor.getType() != Cursor.HAND_CURSOR) {
                        this.currentCursor = this.handCursor;
                        SliderControl.this.setCursor(this.currentCursor);
                    }
                } else if (this.currentCursor.getType() != Cursor.DEFAULT_CURSOR) {
                    this.currentCursor = this.defaultCursor;
                    SliderControl.this.setCursor(this.currentCursor);
                }
            }

            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                if (isCursorCapturedByMouse) {
                    calculateCursorPositionDependsOnMousePosition(mouseEvent.getX(), mouseEvent.getY());
                }
            }
        };
    }

    private boolean isMouseOverSlider(int mouseX, int mouseY) {
        int startY = this.getPreferredSize().height / 2 - SLIDER_HEIGHT / 2;

        return mouseX >= 0 &&
                mouseX <= this.getPreferredSize().width &&
                mouseY >= startY &&
                mouseY <= startY + SLIDER_HEIGHT;
    }

    private void calculateCursorPosition() {
        if (this.cursorPosition == null) {
            this.cursorPosition = new Point();
        }

        this.cursorPosition.x = (int)(
                (this.getPreferredSize().width - CURSOR_SIZE.width) /
                        this.delimiter.distanceBetweenMinAndMax() *
                        this.delimiter.distanceBetweenMinAndCurrent()
        );
        this.cursorPosition.y = this.getPreferredSize().height / 2 - CURSOR_SIZE.height / 2;
        this.repaint();
    }

    private void calculateCursorPositionDependsOnMousePosition(int mouseX, int mouseY) {
        double neighboringPointsDistance = (
                (this.getPreferredSize().width - CURSOR_SIZE.width) / this.delimiter.distanceBetweenMinAndMax()
        );
        double lastPassedPoint = mouseX / neighboringPointsDistance;
        double distanceBetweenLastPointAndMouseX = mouseX - lastPassedPoint * neighboringPointsDistance;

        double changedDelimiterValue = distanceBetweenLastPointAndMouseX >= neighboringPointsDistance / 2 ?
                lastPassedPoint + 1 : lastPassedPoint;

        if (this.delimiter.currentValue != changedDelimiterValue &&
                changedDelimiterValue >= this.delimiter.minValue &&
                changedDelimiterValue <= this.delimiter.maxValue) {
            this.delimiter.currentValue = changedDelimiterValue;
            this.calculateCursorPosition();
            if (this.changeValueListener != null) {
                this.changeValueListener.apply((int)this.delimiter.currentValue);
            }
        }
    }

    final class Delimiter {
        private double maxValue;
        private double minValue;
        private double currentValue;

        Delimiter(double maxValue, double minValue, double currentValue) throws IllegalAccessException {
            this.maxValue = maxValue;
            this.minValue = minValue;
            this.currentValue = currentValue;

            if (this.minValue > this.maxValue) {
                throw new IllegalAccessException("Min value is more than max");
            }

            if (this.currentValue > this.maxValue || this.currentValue < this.minValue) {
                throw new IllegalAccessException("Invalid current value");
            }
        }

        double distanceBetweenMinAndMax() {
            return this.maxValue - this.minValue;
        }

        double distanceBetweenMinAndCurrent() {
            return this.currentValue - this.minValue;
        }
    }
}
