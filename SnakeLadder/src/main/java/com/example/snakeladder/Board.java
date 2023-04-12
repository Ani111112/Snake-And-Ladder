package com.example.snakeladder;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    ArrayList<Pair<Integer, Integer>>positionCoordinate;
    ArrayList<Integer>snakeLadderPosition;
    public Board(){
        positionCoordinate = new ArrayList<>();
        populatepositionCoodinate();
        polulateSnakeAndLadder();
    }

    private void populatepositionCoodinate(){
        positionCoordinate.add(new Pair<>(0,0));
        for (int i = 0; i < SnakeLadder.height; i++) {
            for (int j = 0; j < SnakeLadder.width; j++) {
                //x cordinate
                int xCordinate = 0;
                if(i % 2 == 0){
                    xCordinate = j * SnakeLadder.tileSize + SnakeLadder.tileSize / 2;
                }else {
                    xCordinate = SnakeLadder.tileSize * SnakeLadder.height - (j * SnakeLadder.tileSize) - SnakeLadder.tileSize / 2;
                }
                //y cordinate
                int yCordinate = SnakeLadder.tileSize * SnakeLadder.height - (i * SnakeLadder.tileSize) - SnakeLadder.tileSize / 2;
                positionCoordinate.add(new Pair<>(xCordinate, yCordinate));
            }
        }
    }

    //store all the position of Snake and ladder
    private void polulateSnakeAndLadder(){
        snakeLadderPosition = new ArrayList<>();
        for(int i = 0; i <= SnakeLadder.height * SnakeLadder.width; i++){
            snakeLadderPosition.add(i);
        }
        //Ladder Position
        snakeLadderPosition.set(4, 25);
        snakeLadderPosition.set(13, 46);
        snakeLadderPosition.set(42, 63);
        snakeLadderPosition.set(50, 69);
        snakeLadderPosition.set(62, 81);
        snakeLadderPosition.set(74, 92);
        snakeLadderPosition.set(33, 49);
        //Snake Position
        snakeLadderPosition.set(27, 5);
        snakeLadderPosition.set(40, 3);
        snakeLadderPosition.set(43, 18);
        snakeLadderPosition.set(54, 31);
        snakeLadderPosition.set(66, 45);
        snakeLadderPosition.set(76, 58);
        snakeLadderPosition.set(89, 53);
        snakeLadderPosition.set(99, 41);
    }
    int getXCoordinate(int pos){
        if(pos >= 1 && pos <= 100)
            return positionCoordinate.get(pos).getKey();
        return -1;
    }
    int getYCoordinate(int pos){
        if(pos >= 1 && pos <= 100)
            return positionCoordinate.get(pos).getValue();
        return -1;
    }
    int getladderSnakePosition(int pos){
        if(pos >= 1 && pos <= 100)
            return snakeLadderPosition.get(pos);
        return -1;
    }

    public static void main(String[] args) {
        Board board = new Board();
        for (int i = 0; i < board.positionCoordinate.size(); i++) {
            System.out.printf(board.positionCoordinate.get(i) + " ");
        }
    }
}
