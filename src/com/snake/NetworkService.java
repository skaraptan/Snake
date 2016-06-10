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
    static Vector<NetworkService> players = new Vector<>();
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
                    sendCurrentPlayerSnakeCoordinatesToEnemy();
                    readEnemySnakeCoordinates();
                    sendEnemySnakeCoordinatesToClient();
//                    System.out.println(currentPlayerCoordinates[0].getX() + "  ---  " + currentPlayerCoordinates[0].getY());
//                    System.out.println(enemyCoordinates[0].getX() + "  ---  " + enemyCoordinates[0].getY() + "\n\n");
                }
                sleep(100);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void readEnemySnakeCoordinates(){
        for(NetworkService player : players) {
            synchronized (players) {
                if(player != this) {
                    try {
                        this.enemyCoordinates = (Coordinates[]) player.objectInputStream.readObject();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void readCurrentPlayerSnakeCoordinates(){
        for(NetworkService player : players) {
            synchronized (players) {
                if(player == this) {
                    try {
                        currentPlayerCoordinates = (Coordinates[]) objectInputStream.readObject();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void sendEnemySnakeCoordinatesToClient(){
        for(NetworkService player : players) {
            synchronized (players) {
                if(player == this) {
                    try {
                        objectOutputStream.writeObject(enemyCoordinates);
                        objectOutputStream.flush();
                        objectOutputStream.reset();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public void sendCurrentPlayerSnakeCoordinatesToEnemy(){
        for(NetworkService player : players) {
            synchronized (players) {
                if(player != this) {
                    try {
                        objectOutputStream.writeObject(this.currentPlayerCoordinates);
                        objectOutputStream.flush();
                        objectOutputStream.reset();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
