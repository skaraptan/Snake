package com.snake;

import java.io.*;
import java.net.Socket;

/**
 * Created by Yoga2pro on 04.06.2016.
 */
class ClientSnakeCoordinatesService extends Thread{
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private BodyModel enemySnakeBody;
    private BodyModel currentPlayerSnakeBody;

    public ClientSnakeCoordinatesService(ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream,
                                         BodyModel enemySnakeBody, BodyModel currentPlayerSnakeBody){
        this.objectOutputStream = objectOutputStream;
        this.objectInputStream = objectInputStream;
        this.enemySnakeBody = enemySnakeBody;
        this.currentPlayerSnakeBody = currentPlayerSnakeBody;
    }

    @Override
    public void run() {
        while (true) {
            sendCoordinatesToServer();
            readEnemyCoordinatesFromServer();
        }
    }

    private void readEnemyCoordinatesFromServer(){
        try {
            enemySnakeBody.setSnakeCoordinates((Coordinates[]) objectInputStream.readObject());
            enemySnakeBody.setBodyLength((int) objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void sendCoordinatesToServer(){
        try {
            objectOutputStream.writeObject(currentPlayerSnakeBody.getSnakeCoordinates());
            objectOutputStream.writeObject(currentPlayerSnakeBody.getBodyLength());
            objectOutputStream.flush();
            objectOutputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}