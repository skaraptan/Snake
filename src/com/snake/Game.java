package com.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JPanel implements ActionListener{

    public static final int baseSize = 32;
    public static final int height = 20;
    public static final int width = 20;
    public static final int speed =  8;

    Timer timer = new Timer(1000/speed, this);
    BodyModel snakeBody = new BodyModel(10, 10, 9, 10, width, height);
    AppleModel apple = new AppleModel(height, width);


    public Game(){
        timer.start();
        addKeyListener(new ControlService(snakeBody));
        setFocusable(true);
    }

    /*Draw game field*/
    public void fillTexture(Graphics graphics){
        graphics.setColor(Color.YELLOW);
        for(int horizontalLine = 0; horizontalLine < width*baseSize; horizontalLine+=baseSize){
            graphics.drawLine(horizontalLine, 0, horizontalLine, height *baseSize);
        }
        for(int verticalLine = 0; verticalLine < height *baseSize; verticalLine+=baseSize){
            graphics.drawLine(0, verticalLine, width*baseSize, verticalLine);
        }
    }


    /*Draw snake body model*/
    public void fillBodyTexture(Graphics graphics){
        for (int snakeBodyBlock = 0; snakeBodyBlock < snakeBody.getBodyLength(); snakeBodyBlock++){
            graphics.setColor(Color.YELLOW);
            graphics.fillRect(
                    snakeBody.getSnakeCoordinatesX()[snakeBodyBlock]*baseSize,
                    snakeBody.getSnakeCoordinatesY()[snakeBodyBlock]*baseSize,
                    baseSize-1,
                    baseSize-1);

        }
    }

    /*Draw apples*/
    public void fillAppleTexture(Graphics graphics){
        graphics.setColor(Color.CYAN);
        graphics.fillRect(apple.getAppleCoordinateX()*baseSize, apple.getAppleCoordinateY()*baseSize, baseSize, baseSize);
    }
    @Override
    public void paint(Graphics graphics){
        graphics.setColor(new Color(10, 150, 50));
        graphics.fillRect(0, 0 , baseSize* height, baseSize*width );
        fillTexture(graphics);
        fillBodyTexture(graphics);
        fillAppleTexture(graphics);

    }

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(baseSize* height, baseSize*width);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.add(new Game());
        window.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snakeBody.move();
        if(snakeBody.getSnakeCoordinatesX()[0]==apple.getAppleCoordinateX() &&
                snakeBody.getSnakeCoordinatesY()[0]==apple.getAppleCoordinateY()){
                    apple.generateNewApple();
                    snakeBody.increaseBodyLength();
        }
        repaint();
    }
}
