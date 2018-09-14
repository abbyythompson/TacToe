package ticTacToe.gameEngine;

import ticTacToe.*;
import ticTacToe.printer.*;
/**
 * Game Engine to run the backend of the game and update the board.
 */
public class Game {
    protected int[][] boardState;
    private Status status;
    private int numberTurns = 0;

    public Game() {
        this.boardState = new int[3][3];
        this.status = Status.INIT;
    }

    public boolean coordIsFree(int i, int j){
        if(boardState[i][j] != 0){
            return false;
        }

        return true;
    }

    public void processMove(int i, int j){
        switch (status) {
            case PLAYER1_TURN:
                boardState[i][j] = 1;
                break;
            case PLAYER2_TURN:
                boardState[i][j] = 2;
                break;
        };

        numberTurns++;
    }

    public void changeTurn(){
        switch (status) {
            case PLAYER1_TURN:
                status = Status.PLAYER2_TURN;
                break;
            case PLAYER2_TURN:
                status = Status.PLAYER1_TURN;
                break;
        };
    }

    public boolean isWinner(){
        int player = 0;
        switch (status) {
            case PLAYER1_TURN:
                player = 1;
                break;
            case PLAYER2_TURN:
                player = 2;
                break;
        };

        int tally = 0;

        for(int row=0; row<3; row++){
            for(int col=0; col<3; col++) {
                if(boardState[row][col] == player){
                    tally++;
                } else {
                    col = 3;
                    tally = 0;
                }
            }
            if (tally == 3){
                return true;
            } else {
                tally = 0;
            }
        }

        for(int j=0; j<3; j++){
            for(int i=0; i<3; i++) {
                if(boardState[i][j] == player){
                    tally++;
                } else {
                    i = 3;
                    tally = 0;
                }
            }
            if (tally == 3){
                return true;
            } else {
                tally = 0;
            }
        }

        if(boardState[1][1] == player){
            if (boardState[0][0] == player && boardState[2][2] == player){
                return true;
            } else if (boardState[0][2] == player && boardState[2][0] == player){
                return true;
            }
        }

        return false;
    }

    public boolean isDraw() {
        if(numberTurns > 8){
            return true;
        } else {
            return false;
        }
    }

    public int[][] getBoardState() {
        return boardState;
    }

    public void start(){
            status = Status.PLAYER1_TURN;
    }

    public Status getStatus(){
        return status;
    }
    public void setStatus(Status s){
        status = s;
    }

    public String getTurn() {
        String turn = "";

        switch (status) {
            case PLAYER1_TURN:
                turn = Constants.P1_TURN;
            break;
            case PLAYER2_TURN:
                turn = Constants.P2_TURN;
            break;
        }

        return turn;
    }
}
