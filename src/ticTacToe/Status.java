package ticTacToe;

/**
 * Enums for the state of the game.
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
