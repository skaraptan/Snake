package com.snake;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by Yoga2pro on 13.06.2016.
 */
public class AppleCoordinatesReader extends Thread{

    private FruitModel fruit;
    private ObjectInputStream objectInputStream;

    public AppleCoordinatesReader(FruitModel fruit, ObjectInputStream objectInputStream){
        this.fruit = fruit;
        this.objectInputStream = objectInputStream;
    }
    public void run() {
        while (true) {
            try {
                fruit.setAppleCoordinates((Coordinates) objectInputStream.readObject());
            } catch (IOException | ClassNotFoundException ignored) {
            }
        }
    }
}
