package com.snake;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Vector;

/**
 * Created by Yoga2pro on 03.06.2016.
 */
public class GameServer extends Thread{

    private ServerSocket serverSocket;
    private int port;
    private int snakeLenght1;
    private int snakeLenght2;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    public GameServer(int port){
        this.port = port;
    }

    public void run(){
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is running on port  " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Connected from" + socket.getInetAddress());
                new NetworkService(socket).start();
                }
        }
        catch (SocketException se){

        }
        catch (IOException ioe){

        }
    }
}
