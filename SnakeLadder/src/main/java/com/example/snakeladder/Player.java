package com.example.snakeladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
    private Circle coin;
    private int currentPosition;
    private String playerName;
    private static Board gameBoard = new Board();
    Player(int tileSize, Color coinColor, String name){
        coin = new Circle(tileSize / 2);
        coin.setFill(coinColor);
        playerName = name;
        currentPosition = 1;
        movePlayer(0);
    }

    public void movePlayer(int diceValue){
        if(currentPosition + diceValue <= 100){
            currentPosition += diceValue;

            TranslateTransition secondMove = null, firstMove = addAnimation(diceValue);
            int newPosition = gameBoard.getladderSnakePosition(currentPosition);
            if(newPosition != currentPosition){
                currentPosition = newPosition;
                secondMove = addAnimation(diceValue);
            }
            if(secondMove == null){
                firstMove.play();
            }else {
                SequentialTransition sequentialTransition = new SequentialTransition(firstMove, new PauseTransition(Duration.millis(550)),
                    secondMove
                );
                sequentialTransition.play();
            }
        }

    }
    public void initialPosition(){
        currentPosition = 1;
        movePlayer(0);
    }
    //Winning Condition
    boolean winningCondition(){
        if(currentPosition == 100){
            return true;
        }
        else return false;
    }

    //add animation to the board
    private TranslateTransition addAnimation(int diceValue){
        if(diceValue == 0)diceValue = 1;
        TranslateTransition transition = new TranslateTransition(Duration.millis(200 * diceValue), getCoin());
        transition.setToX(gameBoard.getXCoordinate(currentPosition));
        transition.setToY(gameBoard.getYCoordinate(currentPosition));
        transition.setAutoReverse(false);
        return transition;
    }
    public Circle getCoin() {
        return coin;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public String getPlayerName() {
        return playerName;
    }
}
