package com.example.snakeladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class SnakeLadder extends Application {
    public static final int tileSize = 40, width = 10, height = 10;
    public static final int buttonLine = height*tileSize + 50, infoLine = buttonLine - 30;
    private static Dice dice = new Dice();
    private Player playerOne, playerTwo;
    private boolean gameStarted = false, playerOneTurn = false, playerTwoTurn = false;
    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width*tileSize, height*tileSize+100);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().add(tile);
            }
        }
        Image img = new Image("C:\\Users\\Dell\\IdeaProjects\\SnakeLadder\\src\\main\\istockphoto-531466314-1024x1024.jpg");
        ImageView board = new ImageView();
        board.setImage(img);
        board.setFitHeight(height*tileSize);
        board.setFitWidth(width*tileSize);

        // add buttons
        Button player1 = new Button("Player 1");
        Button player2 = new Button("Player 2");
        Button startButton = new Button("Start");

        player1.setTranslateY(buttonLine);
        player1.setTranslateX(20);
        player1.setDisable(true);
        player2.setTranslateY(buttonLine);
        player2.setTranslateX(320);
        player2.setDisable(true);
        startButton.setTranslateY(buttonLine);
        startButton.setTranslateX(180);

        //Info display
        Label player1Label = new Label("");
        Label player2Label = new Label("");
        Label diceLabel = new Label("Roll!");

        player1Label.setTranslateY(infoLine);
        player1Label.setTranslateX(20);
        player2Label.setTranslateY(infoLine);
        player2Label.setTranslateX(320);
        diceLabel.setTranslateY(infoLine);
        diceLabel.setTranslateX(180);

        playerOne = new Player(tileSize, Color.BLACK, "Pritesh");
        playerTwo = new Player(tileSize-5, Color.WHITE, "Prabhav");
        // Moving Players
        player1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerOneTurn){
                        int diceValue = dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value :" + diceValue);
                        playerOne.movePlayer(diceValue);
                        // winning condition
                        if(playerOne.isWinner()){
                            diceLabel.setText("Winner is"+ playerOne.getName()+"!");
                            playerOneTurn = false;
                            player1.setDisable(true);
                            player1Label.setText("");

                            playerTwoTurn = false;
                            player2.setDisable(true);
                            player2Label.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart Game");
                            gameStarted = false;
                        }
                        else{
                            playerOneTurn = false;
                            player1.setDisable(true);
                            player1Label.setText("");

                            playerTwoTurn = true;
                            player2.setDisable(false);
                            player2Label.setText("Your Turn "+ playerTwo.getName());
                        }
                    }
                }
            }
        });
        player2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerTwoTurn){
                        int diceValue = dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value :" + diceValue);
                        playerTwo.movePlayer(diceValue);
                        // winning condition
                        if (playerTwo.isWinner()){
                            diceLabel.setText("Winner is " + playerTwo.getName()+"!");
                            playerOneTurn = false;
                            player1.setDisable(true);
                            player1Label.setText("");

                            playerTwoTurn = false;
                            player2.setDisable(true);
                            player2Label.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart Game");
                        }
                        else{
                            playerOneTurn = true;
                            player1.setDisable(false);
                            player1Label.setText("Your Turn " + playerOne.getName());

                            playerTwoTurn = false;
                            player2.setDisable(true);
                            player2Label.setText("");
                        }
                    }
                }
            }
        });
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted = true;
                diceLabel.setText("Game Started");
                startButton.setDisable(true);

                playerOneTurn = true;
                player1Label.setText("Your Turn "+ playerOne.getName());
                player1.setDisable(false);
                playerOne.startingPosition();

                playerTwoTurn = false;
                player2Label.setText("");
                player2.setDisable(true);
                playerTwo.startingPosition();
            }
        });
        root.getChildren().addAll(board, player2, player1, startButton, player1Label, player2Label, diceLabel, playerOne.getCoin(), playerTwo.getCoin());
        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(createContent());
        stage.setTitle("Snakes & Ladders!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}