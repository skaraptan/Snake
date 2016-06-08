package com.snake;

import java.io.IOException;

/**
 * Created by Yoga2pro on 03.06.2016.
 */
public class AppStarter {
    public static void main(String[] args){
        try {
            new Game().process();
        }
        catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
