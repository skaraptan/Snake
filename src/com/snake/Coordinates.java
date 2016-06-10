package com.snake;

import java.io.Serializable;

/**
 * Created by Yoga2pro on 03.06.2016.
 */
public class Coordinates implements Serializable{

    private int x=0;
    private int y=0;

    public Coordinates(){

    }
    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String toString(){
        return ("X: " + x  + "Y:  " +  y );
    }

}
