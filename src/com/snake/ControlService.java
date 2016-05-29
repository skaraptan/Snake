package com.snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Yoga2pro on 29.05.2016.
 */
public class ControlService extends KeyAdapter{

    private int keyCode;

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }



    @Override
    public void keyPressed(KeyEvent keyEvent) {
        keyCode = keyEvent.getKeyCode();
    }
}
