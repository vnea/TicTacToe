package com.orsay.tictactoe.engine;


import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import com.orsay.tictactoe.R;
import com.orsay.tictactoe.tools.Utils;


public class Board implements View.OnTouchListener {
    /**
     * Status of the game
     */
    private enum StatusGame {
        NOT_FINISHED,
        TIE,
        WON
    }

    int mode;
    private Grid grid;
    private TextView textViewStatusGame;

    public static final String CROSS_SYMBOL = "X";
    public static final String CIRCLE_SYMBOL = "O";
    private String actualSymbol;
    private boolean endOfGame;

    /**
     *
     * @param activity Activity
     */
    public Board(Activity activity, int mode) {
        // Get the mode, create the grid, get the text view
        this.mode = mode;
        grid = new Grid(activity);
        textViewStatusGame = (TextView) activity.findViewById(R.id.textViewStatusGame);

        // Get the button new game
        Button buttonNewGame;
        buttonNewGame = (Button) activity.findViewById(R.id.buttonNewGame);
        buttonNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });

        // New game
        resetGame();

        // "Main loop"
        TableLayout tableLayoutGame = (TableLayout) activity.findViewById(R.id.tableLayoutGame);
        tableLayoutGame.setOnTouchListener(this);
    }

    /**
     * Resets game
     */
    private void resetGame() {
        endOfGame = false;
        actualSymbol = CROSS_SYMBOL;
        textViewStatusGame.setText("");
        grid.empty();
    }

    /**
     * "Main loop" of the game
     * @param x line index
     * @param y column index
     */
    private void play(int x, int y) {
        /*
            As grid, textViewStatusGame and buttonNewGame are in the same
            TableLayout, we have to check if we that the player has touched
            a tile to play (and not textViewStatusGame for example).
            Moreover, we can only play if the game is not finished.
         */
        if (x < Grid.NB_LINES && y < Grid.NB_COLUMNS && !endOfGame) {
            Tile tile = grid.getTile(x ,y);

            // We only can play if the touched tile is not empty
            if (tile.isEmpty()) {
                fillTile(tile);
                manageEndOfTurn(x, y, true);

                // 1 player mode : Human vs computer
                if (!endOfGame && mode == TicTacToe.Mode.HUMAN_VS_COMPUTER.ordinal()) {
                    int [] xComputerChoice = new int[1];
                    int [] yComputerChoice = new int[1];

                    fillTile(computerPlay(xComputerChoice, yComputerChoice));
                    manageEndOfTurn(xComputerChoice[0], yComputerChoice[0], false);
                }
            }
        }
    }

    /**
     * Basic AI
     * @param x line index
     * @param y column index
     * @return Chosen tile by AI
     */
    private Tile computerPlay(int[] x, int[] y) {
        int maxX = Grid.NB_LINES - 1;
        int maxY = Grid.NB_COLUMNS - 1;
        Tile randomTile;

        // We search a tile while the tile found is not empty
        do {
            // Generate a number between [0, 2]
            x[0] = Utils.randInt(0, maxX);
            y[0] = Utils.randInt(0, maxY);

            randomTile = grid.getTile(x[0], y[0]);
        }  while (!randomTile.isEmpty());

        return randomTile;
    }
    /**
     * Fill the tile and increase number of filled tiles
     * @param tile Tile to update
     */
    private void fillTile(Tile tile) {
        tile.update(actualSymbol);
        grid.increaseNumberFilledTiles();
    }

    /**
     *
     * @param x line index
     * @param y column index
     * @param humanTurn Human turn or not
     */
    private void manageEndOfTurn(int x, int y, boolean humanTurn) {
        StatusGame statusGame = statusGame(x, y);
        // Case when someone wins
        if (statusGame == StatusGame.WON) {
            // 1 player mode
            if (mode == TicTacToe.Mode.HUMAN_VS_COMPUTER.ordinal()) {
                // If it was a human turn
                if (humanTurn) {
                    updateTextView("You win !");
                }
                // Computer turns
                else {
                    updateTextView("You lose !");
                }
            }
            // 2 players mode
            else {
                updateTextView(actualSymbol + " wins !");
            }

            endOfGame = true;
        }
        // Case when it's tie
        else if (statusGame == StatusGame.TIE) {
            textViewStatusGame.setText("Tie !");
            endOfGame = true;
        }
        // Game not finish : next turn
        else {
            nextTurn();
        }
    }

    /**
     * Updates text view content
     * @param message Message to set in the text view
     */
    private void updateTextView(String message) {
        textViewStatusGame.setText(message);
    }

    /**
     * Triggers "play(...)" method
     * @param view View
     * @param motionEvent Motion event
     * @return boolean
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        // We get the touched tile
        int x = (int) motionEvent.getX() / grid.getTileWidth();
        int y = (int) motionEvent.getY() / grid.getTileHeight();

        play(x, y);

        return false;
    }

    /**
     * Switches symbol from cross/circle to circle/cross
     */
    private void nextTurn() {
        actualSymbol = actualSymbol.equals(CROSS_SYMBOL) ? CIRCLE_SYMBOL : CROSS_SYMBOL;
    }

    /**
     * Checks victory the current on vertical line
     * @param y column index
     * @return Returns true if victory
     */
    private boolean checkVictoryOnVerticalLine(int y) {
        Tile middleTile = grid.getTile(1, y);

        return grid.getTile(0, y).equals(middleTile) &&
               middleTile.equals(grid.getTile(2, y));
    }

    /**
     * Checks victory on the current horiontal line
     * @param x line index
     * @return Returns true if victory
     */
    private boolean checkVictoryOnHorizontalLine(int x) {
        Tile middleTile = grid.getTile(x, 1);

        return grid.getTile(x, 0).equals(middleTile) &&
               middleTile.equals(grid.getTile(x, 2));
    }

    /**
     * Checks victory on main diagonal if needed
     * @param x line index
     * @param y column index
     * @return Returns true if victory
     */
    private boolean checkVictoryOnMainDiagonal(int x, int y) {
        // If needed
        if (x == y) {
            Tile middleTile = grid.getTile(1, 1);

            return grid.getTile(0, 0).equals(middleTile) &&
                    middleTile.equals(grid.getTile(2, 2));
        }

        return false;
    }

    /**
     * Checks victory on secondary diagonal if needed
     * @param x line index
     * @param y column index
     * @return Returns true if victory
     */
    private boolean checkVictoryOnSecondaryDiagonal(int x, int y) {
        // If needed
        if (x + y == 2) {
            Tile middleTile = grid.getTile(1, 1);

            return grid.getTile(0, 2).equals(middleTile) &&
                    middleTile.equals(grid.getTile(2, 0));
        }

        return false;
    }

    /**
     * Returns status of game
     * @param x line index
     * @param y column index
     * @return Returns the status of the game
     */
    private StatusGame statusGame(int x, int y) {
        /*
            Check victory
         */
        if (checkVictoryOnVerticalLine(y) || checkVictoryOnHorizontalLine(x) ||
            checkVictoryOnMainDiagonal(x, y) || checkVictoryOnSecondaryDiagonal(x, y)) {
            return StatusGame.WON;
        }

        // Nobody wins but grid is full = tie
        if (grid.isFull()) {
            return StatusGame.TIE;
        }

        // Game not finished yet
        return StatusGame.NOT_FINISHED;
    }
}
