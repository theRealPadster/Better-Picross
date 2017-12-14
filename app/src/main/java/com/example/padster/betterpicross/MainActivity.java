package com.example.padster.betterpicross;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.padster.betterpicross.database.AppDatabase;
import com.example.padster.betterpicross.database.Level;
import com.example.padster.betterpicross.database.Pack;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button continueBtn = (Button) findViewById(R.id.continueBtn);
        Button newBtn = (Button) findViewById(R.id.newBtn);
        Button generateBtn = (Button) findViewById(R.id.generateBtn);
        Button tutorialBtn = (Button) findViewById(R.id.tutorialBtn);
        Button settingsBtn = (Button) findViewById(R.id.settingsBtn);
        Button aboutBtn = (Button) findViewById(R.id.aboutBtn);

        continueBtn.setOnClickListener(this);
        newBtn.setOnClickListener(this);
        generateBtn.setOnClickListener(this);
        tutorialBtn.setOnClickListener(this);
        settingsBtn.setOnClickListener(this);
        aboutBtn.setOnClickListener(this);

        database = AppDatabase.getDatabase(getApplicationContext());

        // cleanup for testing some initial data
//        database.packDao().removeAllPacks();
        // add some data
        List<Pack> packs = database.packDao().getAllPacks();
        if (packs.size()==0) {
            database.packDao().addPack(new Pack(1, "Easy"));
            Pack pack = database.packDao().getAllPacks().get(0);
            Level level = new Level(1, "easy1", pack.id);
            database.levelDao().addLevel(level);
            database.levelDao().addLevel(new Level(2, "easy2", pack.id));
            database.packDao().addPack(new Pack(2, "Medium"));
            database.levelDao().addLevel(new Level(3, "med1", 2));
            database.levelDao().addLevel(new Level(4, "med2", 2));
            database.levelDao().addLevel(new Level(5, "med3", 2));
            database.packDao().addPack(new Pack(3, "Hard"));
            database.levelDao().addLevel(new Level(6, "hard1", 3));
        }

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

        if (Build.VERSION.SDK_INT >= 26) {
            //add notification channel
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            // The id of the channel.
            String id = getString(R.string.channel_id);
            // The user-visible name of the channel.
            CharSequence name = getString(R.string.channel_name);
            // The user-visible description of the channel.
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(id, name, importance);
            // Configure the notification channel.
            mChannel.setDescription(description);
            mChannel.enableLights(true);
            // Sets the notification light color for notifications posted to this
            // channel, if the device supports this feature.
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mNotificationManager.createNotificationChannel(mChannel);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.continueBtn:
                Toast.makeText(this, "\"Continue\" not implemented yet", Toast.LENGTH_SHORT).show();
                return;
            case R.id.newBtn:
                intent = new Intent(this, LevelsActivity.class);
                break;
            case R.id.generateBtn:
                intent = new Intent(this, GenerateActivity.class);
                break;
            case R.id.tutorialBtn:
                intent = new Intent(this, TutorialActivity.class);
                break;
            case R.id.settingsBtn:
                intent = new Intent(this, SettingsActivity.class);
                break;
            case R.id.aboutBtn:
                intent = new Intent(this, AboutActivity.class);
                break;
        }

        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }

}
