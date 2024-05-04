package org.mkproductions.jugnu.graphics;

import org.mkproductions.jugnu.entities.ClassData;
import org.mkproductions.jugnu.utilities.ClassesDataRender;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class JUGNUPanel extends JPanel {
    public boolean running;
    public static final long TARGET_FPS = 60;
    private final ClassesDataRender classesDataRender;

    public JUGNUPanel(int panelWidth, int panelHeight, ArrayList<ClassData> classesData) {
        this.classesDataRender = new ClassesDataRender(panelWidth / 2, panelHeight / 2, classesData);
        this.running = false;
        setSize(panelWidth, panelHeight);
        setBounds(0, 0, panelWidth, panelHeight);
        setVisible(true);
    }

    public void resume() {
        this.running = true;
    }

    public void pauseRendering() {
        this.running = false;
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        long startTime = System.nanoTime();
        Graphics2D g = (Graphics2D) graphics;
        this.draw(g);
        long frameTime = System.nanoTime() - startTime;
        g.drawString("FPS: " + (1000000000 / frameTime), 50, getHeight() - 50);
        g.drawString("Target FPS: " + TARGET_FPS, 50, getHeight() - 70);
        try {
            Thread.sleep(1000 / TARGET_FPS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void draw(Graphics2D g) {
        setBackground(Color.BLACK);
        g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        this.classesDataRender.renderData(g);
    }
}
