package com.example.padster.betterpicross;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.PaintDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    PicrossGrid solutionGrid = null;
    PicrossGrid gameGrid = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle bundle = getIntent().getExtras();

        int rows = bundle.getInt("rows");
        int cols = bundle.getInt("cols");

        solutionGrid = new PicrossGrid(rows, cols);
        gameGrid = solutionGrid; //TODO - does this use same pointer or new array? i need to modify separately...

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
                            gameGrid.setTile(rowFinal, colFinal, PicrossGrid.Colours.BLACK);
                        }
                        else { //if filled
//                            toastMsg += " - pink";
                            gameGrid.setTile(rowFinal, colFinal, PicrossGrid.Colours.WHITE);
                        }

                        toastMsg += " - isSolved? " + isSolved();

                        Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_SHORT).show();
                    }
                });

                // add the button to the new TableRow
                row.addView(b);
            }

            // add the TableRow to the TableLayout
            table.addView(row,new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));

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
}
