package com.orsay.tictactoe.engine;


import android.app.Activity;

public class TicTacToe {
    /**
     * Mode game
     */
    public enum Mode {
        HUMAN_VS_HUMAN,
        HUMAN_VS_COMPUTER
    }

    /**
     *
     * @param activity Activity
     * @param mode Mode game
     */
    public TicTacToe(Activity activity, int mode) {
        Board board = new Board(activity, mode);
    }
}
