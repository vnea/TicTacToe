package com.orsay.tictactoe.activities;

import android.app.Activity;
import android.os.Bundle;

import com.orsay.tictactoe.R;
import com.orsay.tictactoe.engine.TicTacToe;
import com.orsay.tictactoe.tools.Utils;

public class GameActivity extends Activity {
    /**
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Utils.hideActionBar(this);
        setContentView(R.layout.activity_game);

        // We get the mode game sent by the intent
        int mode = getIntent().getIntExtra(getResources().getResourceName(R.string.mode), 0);
        TicTacToe gameTicTacToe = new TicTacToe(this, mode);
    }

}
