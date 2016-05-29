package com.snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Yoga2pro on 29.05.2016.
 */
public class ControlService extends KeyAdapter {

    private int keyCode;
    private BodyModel bodyModel;

    public ControlService(BodyModel bodyModel){
        this.bodyModel = bodyModel;
    }
    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        keyCode = keyEvent.getKeyCode();
        if(keyCode == keyEvent.VK_UP && bodyModel.getSnakeDirection()!=Direction.DOWN){
            bodyModel.setSnakeDirection(Direction.UP);
        }
        if(keyCode == keyEvent.VK_DOWN && bodyModel.getSnakeDirection()!=Direction.UP){
            bodyModel.setSnakeDirection(Direction.DOWN);
        }
        if(keyCode == keyEvent.VK_LEFT && bodyModel.getSnakeDirection()!=Direction.RIGHT){
            bodyModel.setSnakeDirection(Direction.LEFT);
        }
        if(keyCode == keyEvent.VK_RIGHT && bodyModel.getSnakeDirection()!=Direction.LEFT){
            bodyModel.setSnakeDirection(Direction.RIGHT);
        }
    }

}