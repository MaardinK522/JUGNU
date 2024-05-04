package org.mkproductions.jugnu.utilities;

import org.mkproductions.jugnu.entities.ClassData;
import org.mkproductions.jugnu.graphics.JUGNUFrame;

import java.awt.*;
import java.util.ArrayList;

public class ClassesDataRender {
    private final ArrayList<ClassData> classesData;
    private int posX;
    private int posY;
    public static int containerGaps = 50;

    public ClassesDataRender(int x, int y, ArrayList<ClassData> classesData) {
        this.posX = x;
        this.posY = y;
        this.classesData = classesData;
    }

    public void renderData(Graphics2D g) {
        if (JUGNUFrame.UP) posY--;
        else if (JUGNUFrame.DOWN) posY++;
        if (JUGNUFrame.LEFT) posX--;
        else if (JUGNUFrame.RIGHT) posX++;
        g.setColor(Color.white);
        for (int classIndex = 0; classIndex < this.classesData.size(); classIndex++) {
            ClassData classData = this.classesData.get(classIndex);
            int x = posX;
            int y = posY;
            if (classIndex != 0) {
                x = this.posX + this.classesData.get(classIndex - 1).getClassContainerWidth() + containerGaps;
            }
            // Rendering class Rects
            g.drawRoundRect(x, y, classData.getClassContainerWidth(), classData.getClassContainerHeight(), 20, 20);
            drawCenteredString(g, classData.getClassName(), x + classData.getClassContainerWidth() / 2, y + classData.padding / 2);
            y += classData.padding;
            g.drawLine(x, y, x + classData.getClassContainerWidth(), y);
            // Rendering the variable heading.
            y += 20;
            drawTopLeftString(g, "Variables:", x + classData.padding / 4, y);
            // Rendering the variables in a columns.
            int f;
            for (f = 0; f < classData.getFields().size(); f++) {
                drawTopLeftString(g, (f + 1) + ") " + classData.getFields().get(f).getName(), (int) (x + classData.padding / 4f), (int) (y + ((f + 1) * classData.getClassNameHeight())));
            }
            // Rendering the methods in a columns.
            y += (int) ((f + 1) * classData.getClassNameHeight()) + 20;
            drawTopLeftString(g, "Methods:", x + classData.padding / 4, y);
            for (f = 0; f < classData.getFields().size(); f++) {
                drawTopLeftString(g, (f + 1) + ") " + classData.getMethods().get(f).getName(), (int) (x + classData.padding / 4f), (int) (y + ((f + 1) * classData.getClassNameHeight())));
            }
        }
    }

    private void drawCenteredString(Graphics2D g2d, String text, int xCenter, int yCenter) {
        FontMetrics metrics = g2d.getFontMetrics();
        int ascent = metrics.getAscent();
        int textWidth = metrics.stringWidth(text);
        int xOffset = xCenter - textWidth / 2;
        int yOffset = yCenter + ascent / 2;
        g2d.drawString(text, xOffset, yOffset);
    }

    private void drawTopLeftString(Graphics2D g2d, String text, int x, int y) {
        FontMetrics metrics = g2d.getFontMetrics();
        int ascent = metrics.getAscent();
        int yCoordinate = y + ascent;
        g2d.drawString(text, x, yCoordinate);
    }
}
