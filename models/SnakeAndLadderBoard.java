package com.snakeandladder.models;


import com.snakeandladder.Ladder;
import com.snakeandladder.Snake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SnakeAndLadderBoard {

    private int Size; //size of board
    private List<Snake> snakes;
    private List<Ladder> ladders;
    private HashMap<String, Integer>playerPieces; // this map store the last postion of every player

    public SnakeAndLadderBoard(){

    }

    public SnakeAndLadderBoard(int size) {

        Size = size;
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();
        this.playerPieces = new HashMap<>();
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int size) {
        Size = size;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public void setSnakes(List<Snake> snakes) {
        this.snakes = snakes;
    }

    public List<Ladder> getLadders() {
        return ladders;
    }

    public void setLadders(List<Ladder> ladders) {
        this.ladders = ladders;
    }

    public HashMap<String, Integer> getPlayerPieces() {
        return playerPieces;
    }

    public void setPlayerPieces(HashMap<String, Integer> playerPieces) {
        this.playerPieces = playerPieces;
    }
}
