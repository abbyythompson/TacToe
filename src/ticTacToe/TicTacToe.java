package ticTacToe;

import com.sun.tools.internal.jxc.ap.Const;
import ticTacToe.printer.*;
import ticTacToe.gameEngine.*;
import java.util.*;
/**
 * Created by abbythompson
 */


public class TicTacToe {
    private BoardPrinter boardPrinter;
    private Game game;
    private Boolean quit = false;

    public static void main (String[] args){ new TicTacToe().play(); }

    public void play(){
            game = new Game();
            game.start();
            boardPrinter = new BoardPrinter(game);
            Scanner scanner = new Scanner(System.in);


            //Print welcome and board state.
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

                //get the move
                System.out.println(turn);
                String playersMove = scanner.nextLine();


                if(playersMove.contentEquals("q")){
                    game.setStatus(Status.FINISHED);
                    quit = true;
                    System.out.println(Constants.QUIT);
                } else if (playersMove.length() != 3){
                    System.out.println(Constants.INVALID_INPUT);

                } else {
                    //analyse move
                    String[] coord = playersMove.split(",");

                    if(coord.length != 2){
                        System.out.println(Constants.INVALID_INPUT);
                    } else {
                        int row = Integer.valueOf(coord[0]) - 1;
                        int col = Integer.valueOf(coord[1]) - 1;

                        if((row <= 2 && row >= 0) && (col <= 2 && col >= 0)){
                            //check for invalid input
                            if (game.coordIsFree(row, col)) {
                                System.out.println(Constants.TURN_ACCEPTED);

                                game.move(row, col);

                                boardPrinter.printBoard();

                                if (game.isWinner()) {
                                    game.setStatus(Status.FINISHED);
                                    System.out.println(Constants.TURN_ACCEPTED_WINNER);
                                }

                                game.changeTurn();
                            } else {
                                System.out.println(Constants.TURN_DECLINED);
                            }
                        } else {
                            System.out.println(Constants.INVALID_INPUT);
                        }
                    }
                }
            }

            if (!quit){
                //boardPrinter.printWinner();
            }
    }
}
