package com.example.padster.betterpicross;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

public class GenerateActivity extends AppCompatActivity {

    NumberPicker rowsPicker, colsPicker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);

        //Get the widgets reference from XML layout
        rowsPicker = (NumberPicker) findViewById(R.id.rowsPicker);
        colsPicker = (NumberPicker) findViewById(R.id.colsPicker);


        //Populate NumberPicker values from minimum and maximum value range
        //Set the minimum value of NumberPicker
        rowsPicker.setMinValue(3);
        colsPicker.setMinValue(3);
        //Specify the maximum value/number of NumberPicker
        rowsPicker.setMaxValue(10);
        colsPicker.setMaxValue(10);

        //Gets whether the selector wheel wraps when reaching the min/max value.
        rowsPicker.setWrapSelectorWheel(false);
        colsPicker.setWrapSelectorWheel(false);

        //Set a value change listener for NumberPicker
        rowsPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker
                //tv.setText("Selected Number : " + newVal);
            }
        });
    }

    public void generateClicked(View v) {
        Bundle bundle = new Bundle();
        bundle.putInt("rows", rowsPicker.getValue());
        bundle.putInt("cols", colsPicker.getValue());

        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);

    }


}
