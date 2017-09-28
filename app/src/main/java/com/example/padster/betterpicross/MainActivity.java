package com.example.padster.betterpicross;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button aboutBtn = (Button) findViewById(R.id.aboutBtn);
        Button tutorialBtn = (Button) findViewById(R.id.tutorialBtn);
        Button generateBtn = (Button) findViewById(R.id.generateBtn);
        aboutBtn.setOnClickListener(this);
        tutorialBtn.setOnClickListener(this);
        generateBtn.setOnClickListener(this);


        //  Declare a new thread to do a preference check
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //  Initialize SharedPreferences
                SharedPreferences getPrefs = PreferenceManager
                        .getDefaultSharedPreferences(getBaseContext());

                //  Create a new boolean and preference and set it to true
                boolean isFirstStart = getPrefs.getBoolean("firstStart", true);

                //  If the activity has never started before...
                if (isFirstStart) {

                    //  Launch app intro
                    final Intent i = new Intent(MainActivity.this, TutorialActivity.class);

                    runOnUiThread(new Runnable() {
                        @Override public void run() {
                            startActivity(i);
                        }
                    });

                    //  Make a new preferences editor
                    SharedPreferences.Editor e = getPrefs.edit();

                    //  Edit preference to make it false because we don't want this to run again
                    e.putBoolean("firstStart", false);

                    //  Apply changes
                    e.apply();
                }
            }
        });

        // Start the thread
        t.start();

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.aboutBtn:
                intent = new Intent(this, AboutActivity.class);
                break;
            case R.id.tutorialBtn:
                intent = new Intent(this, TutorialActivity.class);
                break;
            case R.id.generateBtn:
                intent = new Intent(this, GenerateActivity.class);
                break;
        }

        startActivity(intent);
    }
}
