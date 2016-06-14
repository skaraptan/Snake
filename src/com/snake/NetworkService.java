package com.snake;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

/**
 * Created by Yoga2pro on 10.06.2016.
 */
public class NetworkService extends Thread {
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private Coordinates[] enemyCoordinates;
    private Coordinates[] currentPlayerCoordinates;
    private int currentPlayerBodyLength;
    private int enemyBodyLength;
    static Vector<NetworkService> players = new Vector<>();
    static FruitModel apple;
    public NetworkService(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        synchronized (players) {
            players.add(this);
        }
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            while(true) {
                if (players.size() == 2) {
                    readCurrentPlayerSnakeCoordinates();
                    readEnemySnakeCoordinates();
                    sendEnemySnakeCoordinatesToClient();
                }
            }
        } catch (IOException  e) {
        }
    }

    private void readEnemySnakeCoordinates(){
        for(NetworkService player : players) {
            synchronized (players) {
                if(player != this) {
                        this.enemyCoordinates = player.currentPlayerCoordinates;
                        this.enemyBodyLength = player.currentPlayerBodyLength;
                }
            }
        }
    }

    private void readCurrentPlayerSnakeCoordinates(){
                    try {
                        currentPlayerCoordinates = (Coordinates[]) objectInputStream.readObject();
                        currentPlayerBodyLength = (int) objectInputStream.readObject();
                    } catch (IOException | ClassNotFoundException e) {
                    }
                }

    private void sendEnemySnakeCoordinatesToClient(){
                    try {
                        objectOutputStream.writeObject(enemyCoordinates);
                        objectOutputStream.writeObject(enemyBodyLength);
                        objectOutputStream.flush();
                        objectOutputStream.reset();
                    } catch (IOException e) {
                    }
                 }
}
