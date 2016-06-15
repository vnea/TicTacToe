package com.orsay.tictactoe.activities;

import android.app.Activity;
import android.os.Bundle;

import com.orsay.tictactoe.R;
import com.orsay.tictactoe.engine.Menu;
import com.orsay.tictactoe.tools.Utils;


public class TicTacToeMainActivity extends Activity {
    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Utils.hideActionBar(this);
        setContentView(R.layout.activity_tic_tac_toe_main);

        Menu menu = new Menu(this);
    }
}
