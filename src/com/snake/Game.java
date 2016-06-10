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
    public static final int speed =  8;
    private Socket socket;
    ImageService imageService = new ImageService();
    BufferedImage fruitImg = imageService.getRandomFruit();
    Timer timer = new Timer(1000/speed, this);
    BodyModel snakeBody = new BodyModel(imageService.getSnakeBlockImg(),9, 10, 10, 10, width, height);
    BodyModel enemySnakeBody = new BodyModel(imageService.getEnemySnakeBlockImg(), 1 , 2, 1, 1, width, height);
    FruitModel apple = new FruitModel(height, width, snakeBody);
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    public JLabel getScore() {
        JLabel score = new JLabel("Score : " + snakeBody.getBodyLength());
        return score;
    }

    public Game() throws IOException{
        addKeyListener(new ControlService(snakeBody));
        setFocusable(true);
        timer.start();
        socket = new Socket("localhost", 1234);
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
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
            graphics.drawImage(snakeBody.getSnakeBodyImg(),
                    snakeBody.getSnakeCoordinates()[snakeBodyBlock].getX()*baseSize,
                    snakeBody.getSnakeCoordinates()[snakeBodyBlock].getY()*baseSize,
                    null,
                    null);

        }
        for (int snakeBodyBlock = 0; snakeBodyBlock < enemySnakeBody.getBodyLength(); snakeBodyBlock++){
            graphics.setColor(Color.YELLOW);
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
    public void sentCoordinates(BodyModel snakeBody){
        try {
            objectOutputStream.writeObject(snakeBody.getSnakeCoordinates());
            objectOutputStream.flush();
            objectOutputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getEnemyCoordinates(){
        try {
            Coordinates[] enemySnakeCoordinates = (Coordinates[]) objectInputStream.readObject();
                enemySnakeBody.setSnakeCoordinates(enemySnakeCoordinates);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void actionPerformed(ActionEvent e) {
        snakeBody.move();
        if (snakeBody.getSnakeCoordinates()[0].getX()==apple.getAppleCoordinates().getX() && snakeBody.getSnakeCoordinates()[0].getY()==apple.getAppleCoordinates().getY()) {
            fruitImg = imageService.getRandomFruit();
            apple.generateNewApple(snakeBody);
            snakeBody.increaseBodyLength();
            getEnemyCoordinates();
        }
        sentCoordinates(snakeBody);
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
