package com.snake;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 * Created by Yoga2pro on 03.06.2016.
 */
public class GameServer extends Thread{

    private ServerSocket serverSocket;
    private int port = 1234;
    private Vector<Coordinates[]> snakeCoordinates = new Vector<>();
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public GameServer(){
    }

    public void run(){
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Connected from" + socket.getInetAddress());
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                    while(true) {
                        Coordinates[] snake = (Coordinates[]) objectInputStream.readObject();
                        snakeCoordinates.add(snake);
                        System.out.print(snakeCoordinates.get(0)[0].getX());
                    }

            }
        }
        catch (Exception e){
            System.err.println(e);
        }
    }
}
