package ticTacToe;

/**
 * Created by abbythompson on 13/09/18.
 */
public enum Status {

    /** Game has not started yet. */
    INIT,
    /** HumanPlayer 1's turn. */
    PLAYER1_TURN,
    /** HumanPlayer 2's turn. */
    PLAYER2_TURN,
    /** Game has finished. */
    FINISHED;

    Status() {
    }
}
