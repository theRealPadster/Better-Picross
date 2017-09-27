package com.example.padster.betterpicross;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.TextView;

public class GenerateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);

        //Get the widgets reference from XML layout
        NumberPicker rowsPicker = (NumberPicker) findViewById(R.id.rowsPicker);
        NumberPicker colsPicker = (NumberPicker) findViewById(R.id.colsPicker);


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


}
