package com.snake;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Created by Yoga2pro on 27.05.2016.
 */
public class BodyModel implements Serializable{


    private Direction snakeDirection = Direction.RIGHT;


    private Coordinates[] snakeCoordinates = new Coordinates[100];
    private int bodyLength = 2;
    private int width;
    private int height;
    transient BufferedImage snakeBodyImg;

    public BufferedImage getSnakeBodyImg() {
        return snakeBodyImg;
    }

    public void setSnakeBodyImg(BufferedImage snakeBodyImg) {
        this.snakeBodyImg = snakeBodyImg;
    }

    public BodyModel(BufferedImage snakeBodyImg, int startX, int startX1, int startY, int startY1, int width, int height){
        for(int i = 0; i <100 ; i++){
            snakeCoordinates[i] = new Coordinates();
        }
        this.snakeBodyImg = snakeBodyImg;
        snakeCoordinates[0] = new Coordinates(startX, startY);
        snakeCoordinates[1] = new Coordinates(startX1, startY1);
        this.width = width;
        this.height = height;
    }
    public void setSnakeDirection(Direction snakeDirection) {
        this.snakeDirection = snakeDirection;
    }

    public Direction getSnakeDirection() {
        return snakeDirection;
    }

    public int getBodyLength(){
        return bodyLength;
    }

    public void setBodyLength(int bodyLength) {
        this.bodyLength = bodyLength;
    }

    public void setSnakeCoordinates(Coordinates[] snakeCoordinates) {
        this.snakeCoordinates = snakeCoordinates;
    }

    public Coordinates[] getSnakeCoordinates(){
        return snakeCoordinates;
    }

    public void increaseBodyLength(){
        bodyLength++;
    }

    public void changeCoordinatesIfRequired(){
        if(snakeCoordinates[0].getX()<0 && snakeDirection==Direction.LEFT){
            snakeCoordinates[0].setX(width);
        }
        if(snakeCoordinates[0].getX()>=width && snakeDirection==Direction.RIGHT){
            snakeCoordinates[0].setX(0);
        }
        if(snakeCoordinates[0].getY()<0 && snakeDirection==Direction.UP){
            snakeCoordinates[0].setY(height);
        }
        if(snakeCoordinates[0].getY()>=height && snakeDirection==Direction.DOWN){
            snakeCoordinates[0].setY(0);
        }
    }
    public void move(){

        for(int blockId = bodyLength; blockId > 0; blockId--){
            snakeCoordinates[blockId].setX(snakeCoordinates[blockId-1].getX());
            snakeCoordinates[blockId].setY(snakeCoordinates[blockId-1].getY());
        }


        if(snakeDirection==Direction.RIGHT){
            snakeCoordinates[0].setX(snakeCoordinates[0].getX()+1);
        }
        if(snakeDirection==Direction.DOWN){
            snakeCoordinates[0].setY(snakeCoordinates[0].getY()+1);
        }
        if(snakeDirection==Direction.LEFT){
            snakeCoordinates[0].setX(snakeCoordinates[0].getX()-1);
        }
        if(snakeDirection==Direction.UP){
            snakeCoordinates[0].setY(snakeCoordinates[0].getY()-1);
        }
        for (int blockId = bodyLength-1; blockId > 0; blockId--){
            if (snakeCoordinates[0].getX() == snakeCoordinates[blockId].getX() && snakeCoordinates[0].getY() == snakeCoordinates[blockId].getY()){
                bodyLength = blockId;
            }
        }
        changeCoordinatesIfRequired();
    }

}
