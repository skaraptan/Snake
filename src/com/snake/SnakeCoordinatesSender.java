package com.snake;

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Yoga2pro on 04.06.2016.
 */
class SnakeCoordinatesSender extends Thread{

    private Coordinates[] snakeCoordinates;
    private ObjectOutputStream objectOutputStream;
    public Coordinates[] getSnakeCoordinates() {
        return snakeCoordinates;
    }
    public void setSnakeCoordinates(Coordinates[] snakeCoordinates){
        this.snakeCoordinates = snakeCoordinates;
    }

    public SnakeCoordinatesSender(Coordinates[] snakeCoordinates, ObjectOutputStream objectOutputStream){
        this.snakeCoordinates = snakeCoordinates.clone();
        this.objectOutputStream = objectOutputStream;
    }

    @Override
    public void run() {
        while(true) {
            try {
                System.out.println(snakeCoordinates[0]);
                objectOutputStream.writeObject(snakeCoordinates.clone());
                objectOutputStream.flush();
                sleep(500);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}