package org.mkproductions.jugnu.graphics;


import org.mkproductions.jugnu.entities.ClassData;
import org.mkproductions.jugnu.entities.ClassifyAbleClass;
import org.mkproductions.jugnu.entities.ClassifyAbleConstructor;
import org.mkproductions.jugnu.utilities.PackageReader;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class JUGNUFrame extends JFrame implements Runnable {
    private final Thread paintingThread;
    private final JUGNUPanel jugnuPanel;
    public static int mouseX = 0;
    public static int mouseY = 0;
    public static boolean mousePressed = false;
    public static boolean UP = false;
    public static boolean DOWN = false;
    public static boolean RIGHT = false;
    public static boolean LEFT = false;
    private static final int frameWidth = 1000;
    private static final int frameHeight = 1000;

    public JUGNUFrame() {
        this.paintingThread = new Thread(this);
        ArrayList<ClassData> classesData = new ArrayList<>();
        PackageReader packageReader = PackageReader.getInstance();
        for (Class<? extends ClassifyAbleClass> cls : packageReader.findAllClassesAnnotatedWith("org.mkproductions.jugnu")) {
            ClassData classData = new ClassData(cls.getName());
            // Storing all the constructors
            for (Constructor<ClassifyAbleConstructor> cons : packageReader.findAllAnnotatedConstructorsFromClass(cls))
                classData.addConstructor(cons);
            // Storing all the fields
            for (Field field : packageReader.findAllAnnotatedFieldsFromClass(cls))
                classData.addField(field);
            // Storing all the Methods
            for (Method method : packageReader.findAllAnnotatedMethodsFromClass(cls))
                classData.addMethod(method);
            classesData.add(classData);
        }

        this.jugnuPanel = new JUGNUPanel(frameWidth, frameHeight, classesData);
        add(jugnuPanel);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_UP)
                    UP = true;
                if (e.getKeyCode() == KeyEvent.VK_DOWN)
                    DOWN = true;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                    RIGHT = true;
                if (e.getKeyCode() == KeyEvent.VK_LEFT)
                    LEFT = true;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() == KeyEvent.VK_UP)
                    UP = false;
                if (e.getKeyCode() == KeyEvent.VK_DOWN)
                    DOWN = false;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                    RIGHT = false;
                if (e.getKeyCode() == KeyEvent.VK_LEFT)
                    LEFT = false;
            }
        });
        getContentPane().addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                mouseX = e.getX();
                mouseY = e.getY();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                mouseX = e.getX();
                mouseY = e.getY();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                mousePressed = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                mousePressed = false;
            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("JUGNU");
        setSize(frameWidth, frameHeight);
        setVisible(true);
        setLocationRelativeTo(null);
        this.startingRendering();
    }

    public void startingRendering() {
        this.jugnuPanel.resume();
        this.paintingThread.start();
    }

    public void stopRendering() {
        this.jugnuPanel.pauseRendering();
        this.paintingThread.interrupt();
    }


    @Override
    public void run() {
        while (this.jugnuPanel.running && !paintingThread.isInterrupted()) {
            this.repaint();
        }
    }
}
