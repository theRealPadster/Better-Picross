package com.example.padster.betterpicross;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by padster on 05/10/17.
 */

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }

}
