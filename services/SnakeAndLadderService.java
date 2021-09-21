package com.snakeandladder.services;

import com.snakeandladder.Ladder;
import com.snakeandladder.Player;
import com.snakeandladder.Snake;
import com.snakeandladder.models.SnakeAndLadderBoard;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SnakeAndLadderService {

    private SnakeAndLadderBoard snakeAndLadderBoard;
    private int initialnumberofplayers;
    private Queue<Player>players;

    private int noOfDices; //Optional Rule 1
    private boolean shouldGameContinueTillLastPlayer; //Optional Rule 3
    private boolean shouldAllowMultipleDiceRollOnSix; //Optional Rule 4

    private static final int DEFAULT_BOARD_SIZE = 100; //The board will have 100 cells numbered from 1 to 100.
    private static final int DEFAULT_NO_OF_DICES = 1;


    public SnakeAndLadderService(int boardsize){
      this.snakeAndLadderBoard = new SnakeAndLadderBoard(boardsize);
      this.players = new LinkedList<Player>();
      this.noOfDices = SnakeAndLadderService.DEFAULT_NO_OF_DICES;
    }

    public SnakeAndLadderService() {
        this(SnakeAndLadderService.DEFAULT_BOARD_SIZE);
    }

    //extend the game if setter demand for more functionality
    public void setNoOfDices(int noOfDices) {
        this.noOfDices = noOfDices;
    }

    public void setShouldGameContinueTillLastPlayer(boolean shouldGameContinueTillLastPlayer) {
        this.shouldGameContinueTillLastPlayer = shouldGameContinueTillLastPlayer;
    }

    public void setShouldAllowMultipleDiceRollOnSix(boolean shouldAllowMultipleDiceRollOnSix) {
        this.shouldAllowMultipleDiceRollOnSix = shouldAllowMultipleDiceRollOnSix;
    }


    /**
     * ==================Initialize board==================
     */

    public void setPlayers(List<Player>playerlist){
        this.players = new LinkedList<>();
        this.initialnumberofplayers = playerlist.size();
        HashMap<String, Integer>playermap = new HashMap<>();
        for(Player player : playerlist){
            this.players.add(player);
            playermap.put(player.getId(), 0); //Each player has a piece which is initially kept outside the board (i.e., at position 0).
        }
        this.snakeAndLadderBoard.setPlayerPieces(playermap);//  Add pieces to board
    }

    public void setladders(List<Ladder>listofladders){
        this.snakeAndLadderBoard.setLadders(listofladders);
    }

    public void setsnakes(List<Snake>snakelist){
        this.snakeAndLadderBoard.setSnakes(snakelist);
    }

    /**
     * ==========Core business logic for the game==========
     */
    //this function check at the given positon there is any sanke and ladder if it is then return the new postion
    public int getnewpositionaftergoesthroughsnakeandladder( int newposition){
        //ya to 2 while lga lo ya fir do while
        // Whenever a piece ends up at a position with the head of the snake, the piece should go down to the position of the tail of that snake.
       int prevposition;
        do{
            prevposition = newposition;
            for(Snake snake : this.snakeAndLadderBoard.getSnakes()){
                if(snake.getHead() == newposition){
                    newposition = snake.getTail();
                }
            }

            for(Ladder ladder : this.snakeAndLadderBoard.getLadders()){
                if(ladder.getStart() == newposition){
                    newposition = ladder.getEnd();
                }
            }

        }while(prevposition != newposition);

        return newposition;
    }

    public void move(Player player, int position){
         int oldposition = this.snakeAndLadderBoard.getPlayerPieces().get(player.getId());
         int newpositon = oldposition + position;

         int lastpostionofboad = this.snakeAndLadderBoard.getSize();

         if(newpositon > lastpostionofboad){
             newpositon = oldposition;
         }else{
             newpositon = getnewpositionaftergoesthroughsnakeandladder(newpositon);
         }

         this.snakeAndLadderBoard.getPlayerPieces().put(player.getId(),newpositon);

        System.out.println(player.getName() + " rolled a " + position + " and moved from " + oldposition +" to " + newpositon);

    }

    public int getTotalvalueafterdiceroll(){
        return DiceService.roll();
    }

    boolean hasplayerwon(Player player){
        int winningposition = this.snakeAndLadderBoard.getSize();
        if(this.snakeAndLadderBoard.getPlayerPieces().get(player.getId()) == winningposition){
            return true;
        }
        return false;
    }

    boolean isgamecompleted(){
        int playersize = this.players.size();
        if(playersize < this.initialnumberofplayers){
            return true;
        }

        return false;
    }

    public void startgame(){
        while(!isgamecompleted()){
             int dicerollvalue = getTotalvalueafterdiceroll();
             Player currentplayer = this.players.poll();
             this.move(currentplayer, dicerollvalue);

             if(this.hasplayerwon(currentplayer)){
                 System.out.println(currentplayer.getName() + " wins the game");
                 this.snakeAndLadderBoard.getPlayerPieces().remove(currentplayer.getId());

             }else{
                this.players.add(currentplayer);
             }

        }
    }




}
