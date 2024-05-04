package org.mkproductions.jugnu.demos;

import org.mkproductions.jugnu.entities.*;

@ClassifyAbleClass
public class Data {
    @ClassifyAbleField
    private int intData;
    private float floatData;
    private double doubleData;
    private String stringData;
    @ClassifyAbleField
    private char charData;

    @ClassifyAbleConstructor(key = "user_data")
    public Data() {
        this.intData = 0;
        this.floatData = 0.f;
        this.doubleData = 0.0;
        this.stringData = "";
        this.charData = ' ';
    }

    public Data(int intData, float floatData, double doubleData, String stringData, char charData) {
        this.intData = intData;
        this.floatData = floatData;
        this.doubleData = doubleData;
        this.stringData = stringData;
        this.charData = charData;
    }

    public int getIntData() {
        return intData;
    }

    @ClassifyAbleMethod
    public void setIntData(int intData) {
        this.intData = intData;
    }

    public float getFloatData() {
        return floatData;
    }

    @ClassifyAbleMethod
    public void setFloatData(float floatData) {
        this.floatData = floatData;
    }

    public double getDoubleData() {
        return doubleData;
    }

    @ClassifyAbleMethod
    public void setDoubleData(double doubleData) {
        this.doubleData = doubleData;
    }

    public String getStringData() {
        return stringData;
    }

    @ClassifyAbleMethod
    public void setStringData(String stringData) {
        this.stringData = stringData;
    }

    public char getCharData() {
        return charData;
    }

    @ClassifyAbleMethod
    public void setCharData(char charData) {
        this.charData = charData;
    }
}
