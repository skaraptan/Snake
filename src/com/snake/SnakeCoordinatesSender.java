package com.snake;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Created by Yoga2pro on 04.06.2016.
 */
public class SnakeCoordinatesSender extends ObjectOutputStream{
    private OutputStream out;
    private Coordinates snakeBody;
    public SnakeCoordinatesSender(OutputStream out) throws IOException {
        super(out);
    }

}
