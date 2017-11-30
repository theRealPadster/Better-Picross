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

public class ScoresActivity extends AppCompatActivity {

    private AppDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        Bundle bundle = getIntent().getExtras();

        int levelId = bundle.getInt("levelId");

        database = AppDatabase.getDatabase(getApplicationContext());

        ListView levelsListView = (ListView) findViewById(R.id.levelListView);

        SimpleAdapter adapter = new SimpleAdapter(this,
                getScoresForLevel(levelId),
                R.layout.levels_listview_columns,
                new String[] { "id", "time", "levelId" },
                new int[] { R.id.column1, R.id.column2, R.id.column3});

        levelsListView.setAdapter(adapter);
    }

    List<Map<String, String>> getScoresForLevel(int levelId) {

        List<Map<String, String>> allScores = new ArrayList<Map<String, String>>();

        List<Score> scoresList = database.scoreDao().getScoresForLevel(levelId);

        for (Score currScore:scoresList) {
            HashMap<String, String> currScoreMap = new HashMap<String, String>();
            currScoreMap.put("id", Integer.toString(currScore.id));
            currScoreMap.put("time", currScore.time);
            currScoreMap.put("levelId", Integer.toString(currScore.levelId));

            allScores.add(currScoreMap);
        }

        return allScores;
    }
}
