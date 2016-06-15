package com.orsay.tictactoe.engine;


import android.app.Activity;
import android.widget.TextView;

import com.orsay.tictactoe.tools.Utils;

import java.util.ArrayList;

public class Grid {
    public static int NB_LINES = 3;
    public static int NB_COLUMNS = 3;
    public static int NB_TILES = NB_LINES * NB_COLUMNS;

    public ArrayList<Tile> tiles;
    private int nbFilledTiles;

    /**
     *
     * @param activity Activity
     */
    public Grid(Activity activity) {
        // We load all tiles
        loadTiles(activity);
    }

    /**
     * Empties the grid
     */
    public void empty() {
        for (int i = 0; i < NB_TILES; ++i) {
            tiles.get(i).empty();
        }
        nbFilledTiles = 0;
    }

    /**
     * Loads tiles of the game into the grid
     * @param activity Activity
     */
    private void loadTiles(Activity activity) {
        /*
            A loop to load the tiles into the grid.
            It's faster to write this then load tile by tile into the grid.
            That's why I created the function Utils.findViewById(...)
         */
        tiles = new ArrayList<Tile>(NB_TILES);
        for (int i = 0; i < NB_TILES; ++i) {
            String nameId = "textView" + Integer.toString(i + 1);
            tiles.add(new Tile((TextView) Utils.findViewById(activity, nameId)));
        }
    }

    /**
     * Returns a tile
     * @param x line index
     * @param y column index
     * @return Tile
     */
    public Tile getTile(int x, int y) {
        return tiles.get(x + y * NB_COLUMNS);
    }

    /**
     * Returns width of tiles
     * @return Returns width of tiles
     */
    public int getTileWidth() {
        return tiles.get(0).getWidth();
    }

    /**
     * Returns height of tiles
     * @return Returns height of tiles
     */
    public int getTileHeight() {
        return tiles.get(0).getHeight();
    }

    /**
     * Checks if grid is full or not
     * @return Returns true if the grid is full
     */
    public boolean isFull() {
        return nbFilledTiles == NB_TILES;
    }

    /**
     * Increase number of filled tiles by 1
     */
    public void increaseNumberFilledTiles() {
        ++nbFilledTiles;
    }
}
