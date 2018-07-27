package com.testproject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PainterTest {
    private Painter painter = new Painter();

    @Before
    public void setCanvas(){
        painter.prepareCanvas(20,5);
    }

    @Test
    public void prepareCanvas() {
        //assert size of canvas
        assertEquals(7,painter.getCanvas().length);
        assertEquals(22,painter.getCanvas()[0].length);

        //assert char in canvas
        assertEquals(' ', painter.getCanvas()[5][5]);
        assertEquals('-', painter.getCanvas()[0][0]);
        assertEquals('|', painter.getCanvas()[1][0]);
    }

    @Test
    public void drawLineOnCanvas() {
        painter.drawLineOnCanvas(1,3,7,3);
        assertEquals('x',painter.getCanvas()[3][2]);
        //assert painter does not draw on border
        assertEquals('|',painter.getCanvas()[3][0]);
    }

    @Test
    public void drawRectangleOnCanvas() {
        painter.drawRectangleOnCanvas(15,2,20,5);
        assertEquals('x',painter.getCanvas()[3][20]);
        //inside rectangle is blank
        assertEquals(' ',painter.getCanvas()[3][19]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void drawDiagonalFail(){
        painter.drawLineOnCanvas(1,3,7,2);
    }
}