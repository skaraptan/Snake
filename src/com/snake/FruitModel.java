package com.snake;

import java.util.Random;

/**
 * Created by Yoga2pro on 29.05.2016.
 */
public class FruitModel {
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

    public FruitModel(int fieldHeight, int fieldWidth){
        this.fieldHeight = fieldHeight-1;
        this.fieldWidth = fieldWidth-1;
        generateNewApple();
    }

    public void generateNewApple(){
        appleCoordinateX = random.nextInt(fieldWidth);
        appleCoordinateY = random.nextInt(fieldHeight);
    }

}
