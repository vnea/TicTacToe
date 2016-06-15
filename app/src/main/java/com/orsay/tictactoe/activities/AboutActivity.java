package com.orsay.tictactoe.activities;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.orsay.tictactoe.R;
import com.orsay.tictactoe.tools.Utils;

import java.io.IOException;


public class AboutActivity extends Activity {

    /**
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Utils.hideActionBar(this);
        setContentView(R.layout.activity_about);

        final MediaPlayer mp = new MediaPlayer();
        final RelativeLayout relativeLayoutAbout = (RelativeLayout) findViewById(R.id.relativeLayoutAbout);
        Button buttonTap = (Button) findViewById(R.id.buttonTap);

        buttonTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayoutAbout.setBackgroundResource(R.drawable.image);

                if (mp.isPlaying()) {
                    mp.stop();
                    mp.reset();
                }
                try {
                    AssetFileDescriptor afd;
                    afd = getAssets().openFd("sound.mp3");

                    mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                    mp.prepare();
                    mp.start();
                }
                catch (IllegalStateException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
