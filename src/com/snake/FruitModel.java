package com.snake;

import java.util.Random;

/**
 * Created by Yoga2pro on 29.05.2016.
 */
public class FruitModel {
    private Coordinates appleCoordinates;
    private int fieldWidth;
    private int fieldHeight;
    private int fieldBaseSize;
    private boolean collision = false;
    private Random random = new Random();

    public Coordinates getAppleCoordinates() {
        return appleCoordinates;
    }

    public void setAppleCoordinates(Coordinates appleCoordinates) {
        this.appleCoordinates = appleCoordinates;
    }

    public FruitModel(int fieldHeight, int fieldWidth, BodyModel bodyModel){
        this.fieldHeight = fieldHeight-1;
        this.fieldWidth = fieldWidth-1;
        generateNewApple(bodyModel);
    }
    public FruitModel(int fieldHeight, int fieldWidth){
        this.fieldHeight = fieldHeight;
        this.fieldWidth = fieldWidth;
    }
    public void generateCoordinates(){
        appleCoordinates = new Coordinates(random.nextInt(fieldHeight), random.nextInt(fieldWidth));
    }

    public void generateNewApple(BodyModel bodyModel){
        generateCoordinates();
        for(int blockId = 0; blockId < bodyModel.getBodyLength(); blockId++){
            if((appleCoordinates.getX() == bodyModel.getSnakeCoordinates()[blockId].getX()) && appleCoordinates.getY() == bodyModel.getSnakeCoordinates()[blockId].getY())
                generateNewApple(bodyModel);
        }
    }

}
