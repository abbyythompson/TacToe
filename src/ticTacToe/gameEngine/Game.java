package ticTacToe.gameEngine;

import ticTacToe.*;
/**
 * Created by abbythompson on 13/09/18.
 */
public class Game {
    protected int[][] boardState;
    private Status status;

    public Game() {
        this.boardState = new int[3][3];
        this.status = Status.INIT;
    }

    public void move(int i, int j){
        switch (status) {
            case PLAYER1_TURN:
                boardState[i][j] = 1;
                break;
            case PLAYER2_TURN:
                boardState[i][j] = 2;
                break;
        };
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

    //should just check from the last move
    public void checkForWinner(){
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

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++) {
                
            }
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
}
