package com.snake;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Game extends JPanel implements ActionListener{

    public static final int baseSize = 32;
    public static final int height = 20;
    public static final int width = 20;
    public static final int speed =  7;
    private Socket socket;
    ImageService imageService = new ImageService();
    BufferedImage fruitImg = imageService.getRandomFruit();
    Timer timer = new Timer(1000/speed, this);
    BodyModel snakeBody = new BodyModel(imageService.getSnakeBlockImg(),9, 10, 10, 10, width, height);
    BodyModel enemySnakeBody = new BodyModel(imageService.getEnemySnakeBlockImg(), -1, -1, -1, -1, width, height);
    FruitModel apple = new FruitModel(height, width, snakeBody);
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    public Game() throws IOException{
        addKeyListener(new ControlService(snakeBody));
        setFocusable(true);
        timer.start();
        socket = new Socket("localhost", 1234);
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        new ClientSnakeCoordinatesService(objectOutputStream, objectInputStream, enemySnakeBody, snakeBody).start();
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

    public void showScore(Graphics graphics){
        graphics.setFont(new Font("TimesRoman" , Font.BOLD, 30));
        graphics.setColor(new Color(0, 53, 255));
        graphics.drawString("You : " + snakeBody.getBodyLength(), 500, 600 );
        graphics.setColor(Color.WHITE);
        graphics.drawString("Enemy : " + enemySnakeBody.getBodyLength(), 1, 600);
    }
    /*Draw snake body model*/
    public void fillBodyTexture(Graphics graphics){
        for (int snakeBodyBlock = 0; snakeBodyBlock < snakeBody.getBodyLength(); snakeBodyBlock++){
            graphics.drawImage(snakeBody.getSnakeBodyImg(),
                    snakeBody.getSnakeCoordinates()[snakeBodyBlock].getX()*baseSize,
                    snakeBody.getSnakeCoordinates()[snakeBodyBlock].getY()*baseSize,
                    null,
                    null);

        }
        for (int snakeBodyBlock = 0; snakeBodyBlock < enemySnakeBody.getBodyLength(); snakeBodyBlock++){
            graphics.drawImage(enemySnakeBody.getSnakeBodyImg(),
                    enemySnakeBody.getSnakeCoordinates()[snakeBodyBlock].getX()*baseSize,
                    enemySnakeBody.getSnakeCoordinates()[snakeBodyBlock].getY()*baseSize,
                    null,
                    null);

        }
    }

    /*Draw apples*/
    public void fillAppleTexture(Graphics graphics){
        graphics.drawImage(fruitImg ,apple.getAppleCoordinates().getX()*baseSize, apple.getAppleCoordinates().getY()*baseSize, null , null);
    }

    @Override
    public void paint(Graphics graphics){
        graphics.setColor(new Color(10, 150, 50));
        graphics.drawImage(imageService.getGrassImg() ,0, 0 , null, null);
       // fillTexture(graphics);
        fillBodyTexture(graphics);
        fillAppleTexture(graphics);
        showScore(graphics);
    }

    public synchronized void process() throws IOException, InterruptedException {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(baseSize* height, baseSize*(width+1));
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.add(this);
        window.setVisible(true);
    }

    @Override
    public synchronized void actionPerformed(ActionEvent e) {
        snakeBody.move();
        if (snakeBody.getSnakeCoordinates()[0].getX()==apple.getAppleCoordinates().getX() && snakeBody.getSnakeCoordinates()[0].getY()==apple.getAppleCoordinates().getY()) {
            fruitImg = imageService.getRandomFruit();
            apple.generateNewApple(snakeBody);
            snakeBody.increaseBodyLength();
        }
        for (int blockId = snakeBody.getBodyLength()-1 ; blockId > 0; blockId--){
            if (enemySnakeBody.getSnakeCoordinates()[0].getX() == snakeBody.getSnakeCoordinates()[blockId].getX() &&
                    enemySnakeBody.getSnakeCoordinates()[0].getY() == snakeBody.getSnakeCoordinates()[blockId].getY()){
                snakeBody.setBodyLength(blockId);
            }
        }
        repaint();
    }

    /*not used yet*/
    public void changeBlockImageIfRequired(){
        if(snakeBody.getSnakeDirection()==Direction.UP){
            snakeBody.setSnakeBodyImg(imageService.getSnakeBlockImgUp());
        }
        if (snakeBody.getSnakeDirection()==Direction.DOWN){
            snakeBody.setSnakeBodyImg((imageService.getSnakeBlockImgDown()));
        }
        if (snakeBody.getSnakeDirection()==Direction.LEFT){
            snakeBody.setSnakeBodyImg((imageService.getSnakeBlockImgLeft()));
        }
        if (snakeBody.getSnakeDirection()==Direction.RIGHT){
            snakeBody.setSnakeBodyImg((imageService.getSnakeBlockImgRight()));
        }
    }

}
