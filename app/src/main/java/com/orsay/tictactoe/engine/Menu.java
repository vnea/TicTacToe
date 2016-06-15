package com.orsay.tictactoe.engine;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.orsay.tictactoe.R;
import com.orsay.tictactoe.activities.AboutActivity;
import com.orsay.tictactoe.activities.GameActivity;


public class Menu {
    /**
     *
     * @param activity Activity
     */
    public Menu(final Activity activity) {
        // For each button, we "link" an intent to launch its specific activity

        // One player mode
        Button buttonOnePlayerMode = (Button) activity.findViewById(R.id.buttonOnePlayerMode);
        buttonOnePlayerMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                launchActivity(activity, GameActivity.class, TicTacToe.Mode.HUMAN_VS_COMPUTER);
            }
        });

        // Two player mode
        Button buttonTwoPlayersMode = (Button) activity.findViewById(R.id.buttonTwoPlayersMode);
        buttonTwoPlayersMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                launchActivity(activity, GameActivity.class, TicTacToe.Mode.HUMAN_VS_HUMAN);
            }
        });

        // About
        Button buttonAbout = (Button) activity.findViewById(R.id.buttonAbout);
        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                launchActivity(activity, AboutActivity.class);
            }
        });
    }

    /**
     * Launch an activity by giving a class activity
     * @param cls Class activity
     */
    private void launchActivity(Activity activity, Class<?> cls) {
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
    }

    /**
     * Launch an activity by giving a class activity and a message
     * @param cls Class activity
     * @param mode Mode game
     */
    private void launchActivity(Activity activity, Class<?> cls, TicTacToe.Mode mode) {
        Intent intent = new Intent(activity, cls);
        intent.putExtra(activity.getResources().getResourceName(R.string.mode), mode.ordinal());
        activity.startActivity(intent);
    }
}
