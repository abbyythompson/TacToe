package ticTacToe.printer;

import ticTacToe.printer.*;
import ticTacToe.gameEngine.*;
/**
 * Created by abbythompson
 */
public class BoardPrinter {
    private Game game;

    public BoardPrinter(Game game){
        this.game = game;
    }

    public void printBoard(){
        int[][] board = game.getBoardState();

        System.out.print("\n");

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){

                if(board[i][j] == 0){
                    System.out.print(" .");
                } else if(board[i][j] == 1) {
                    System.out.print(" X");
                } else if(board[i][j] == 2) {
                    System.out.print(" O");
                }

                if(j != 2) {
                    System.out.print(" |");
                }
            }
            System.out.print("\n");
            if(i != 2) {
                System.out.print("---|---|---\n");
            }
        }
        System.out.print("\n");
    }
}
