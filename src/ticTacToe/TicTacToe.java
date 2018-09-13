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
                    System.out.println(Constants.TURN_ACCEPTED);

                    //analyse move
                    String[] coord = playersMove.split(",");

                    //check for invalid input
                    game.move(Integer.valueOf(coord[0]) - 1, Integer.valueOf(coord[1]) - 1);
                    game.checkForWinner();

                    game.changeTurn();
                    boardPrinter.printBoard();
                }
            }

            if (!quit){
                //boardPrinter.printWinner();
            }
    }
}
