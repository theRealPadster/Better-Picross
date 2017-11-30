package com.example.padster.betterpicross;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.padster.betterpicross.database.AppDatabase;
import com.example.padster.betterpicross.database.Level;
import com.example.padster.betterpicross.database.Score;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class LevelsActivity extends AppCompatActivity {

    private AppDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        database = AppDatabase.getDatabase(getApplicationContext());

        ListView levelsListView = (ListView) findViewById(R.id.levelListView);

        SimpleAdapter adapter = new SimpleAdapter(this,
                getLevelInfo(),
                R.layout.levels_listview_columns,
                new String[] { "levelId", "clues", "packId" },
                new int[] { R.id.column1, R.id.column2, R.id.column3});

        levelsListView.setAdapter(adapter);

        levelsListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                //TODO - is there a better way to grab the levelId column?
//                LinearLayout level = (LinearLayout)adapter.getItemAtPosition(position);
                LinearLayout level = (LinearLayout)v;
                TextView levelIdView = (TextView) level.getChildAt(0);
                int levelId = Integer.parseInt(levelIdView.getText().toString());

                Intent intent = new Intent(LevelsActivity.this, ScoresActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("levelId", levelId);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }

    List<Map<String, String>> getLevelInfo() {
        List<Map<String, String>> allLevels = new ArrayList<Map<String, String>>();


        List<Level> easyLevels = database.levelDao().getLevelsInPack(1);
        for (Level currLevel:easyLevels) {
            HashMap<String, String> currLevelMap = new HashMap<String, String>();
            currLevelMap.put("levelId", Integer.toString(currLevel.id));
            currLevelMap.put("clues", currLevel.clues);
            currLevelMap.put("packId", Integer.toString(currLevel.packId));

            allLevels.add(currLevelMap);
        }

        List<Level> medLevels = database.levelDao().getLevelsInPack(2);
        for (Level currLevel:medLevels) {
            HashMap<String, String> currLevelMap = new HashMap<String, String>();
            currLevelMap.put("levelId", Integer.toString(currLevel.id));
            currLevelMap.put("clues", currLevel.clues);
            currLevelMap.put("packId", Integer.toString(currLevel.packId));

            allLevels.add(currLevelMap);
        }

        List<Level> hardLevels = database.levelDao().getLevelsInPack(3);
        for (Level currLevel:hardLevels) {
            HashMap<String, String> currLevelMap = new HashMap<String, String>();
            currLevelMap.put("levelId", Integer.toString(currLevel.id));
            currLevelMap.put("clues", currLevel.clues);
            currLevelMap.put("packId", Integer.toString(currLevel.packId));

            allLevels.add(currLevelMap);
        }

        return allLevels;
    }
}
