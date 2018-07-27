package com.testproject;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class Painter {
    private static final char BRUSH_STROKE = 'x';

    private char[][] canvas;
    private int width;
    private int height;

    public void prepareCanvas(int w, int h) {
        this.width = w + 2;
        this.height = h + 2; //according to the example given
        this.canvas = drawBorders(this.width, this.height);
    }

    /**
     * Draw the borders using - and | character and fill in the rest of the canvas with ' '.
     *
     * @param width
     * @param height
     * @return canvas
     */
    private char[][] drawBorders(int width, int height) {
        char[][] tempCanvas = new char[height][width];
        Arrays.stream(tempCanvas).forEach(chars -> Arrays.fill(chars, ' '));
        return drawLine(0, height - 1, width - 1, height - 1, '-',
                drawLine(0, 0, width - 1, 0, '-',
                        drawLine(0, 1, 0, height - 2, '|',
                                drawLine(width - 1, 1, width - 1, height - 2, '|',
                                        tempCanvas))));
    }

    public void printCanvasToConsole() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                System.out.print(this.canvas[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Helper method to draw vertical or horizontal lines in general
     * from coordinates (x1, y1) to (x2, y2).
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param pattern
     * @param canvas
     * @return modified canvas
     */
    private char[][] drawLine(int x1, int y1, int x2, int y2,
                              char pattern, char[][] canvas) {
        if (x1 != x2 && y1 != y2) {
            throw new IllegalArgumentException("cannot draw diagonal line");
        }
        for (int i = y1; i <= y2; i++) {
            for (int j = x1; j <= x2; j++) {
                if (canvas[i][j] == ' ') { //only draw on empty cell
                    canvas[i][j] = pattern;
                }
            }
        }
        return canvas;
    }

    /**
     * Draw a new line from coordinates (x1, y1) to (x2, y2) horizontally or vertically.
     * Lines are made up of the x character
     */
    public void drawLineOnCanvas(int x1, int y1, int x2, int y2) {
        this.canvas = drawLine(x1, y1, x2, y2, BRUSH_STROKE, this.canvas);
    }

    /**
     * Draw a new rectangle, with upper left corner at coordinate (x1, y1)
     * and lower right coordinate at (x2, y2).
     * Lines are made up of the x character
     */
    public void drawRectangleOnCanvas(int x1, int y1, int x2, int y2) {
        this.canvas = drawLine(x1, y1, x2, y1, BRUSH_STROKE,
                drawLine(x1, y1, x1, y2, BRUSH_STROKE,
                        drawLine(x2, y1, x2, y2, BRUSH_STROKE,
                                drawLine(x1, y2, x2, y2, BRUSH_STROKE, this.canvas))));
    }

}
