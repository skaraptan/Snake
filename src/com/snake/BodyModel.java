package com.snake;

import javax.swing.*;

/**
 * Created by Yoga2pro on 27.05.2016.
 */
public class BodyModel {




    private Direction snakeDirection = Direction.RIGHT;
    private int bodyLength = 2;
    private int snakeCoordinatesX[] = new int[100];
    private int snakeCoordinatesY[] = new int[100];
    private int width;
    private int height;

    public BodyModel(int startX, int startX1, int startY, int startY1, int width, int height){
        snakeCoordinatesX[0] = startX;
        snakeCoordinatesX[1] = startX1;
        snakeCoordinatesY[0] = startY;
        snakeCoordinatesY[1] = startY1;
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

    public int[] getSnakeCoordinatesX(){
        return snakeCoordinatesX;
    }

    public int[] getSnakeCoordinatesY(){
        return snakeCoordinatesY;
    }
    public void increaseBodyLength(){
        bodyLength++;
    }

    public void changeCoordinatesIfReqired(){
        if(snakeCoordinatesX[0]==-1 && snakeDirection==Direction.LEFT){
            snakeCoordinatesX[0]=width;
        }
        if(snakeCoordinatesX[0]==width && snakeDirection==Direction.RIGHT){
            snakeCoordinatesX[0]=0;
        }
        if(snakeCoordinatesY[0]==-1 && snakeDirection==Direction.UP){
            snakeCoordinatesY[0]=height;
        }
        if(snakeCoordinatesY[0]==height && snakeDirection==Direction.DOWN){
            snakeCoordinatesY[0]=0;
        }
    }
    public void move(){
        for(int blockId = bodyLength; blockId > 0; blockId--){
            snakeCoordinatesX[blockId] = snakeCoordinatesX[blockId-1];
            snakeCoordinatesY[blockId] = snakeCoordinatesY[blockId-1];
        }
        if(snakeDirection==Direction.RIGHT){
            snakeCoordinatesX[0]++;
        }
        if(snakeDirection==Direction.DOWN){
            snakeCoordinatesY[0]++;
        }
        if(snakeDirection==Direction.LEFT){
            snakeCoordinatesX[0]--;
        }
        if(snakeDirection==Direction.UP){
            snakeCoordinatesY[0]--;
        }
        for (int blockId = bodyLength-1; blockId > 0; blockId--){
            if (snakeCoordinatesX[0] == snakeCoordinatesX[blockId] && snakeCoordinatesY[0] == snakeCoordinatesY[blockId]){
                bodyLength = blockId;
            }
        }
        changeCoordinatesIfReqired();
    }

}
