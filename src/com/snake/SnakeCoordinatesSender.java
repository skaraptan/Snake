package com.snake;

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Yoga2pro on 04.06.2016.
 */
public class SnakeCoordinatesSender extends Thread{

    private Coordinates[] snakeCoordinates;
    private ObjectOutputStream objectOutputStream;
    public Coordinates[] getSnakeCoordinates() {
        return snakeCoordinates;
    }
    public void setSnakeCoordinates(Coordinates[] snakeCoordinates){
        this.snakeCoordinates = snakeCoordinates;
    }

    public SnakeCoordinatesSender(Coordinates[] snakeCoordinates, ObjectOutputStream objectOutputStream){
        this.snakeCoordinates = snakeCoordinates;
        this.objectOutputStream = objectOutputStream;
    }

    @Override
    public synchronized void run() {
            try {
                objectOutputStream.writeObject(snakeCoordinates);
                objectOutputStream.flush();
                sleep(100);
                notify();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
