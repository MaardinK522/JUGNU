package org.mkproductions.jugnu.entities;

import javax.swing.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class ClassData {
    private final String className;
    private final ArrayList<Constructor<?>> constructors;
    private final ArrayList<Field> fields;
    private final ArrayList<Method> methods;
    private int classContainerWidth = 0;
    private int classContainerHeight = 0;
    public int padding = 50;

    public ClassData(String className) {
        this.className = className;
        this.constructors = new ArrayList<>();
        this.fields = new ArrayList<>();
        this.methods = new ArrayList<>();
    }

    public ClassData(String className, Constructor<?>[] constructors, Field[] fields, Method[] methods) {
        this.className = className;
        this.constructors = new ArrayList<>();
        this.constructors.addAll(Arrays.asList(constructors));
        this.fields = new ArrayList<>();
        this.fields.addAll(Arrays.asList(fields));
        this.methods = new ArrayList<>();
        this.methods.addAll(Arrays.asList(methods));
        resetContainerSize();
    }

    public void addConstructor(Constructor<?>... constructors) {
        this.constructors.addAll(Arrays.asList(constructors));
        resetContainerSize();
    }

    public void addMethod(Method... methods) {
        this.methods.addAll(Arrays.asList(methods));
        resetContainerSize();
    }

    public void addField(Field... fields) {
        this.fields.addAll(Arrays.asList(fields));
        resetContainerSize();
    }

    public String getClassName() {
        return className;
    }

    public ArrayList<Constructor<?>> getConstructors() {
        return constructors;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public ArrayList<Method> getMethods() {
        return methods;
    }

    public int getClassContainerWidth() {
        return classContainerWidth;
    }

    public int getClassContainerHeight() {
        return classContainerHeight;
    }

    public void resetContainerSize() {
        int maxTextWidth = 0;
        int maxTextHeight = 0;
        JLabel label = new JLabel(className);
        maxTextWidth = label.getWidth();
        maxTextHeight += label.getHeight();
        for (Constructor<?> constructor : this.constructors) {
            label = new JLabel(constructor.getName());
            if (maxTextWidth < label.getPreferredSize().width) maxTextWidth = label.getPreferredSize().width;
            maxTextHeight += label.getPreferredSize().height;
        }
        for (Field field : this.fields) {
            label = new JLabel(field.getName());
            if (maxTextWidth < label.getPreferredSize().width) maxTextWidth = label.getPreferredSize().width;
            maxTextHeight += label.getPreferredSize().height;
        }
        for (Method method : this.methods) {
            label = new JLabel(method.getName());
            if (maxTextWidth < label.getPreferredSize().width) maxTextWidth = label.getPreferredSize().width;
            maxTextHeight += label.getPreferredSize().height;
        }
        this.classContainerHeight = maxTextHeight + this.padding * 2;
        this.classContainerWidth = maxTextWidth + this.padding * 2;
    }

    public float getClassNamePosX() {
        JLabel label = new JLabel(className);
        return (getClassContainerWidth() - label.getPreferredSize().width) / 2f;
    }

    public float getClassNameHeight() {
        JLabel label = new JLabel(className);
        return label.getPreferredSize().height;
    }

    public int getClassNameWidth() {
        JLabel label = new JLabel(className);
        return label.getPreferredSize().width;
    }
}