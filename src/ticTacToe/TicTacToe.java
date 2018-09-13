package ticTacToe;

import ticTacToe.printer.*;
import ticTacToe.gameEngine.*;
import java.util.*;
/**
 * Main Tic Tac Toe Class that handles the input and output of the game.
 */
public class TicTacToe {
    private BoardPrinter boardPrinter;
    private Game game;

    public static void main (String[] args){ new TicTacToe().play(); }

    public void play(){
        game = new Game();
        game.start();
        boardPrinter = new BoardPrinter(game);
        Scanner scanner = new Scanner(System.in);


        // Print welcome and initial board state.
        System.out.println(Constants.WELCOME);
        boardPrinter.printBoard();


        while(Status.FINISHED != game.getStatus()) {

            String turn = "";
            switch (game.getStatus()) {
                case PLAYER1_TURN:
                    turn = Constants.P1_TURN;
                    break;
                case PLAYER2_TURN:
                    turn = Constants.P2_TURN;
                    break;
            }

            // Get the move from the user with scanner.
            System.out.println(turn);
            String playersMove = scanner.nextLine();

            if(playersMove.contentEquals("q")){  // PLAYER QUITS THE GAME
                game.setStatus(Status.FINISHED);
                System.out.println(Constants.QUIT);

            } else if (playersMove.length() != 3){   // PLAYER GIVES INVALID INPUT
                System.out.println(Constants.INVALID_INPUT);

            } else {
                String[] coord = playersMove.split(",");

                if(coord.length != 2){
                    System.out.println(Constants.INVALID_INPUT); // PLAYER GIVES INVALID INTEGERS

                } else {
                    int row = Integer.valueOf(coord[0]) - 1;
                    int col = Integer.valueOf(coord[1]) - 1;

                    if((row <= 2 && row >= 0) && (col <= 2 && col >= 0)){

                        if (game.coordIsFree(row, col)) {

                            System.out.println(Constants.TURN_ACCEPTED);

                            // Update the gameState and print the board to reflect their move.
                            game.move(row, col);
                            boardPrinter.printBoard();

                            // Check if this move is a winning move.
                            if (game.isWinner()) {
                                game.setStatus(Status.FINISHED);
                                System.out.println(Constants.TURN_WINNER);
                            } else if (game.isDraw()){
                                game.setStatus(Status.FINISHED);
                                System.out.println(Constants.TURN_DRAW);
                            }

                            // Change the status to the next player's turn
                            game.changeTurn();

                        } else { // PLAYER GIVES INPUT FOR SPOT THAT ISN'T VACANT
                            System.out.println(Constants.TURN_DECLINED);
                        }

                    } else { // PLAYER GIVES INVALID INTEGERS
                        System.out.println(Constants.INVALID_INPUT);
                    }
                }
            }
        }
    }
}
