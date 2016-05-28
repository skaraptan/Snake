package com.snake;

/**
 * Created by Yoga2pro on 27.05.2016.
 */
public class BodyModel {
    private Direction snakeDirection=Direction.RIGHT;
    private int bodyLenght = 2;

    private int snakeCoordinatesX[] = new int[100];
    private int snakeCoordinatesY[] = new int[100];

    public int getBodyLenght(){
        return bodyLenght;
    }

    public int[] getSnakeCoordinatesX(){
        return snakeCoordinatesX;
    }

    public int[] getSnakeCoordinatesY(){
        return snakeCoordinatesY;
    }
    public BodyModel(int startX, int startX1, int startY, int startY1){
        snakeCoordinatesX[0] = startX;
        snakeCoordinatesX[1] = startX1;
        snakeCoordinatesY[0] = startY;
        snakeCoordinatesY[1] = startY1;
    }

    public void move(){
        for(int blockId = bodyLenght; blockId > 0; blockId--){
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

    }

}
