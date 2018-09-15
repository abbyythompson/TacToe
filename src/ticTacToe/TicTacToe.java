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

    private final int EXPECTED_INPUT_LENGTH = 3;
    private final int NUM_OF_COORDS = 2;
    private final int MIN_X_VALUE = 0, MIN_Y_VALUE = 0, MAX_X_VALUE = 2, MAX_Y_VALUE = 2;

    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);

        boolean wantToPlay = true;

        while(wantToPlay) {
            new TicTacToe().play(scanner);

            System.out.println(Constants.PLAY_AGAIN);

            switch (scanner.nextLine()) {
                case "n":
                    wantToPlay = false;
                    break;
                case "no":
                    wantToPlay = false;
                    break;
                case "yes":
                    wantToPlay = true;
                    break;
                case "y":
                    wantToPlay = true;
                    break;
                case "Yes":
                    wantToPlay = true;
                    break;
                case "Y":
                    wantToPlay = true;
                    break;
                default: wantToPlay = false;
                    break;
            }
        }
    }

    public void play(Scanner scanner){
        game = new Game();
        game.start();
        boardPrinter = new BoardPrinter(game);


        System.out.println(Constants.WELCOME);
        boardPrinter.printBoard();

        while(Status.FINISHED != game.getStatus()) {


            // Get the move from the user with scanner.
            System.out.println(game.getTurn());
            String playersMove = scanner.nextLine();


            if(playersMove.contentEquals("q")) {  // PLAYER QUITS THE GAME
                game.setStatus(Status.FINISHED);
                System.out.println(Constants.QUIT);

            }

            int[] coord = isValidMove(playersMove);


            if (coord != null){
                System.out.println(Constants.TURN_ACCEPTED);

                // Update the gameState and print the board to reflect their move.
                game.processMove(coord[0], coord[1]);

                boardPrinter.printBoard();

                checkWinner();

                game.changeTurn();
            }
        }
    }

    public int[] isValidMove(String input) {
        int[] output = new int[2];

        if (input.length() != EXPECTED_INPUT_LENGTH) {
            System.out.println(Constants.INVALID_INPUT);
            return null;

        } else {
            String[] coord = input.split(",");

            if (coord.length != NUM_OF_COORDS) {
                System.out.println(Constants.INVALID_INPUT);
                return null;

            } else {
                output[0] = Integer.valueOf(coord[0]) - 1;
                output[1] = Integer.valueOf(coord[1]) - 1;

                if(output[0] > MAX_X_VALUE && output[0] > MIN_X_VALUE){
                    System.out.println(Constants.INVALID_INPUT);
                    return null;
                } else if (output[1] > MAX_Y_VALUE && output[1] > MIN_Y_VALUE) {
                    System.out.println(Constants.INVALID_INPUT);
                    return null;
                }
            }

            if (!game.coordIsFree(output[0], output[1])) {
                System.out.println(Constants.TURN_DECLINED);
                return null;
            }
        }

        return output;
    }

    public void checkWinner(){
        // Check if this move is a winning move.
        if (game.isWinner()) {
            game.setStatus(Status.FINISHED);
            System.out.println(Constants.TURN_WINNER);
        } else if (game.isDraw()){
            game.setStatus(Status.FINISHED);
            System.out.println(Constants.TURN_DRAW);
        }
    }

}
