package com.snake;

import java.util.Random;

/**
 * Created by Yoga2pro on 29.05.2016.
 */
public class AppleModel {
    private int appleCoordinateX;
    private int appleCoordinateY;
    private int fieldWidth;
    private int fieldHeight;
    private int fieldBaseSize;

    private Random random = new Random();

    public int getAppleCoordinateX() {
        return appleCoordinateX;
    }

    public void setAppleCoordinateX(int appleCoordinateX) {
        this.appleCoordinateX = appleCoordinateX;
    }

    public int getAppleCoordinateY() {
        return appleCoordinateY;
    }

    public void setAppleCoordinateY(int appleCoordinateY) {
        this.appleCoordinateY = appleCoordinateY;
    }

    public AppleModel(int fieldHeight, int fieldWidth){
        this.fieldHeight = fieldHeight;
        this.fieldWidth = fieldWidth;
        this.fieldBaseSize = fieldBaseSize;
        appleCoordinateX = random.nextInt(fieldWidth);
        appleCoordinateY = random.nextInt(fieldHeight);
    }

    public void generateNewApple(){
        appleCoordinateX = random.nextInt(fieldWidth);
        appleCoordinateY = random.nextInt(fieldHeight);
    }

}
