package com.example.snakeladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {
    public static final int tileSize = 40, height = 10, width = 10;
    public static final int buttonLine = height * tileSize + 40, infoLine = buttonLine - 30;
    public static Dice dice = new Dice();
    public Player playerOne, playerTwo;
    public boolean startGame, playerOneTurn, playerTwoTurn;

    private Pane contentCreation(){
        Pane root = new Pane();
        root.setPrefSize(tileSize * width , tileSize * height + 80);
        //for Creating the Board
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j * tileSize);
                tile.setTranslateY(i * tileSize);
                root.getChildren().add(tile);
            }
        }

        //setting an image to the Board
        Image image = new Image("E:\\Accio Project\\Snake And Ladder\\SnakeLadder\\src\\main\\Snake.jpg");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitHeight(height * tileSize);
        imageView.setFitWidth(width * tileSize);

        //for Button
        //player1
        Button player1Button = new Button("Player 1");
        //player2
        Button player2Button = new Button("Player 2");
        //for start button
        Button startButton = new Button("START");

        //player1ButtonLine
        player1Button.setTranslateY(buttonLine);
        player1Button.setTranslateX(25);
        player1Button.setDisable(true);

        //player2ButtonLine
        player2Button.setTranslateY(buttonLine);
        player2Button.setTranslateX(300);
        player2Button.setDisable(true);

        //startButtonLine
        startButton.setTranslateY(buttonLine);
        startButton.setTranslateX(165);

        //info Display
        //for player one
        Label player1Label = new Label("");
        //for player two
        Label player2Label = new Label("");
        //for Dice
        Label diceLabel = new Label("Start Your Game");

        //set label
        //player1
        player1Label.setTranslateY(infoLine);
        player1Label.setTranslateX(25);

        //player2
        player2Label.setTranslateY(infoLine);
        player2Label.setTranslateX(300);

        //dice
        diceLabel.setTranslateY(infoLine);
        diceLabel.setTranslateX(160);

        playerOne = new Player(tileSize , Color.RED, "Aniruddha");
        playerTwo = new Player(tileSize - 5, Color.GREEN, "Arindam");

        //Action Event for player One
        player1Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(startGame){
                    if(playerOneTurn){

                        //for move player one
                        int diceValue = dice.rolledDiceValue();
                        playerOne.movePlayer(diceValue);
                        diceLabel.setText("Dice Value " + diceValue);
                        //Winning Condition
                        if(playerOne.winningCondition()){
                            diceLabel.setText("Winner " + playerOne.getPlayerName());
                            //disable player One
                            playerOneTurn = true;
                            player1Label.setText("");
                            player1Button.setDisable(true);

                            // enable player two
                            playerTwoTurn = true;
                            player2Label.setText("");
                            player2Button.setDisable(true);

                            //enable start button
                            startButton.setDisable(false);
                            startButton.setText("Restart");
                            startGame = false;
                        }else {

                            //disable player One
                            playerOneTurn = false;
                            player1Label.setText("");
                            player1Button.setDisable(true);

                            // enable player two
                            playerTwoTurn = true;
                            player2Label.setText("Your Turn " + playerTwo.getPlayerName());
                            player2Button.setDisable(false); // for making enable we have to give false
                        }

                    }
                }

            }
        });

        //Action Event for Player Two
        player2Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(startGame){
                    if(playerTwoTurn){
                        //for move player two
                        int diceValue = dice.rolledDiceValue();
                        playerTwo.movePlayer(diceValue);
                        diceLabel.setText("Dice Value " + diceValue);
                        //Winning Condition
                        if(playerTwo.winningCondition()){
                            diceLabel.setText("Winner " + playerTwo.getPlayerName());
                            //disable player two
                            playerTwoTurn = true;
                            player2Label.setText("");
                            player2Button.setDisable(true);

                            //disbale player one
                            playerOneTurn = true;
                            player1Label.setText("");
                            player1Button.setDisable(true);
                            //enable start button
                            startButton.setDisable(false);
                            startButton.setText("Restart");
                            startGame = false;
                        }else {
                            //disable player two
                            playerTwoTurn = false;
                            player2Label.setText("");
                            player2Button.setDisable(true);

                            //enable player one
                            playerOneTurn = true;
                            player1Label.setText("Your turn " + playerOne.getPlayerName());
                            player1Button.setDisable(false);// for making enable we have to give false
                        }
                    }
                }

            }
        });

        //for Start Button
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //start the game
                diceLabel.setText("Start Game");
                //both pinter should be disable before start Game
                player1Button.setDisable(true);
                player2Button.setDisable(true);
                //making initinal turn to player one
                playerOneTurn = true;
                startGame = true; //start the game;
                //now disable the start button
                startButton.setDisable(true);//it is disable now
                //here now enable the player one
                player1Button.setDisable(false);
                player1Label.setText("Your Turn " + playerOne.getPlayerName());
                playerOne.initialPosition();
                playerTwo.initialPosition();
            }
        });

        root.getChildren().addAll(imageView, player1Button, player2Button,
                startButton, player1Label, player2Label, diceLabel, playerOne.getCoin(),
                playerTwo.getCoin()
        );


        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(contentCreation());
        stage.setTitle("Snake & Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}