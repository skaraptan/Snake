package com.snake;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Yoga2pro on 30.05.2016.
 */
public class ImageService {
    private BufferedImage[] fruitImg = new BufferedImage[3];
    private BufferedImage grassImg;
    private BufferedImage snakeBlockImg;
    private BufferedImage snakeBlockImgUp;
    private BufferedImage snakeBlockImgDown;
    private BufferedImage snakeBlockImgLeft;
    private BufferedImage snakeBlockImgRight;
    private BufferedImage enemySnakeBlockImg;
    private Random rand = new Random();

    public ImageService(){
        try {
            fruitImg[0] = ImageIO.read(new File("lime.png"));
            fruitImg[1] = ImageIO.read(new File("kiwi.png"));
            fruitImg[2] = ImageIO.read(new File("grape.png"));
            grassImg = ImageIO.read(new File("grass.jpg"));
            snakeBlockImg = ImageIO.read(new File("bug.png"));
            enemySnakeBlockImg = ImageIO.read(new File("bug_enemy.png"));
            snakeBlockImgUp = ImageIO.read(new File("bug.png"));
            snakeBlockImgDown = ImageIO.read(new File("bug_down.png"));
            snakeBlockImgLeft = ImageIO.read(new File("bug_left.png"));
            snakeBlockImgRight = ImageIO.read(new File("bug_right.png"));
        }
        catch (IOException e){
            System.err.print("Image cannot by found");
        }
    }
    public BufferedImage getRandomFruit(){
        return fruitImg[rand.nextInt(3)];
    }
    public BufferedImage getGrassImg(){
        return  grassImg;
    }
    public BufferedImage getSnakeBlockImg(){
        return  snakeBlockImg;
    }
    public BufferedImage getSnakeBlockImgUp(){
        return  snakeBlockImgUp;
    }
    public BufferedImage getSnakeBlockImgDown(){
        return  snakeBlockImgDown;
    }
    public BufferedImage getSnakeBlockImgLeft(){
        return  snakeBlockImgLeft;
    }
    public BufferedImage getSnakeBlockImgRight(){
        return  snakeBlockImgRight;
    }
    public BufferedImage getEnemySnakeBlockImg(){
        return enemySnakeBlockImg;
    }
}
