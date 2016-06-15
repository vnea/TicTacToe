package com.orsay.tictactoe.engine;


import android.graphics.Color;
import android.widget.TextView;

public class Tile {
    private TextView textView;

    /**
     *
     * @param textView TextView
     */
    public Tile(TextView textView) {
        this.textView = textView;
    }

    /**
     * Check if ths tile is equaled to another
     * @param object Tile
     * @return Returns true if tiles are equaled
     */
    @Override
    public boolean equals(Object object) {
        return textView.getText().equals(((Tile) object).textView.getText());
    }

    /**
     * Update text and color
     * @param symbol Symbol
     */
    public void update(String symbol) {
        textView.setText(symbol);

        // Blue color for cross
        if (symbol.equals(Board.CROSS_SYMBOL)) {
            textView.setTextColor(Color.BLUE);
        }
        // Red color for circle
        else if (symbol.equals(Board.CIRCLE_SYMBOL)) {
            textView.setTextColor(Color.RED);
        }
    }

    /**
     * Empties tile
     */
    public void empty() {
        update("");
    }

    /**
     * Returns width
     * @return Return width
     */
    public int getWidth() {
        return textView.getWidth();
    }

    /**
     * Returns height
     * @return Returns width
     */
    public int getHeight() {
        return textView.getHeight();
    }

    /**
     * Check if tile is empty or not
     * @return Returns true if tile is empty
     */
    public boolean isEmpty() {
        return textView.getText().length() == 0;
    }
}
