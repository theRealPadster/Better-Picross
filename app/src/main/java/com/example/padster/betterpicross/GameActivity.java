package com.example.padster.betterpicross;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle bundle = getIntent().getExtras();

        int rows = bundle.getInt("rows");
        int cols = bundle.getInt("cols");

        //////////////////////////

        // get a reference for the TableLayout
        TableLayout table = (TableLayout) findViewById(R.id.gameTableLayout);

        for (int rowCounter = 0; rowCounter < rows; rowCounter++) {

            // create a new TableRow
            TableRow row = new TableRow(this);

            for (int colCounter = 0; colCounter < cols; colCounter++) {

                //create new button
                Button b = new Button(this);
                b.setLayoutParams(new TableRow.LayoutParams(0,TableRow.LayoutParams.WRAP_CONTENT));
                b.setText(colCounter + "," + rowCounter);

                // add the button to the new TableRow
                row.addView(b);
            }

            // add the TableRow to the TableLayout
            table.addView(row,new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));

        }


    }
}
