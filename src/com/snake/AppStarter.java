package com.snake;

import java.io.IOException;

/**
 * Created by Yoga2pro on 03.06.2016.
 */
public class AppStarter {
    private int port;
    private String address;
    public AppStarter(int port , String address){
        this.port = port;
        this.address = address;

        try {
            new Game(port, address).process();
        }
        catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
