package com.snake;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by Yoga2pro on 12.06.2016.
 */
/*Unused*
Deprecated/
 */
public class EnemySnakeCoordinatesReader extends Thread{
    private BodyModel enemySnakeBody;
    private ObjectInputStream objectInputStream;

    public EnemySnakeCoordinatesReader(ObjectInputStream objectInputStream, BodyModel enemySnakeBody){
        this.objectInputStream = objectInputStream;
        this.enemySnakeBody = enemySnakeBody;
    }

    @Override
    public void run(){
        while(true){
            try {
                enemySnakeBody.setSnakeCoordinates((Coordinates[]) objectInputStream.readObject());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
