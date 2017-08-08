package com.prolificinteractive.materialcalendarview.spans;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;

/**
 * Span to draw a dot centered under a section of text
 */
public class DotSpan implements LineBackgroundSpan {

    /**
     * Default radius used
     */
    public static final float DEFAULT_RADIUS = 3;

    private final float radius;
    private final int color;
    private final int count;
    private final int index;

    /**
     * Create a span to draw a dot using default radius and color
     *
     * @see #DotSpan(float, int, int, int)
     * @see #DEFAULT_RADIUS
     */
    public DotSpan() {
        this.radius = DEFAULT_RADIUS;
        this.color = 0;
        this.count = 1;
        this.index = 0;
    }

    /**
     * Create a span to draw a dot using a specified color
     *
     * @param color color of the dot
     * @see #DotSpan(float, int, int, int)
     * @see #DEFAULT_RADIUS
     */
    public DotSpan(int color) {
        this.radius = DEFAULT_RADIUS;
        this.color = color;
        this.count = 1;
        this.index = 0;
    }

    /**
     * Create a span to draw a dot using a specified radius
     *
     * @param radius radius for the dot
     * @see #DotSpan(float, int, int, int)
     */
    public DotSpan(float radius) {
        this.radius = radius;
        this.color = 0;
        this.count = 1;
        this.index = 0;
    }

    /**
     * Create a span to draw a dot using a specified radius and color
     *
     * @param radius radius for the dot
     * @param color  color of the dot
     */
    public DotSpan(float radius, int color, int count, int index) {
        this.radius = radius;
        this.color = color;
        this.count = count;
        this.index = index;
    }

    @Override
    public void drawBackground(
            Canvas canvas, Paint paint,
            int left, int right, int top, int baseline, int bottom,
            CharSequence charSequence,
            int start, int end, int lineNum
    ) {
        int oldColor = paint.getColor();
        if (color != 0) {
            paint.setColor(color);
        }
        int middle = (left + right) / 2;
        int quarter = middle / 2;
        int x = quarter + (middle / (count + 1) * (index + 1));
        canvas.drawCircle(x, bottom + radius, radius, paint);
        paint.setColor(oldColor);
    }
}
