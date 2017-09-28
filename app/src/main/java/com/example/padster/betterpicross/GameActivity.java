package com.example.padster.betterpicross;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle bundle = getIntent().getExtras();

        int rows = bundle.getInt("rows");
        int cols = bundle.getInt("cols");

        TextView rowsTextView = (TextView) findViewById(R.id.rows);
        TextView colsTextView = (TextView) findViewById(R.id.cols);

        rowsTextView.setText(Integer.toString(rows));
        colsTextView.setText(Integer.toString(cols));

    }
}
