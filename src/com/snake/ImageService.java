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
    BufferedImage[] fruitImg = new BufferedImage[3];
    BufferedImage grassImg;
    BufferedImage snakeBlockImg;
    Random rand = new Random();

    public ImageService(){
        try {
            fruitImg[0] = ImageIO.read(new File("lime.png"));
            fruitImg[1] = ImageIO.read(new File("kiwi.png"));
            fruitImg[2] = ImageIO.read(new File("grape.png"));
            grassImg = ImageIO.read(new File("grass.jpg"));
            snakeBlockImg = ImageIO.read(new File("body.png"));
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
}
