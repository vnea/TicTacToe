package com.orsay.tictactoe.tools;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import java.util.Random;

/**
 * Created by Victor
 */
public class Utils {

    /**
     * Source : https://developer.android.com/training/system-ui/status.html
     * Hide action bar
     * @param activity
     */
    @SuppressLint("InlinedApi")
    public static void hideActionBar(Activity activity) {
        // For API version < 16
        if (Build.VERSION.SDK_INT < 16) {
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        // For API version >= 16
        else {
            // Remember that you should never show the action bar if the
            // status bar is hidden, so hide that too if necessary.
            ActionBar actionBar = activity.getActionBar();
            actionBar.hide();
        }
    }

    /**
     * Find an Android View by giving its id in String format
     * @param activity
     * @param nameId
     * @return id
     */
    public static View findViewById(Activity activity, String nameId) {
        return activity.findViewById(activity.getResources().getIdentifier(nameId, "id", "com.orsay.tictactoe"));
    }

    /**
     *  Returns an integer between min and max
     * @param min Start
     * @param max End
     * @return
     */
    public static int randInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }
}
