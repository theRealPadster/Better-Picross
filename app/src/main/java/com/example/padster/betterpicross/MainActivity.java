package com.example.padster.betterpicross;

import android.content.Intent;
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
