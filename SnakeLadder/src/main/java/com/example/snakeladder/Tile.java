package com.example.snakeladder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
    Tile(int tileSize){
        setWidth(tileSize);
        setHeight(tileSize);
        setFill(Color.RED);
        setStroke(Color.BLACK);
    }
}
