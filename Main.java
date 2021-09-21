package com.snakeandladder;

import com.snakeandladder.services.DiceService;
import com.snakeandladder.services.SnakeAndLadderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
//        List<List<Integer>>arr2 = new ArrayList<>(3);
//        for(int i =0;i<3;i++){
//            arr2.add(new ArrayList<>(3));
//        }
//        for(int i=0;i<3;i++){
//            for(int j=0;j<3;j++){
//                arr2.get(i).add(2);
//            }
//        }
//        for(int i=0;i<3;i++){
//            for(int j=0;j<3;j++){
//                System.out.print(arr2.get(i).get(j) +" ");
//            }
//            System.out.println();
//        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of snakes");
        int numberofsnakes  = sc.nextInt();
        List<com.snakeandladder.Snake>snakes = new ArrayList<>();
        for(int i =0;i<numberofsnakes;i++){
            System.out.println("Enter the head and tail for snake " + i+1);
            snakes.add(new com.snakeandladder.Snake(sc.nextInt(), sc.nextInt()));
        }

        System.out.println("Enter the number of ladders");
        int numberofladders  = sc.nextInt();
        List<com.snakeandladder.Ladder>ladders = new ArrayList<>();
        for(int i =0;i<numberofladders;i++){
            System.out.println("Enter the start and end point for ladder "+ i+1);
            ladders.add(new com.snakeandladder.Ladder(sc.nextInt(), sc.nextInt()));
        }

        System.out.println("Enter the number of playes");
        int noOfPlayers = sc.nextInt();
        List<com.snakeandladder.Player> players = new ArrayList<com.snakeandladder.Player>();
        for (int i = 0; i < noOfPlayers; i++) {
            System.out.println("Enter the name of the player "+ i+1);
            players.add(new com.snakeandladder.Player(sc.next()));
        }

        SnakeAndLadderService snakeAndLadderService = new SnakeAndLadderService();
        snakeAndLadderService.setPlayers(players);
        snakeAndLadderService.setladders(ladders);
        snakeAndLadderService.setsnakes(snakes);


        snakeAndLadderService.startgame();


    }
}
