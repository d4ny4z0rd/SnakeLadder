package com.example.snakeladder;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    ArrayList<Pair<Integer, Integer>> positionCoordinates;
    ArrayList<Integer> snakeLadderPosition;

    public Board() {
        positionCoordinates = new ArrayList<>();
        populatePositionCoordinates();
        populateSnakeLadder();
    }

    private void populatePositionCoordinates() {
        positionCoordinates.add(new Pair<>(0,0)); // dummy value
        for (int i = 0; i < SnakeLadder.height; i++) {
            for (int j = 0; j < SnakeLadder.width; j++) {
                // x coordinates
                // y coordinates
                int xCord = 0;
                if (i % 2 == 0) {
                    xCord = j * SnakeLadder.tileSize + SnakeLadder.tileSize / 2;
                } else {
                    xCord = SnakeLadder.tileSize * SnakeLadder.height - (j * SnakeLadder.tileSize) - SnakeLadder.tileSize / 2;
                }
                int yCord = SnakeLadder.tileSize * SnakeLadder.height - (i * SnakeLadder.tileSize) - SnakeLadder.tileSize / 2;
                positionCoordinates.add(new Pair<>(xCord, yCord));
            }
        }
    }
    private void populateSnakeLadder(){
        snakeLadderPosition = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            snakeLadderPosition.add(i);
        }
        snakeLadderPosition.set(4,25);
        snakeLadderPosition.set(13,46);
        snakeLadderPosition.set(27,5);
        snakeLadderPosition.set(33,49);
        snakeLadderPosition.set(40,3);
        snakeLadderPosition.set(42,63);
        snakeLadderPosition.set(43,18);
        snakeLadderPosition.set(50,69);
        snakeLadderPosition.set(54,31);
        snakeLadderPosition.set(62,81);
        snakeLadderPosition.set(66,45);
        snakeLadderPosition.set(76,58);
        snakeLadderPosition.set(74,92);
        snakeLadderPosition.set(89,53);
        snakeLadderPosition.set(99,41);
    }
    public int getNewPosition(int currentPosition){
        if(currentPosition>0 && currentPosition<=100){
            return snakeLadderPosition.get(currentPosition);
        }
        return -1;
    }
    int getXCoordinate(int position) {
        if(position >= 1 && position <= 100) {
            return positionCoordinates.get(position).getKey();
        }
        return -1;
    }
    int getYCoordinate(int position) {
        if(position >= 1 && position <= 100) {
            return positionCoordinates.get(position).getValue();
        }
        return -1;
    }
}