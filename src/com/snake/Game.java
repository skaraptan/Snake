package com.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;

public class Game extends JPanel implements ActionListener{

    public static final int baseSize = 32;
    public static final int high = 20;
    public static final int width = 20;
    public static final int speed = 5;
    Timer timer = new Timer(1000/speed, this);
    BodyModel snakeBody = new BodyModel(10, 10, 9, 10);
    public Game(){
        timer.start();
    }
    /*Draw game field*/
    public void fillTexture(Graphics graphics){
        graphics.setColor(Color.YELLOW);
        for(int horizontalLine = 0; horizontalLine < width*baseSize; horizontalLine+=baseSize){
            graphics.drawLine(horizontalLine, 0, horizontalLine, high*baseSize);
        }
        for(int verticalLine = 0; verticalLine < high*baseSize; verticalLine+=baseSize){
            graphics.drawLine(0, verticalLine, width*baseSize, verticalLine);
        }
    }
    /*Draw snake body model*/

    public void fillBodyTexture(Graphics graphics){
        for (int snakeBodyBlock = 0; snakeBodyBlock < snakeBody.getBodyLenght(); snakeBodyBlock++){
            graphics.setColor(Color.YELLOW);
            graphics.fillRect(
                    snakeBody.getSnakeCoordinatesX()[snakeBodyBlock]*baseSize+1,
                    snakeBody.getSnakeCoordinatesY()[snakeBodyBlock]*baseSize+1,
                    baseSize-1,
                    baseSize-1);

        }
    }

    @Override
    public void paint(Graphics graphics){
        graphics.setColor(new Color(10, 150, 50));
        graphics.fillRect(0, 0 , baseSize*high, baseSize*width );
        fillTexture(graphics);
        fillBodyTexture(graphics);

    }

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(baseSize*high, baseSize*width);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.add(new Game());
        window.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snakeBody.move();
        repaint();
    }
}
