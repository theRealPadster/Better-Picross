package com.example.padster.betterpicross;


import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    GridLayout gridLayout;
    PicrossGrid solutionGrid = null;
    PicrossGrid gameGrid = null;
    Timer timer;
    int seconds = 0, minutes = 0, hour = 0;
    boolean isPaused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle bundle = getIntent().getExtras();

        int rows = bundle.getInt("rows");
        int cols = bundle.getInt("cols");

        //TODO
        //basically, I just need to generate clues. or a solution and then get clues from it i guess
        //but need to just check that it satisfies the clues, since there are >1 solutions
        solutionGrid = new PicrossGrid(rows, cols);

        //hook up timer TODO - (can i make it not final?)
        //TODO - also have to implement pause and save on exit
        final TextView timerValue = (TextView) findViewById(R.id.timerLbl);
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!isPaused) {
                            seconds += 1;
                            if (seconds == 60) {
                                minutes++;
                                if (minutes == 60) {
                                    hour++;
                                }
                                minutes = minutes % 60;
                                seconds = seconds % 60;

                                timerValue.setText(String.format("%02d", hour) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
                            }
                            timerValue.setText(String.format("%02d", hour) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
                        }
                        else {
                            timerValue.setText("----");
                        }

                    }
                });
            }
        }, 0, 1000);
        Button playPauseBtn = (Button) findViewById(R.id.playPauseBtn);
        playPauseBtn.setOnClickListener(this);


        //////////////////////////

        // get a reference for the TableLayout
        gridLayout = (GridLayout) findViewById(R.id.gameGridLayout);
//        gridLayout.setLayoutParams(new GridLayout.LayoutParams(GridLayout.LayoutParams.MATCH_PARENT, GridLayout.LayoutParams.MATCH_PARENT));
        gridLayout.setOrientation(GridLayout.HORIZONTAL);
        gridLayout.setColumnCount(cols);
        gridLayout.setRowCount(rows);



        for (int rowCounter = 0; rowCounter < rows; rowCounter++) {

            for (int colCounter = 0; colCounter < cols; colCounter++) {

                //create new button
                Button b = new Button(this);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                //TODO - tweak params




//                params.height = GridLayout.LayoutParams.WRAP_CONTENT;
//                params.width = GridLayout.LayoutParams.WRAP_CONTENT;
                params.rightMargin = 0;
                params.topMargin = 0;

                params.width = (gridLayout.getWidth()/gridLayout.getColumnCount()) -params.rightMargin - params.leftMargin;
                params.height = params.width;

                params.setGravity(Gravity.CENTER);
                params.columnSpec = GridLayout.spec(colCounter);
                params.rowSpec = GridLayout.spec(rowCounter);
                b.setLayoutParams(params);

                b.setText(colCounter + "," + rowCounter);
//                b.setOnClickListener(this);

                if (solutionGrid.getTile(rowCounter, colCounter) == PicrossGrid.Colours.BLACK) {
//                    b.setBackgroundColor(getResources().getColor(R.color.pink));
                    b.setBackgroundDrawable(new PaintDrawable(getResources().getColor(R.color.pink)));
                }
                else {
                    b.setBackgroundDrawable(new PaintDrawable(Color.parseColor("#EEEEEE")));
                }

                //TODO - is there a not gross way to do this?
                //TODO - i'd rather just have one listener and get the coordinates somehow... gridlayout?
                final int rowFinal = rowCounter;
                final int colFinal = colCounter;
                final Button bFinal = b;
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //        int[] locationOnScreen = new int[2];
                        //        view.getLocationOnScreen(locationOnScreen);
                        //        Toast.makeText(this, locationOnScreen[0] + ", " + locationOnScreen[1], Toast.LENGTH_SHORT).show();

                        //TODO - change the state of the button and the gameGrid to match...

                        String toastMsg = "(" + colFinal + ", " + rowFinal + ")";
                        PaintDrawable drawable = (PaintDrawable) bFinal.getBackground();

                        //if empty
                        if (drawable.getPaint().getColor() == Color.parseColor("#EEEEEE")) {
//                            toastMsg += " - #eee";
//                            gameGrid.setTile(rowFinal, colFinal, PicrossGrid.Colours.BLACK);
                        }
                        else { //if filled
//                            toastMsg += " - pink";
//                            gameGrid.setTile(rowFinal, colFinal, PicrossGrid.Colours.WHITE);
                        }

                        toastMsg += " - isSolved? " + isSolved();

                        Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_SHORT).show();
                    }
                });

                // add the button to the new TableRow
                gridLayout.addView(b);
            }
        }


    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(this, "TODO - save the game somehow before navigating away", Toast.LENGTH_LONG).show();
    }

    private boolean isSolved() {

        //TODO - either check if row/column clues are satisfied or if button status and arrays are the same?

        //TODO - pretty sure this grid is the same object, so always equal...
        //TODO - == or .equals()?
        if (solutionGrid == gameGrid) {
            return true;
        }


        return false;
    }

    @Override
    public void onClick(View view) {
        //TODO - check which button, right now it's just play/pause
        Button playPauseBtn = (Button) view;

        if (isPaused) {
            playPauseBtn.setText(getResources().getString(R.string.Pause));
            gridLayout.setAlpha(1);
            isPaused = false;
        }
        else {
            //TODO - show a "paused" message instead of blank space
            playPauseBtn.setText(getResources().getString(R.string.Play));
            gridLayout.setAlpha(0);
            isPaused = true;
        }
    }
}
