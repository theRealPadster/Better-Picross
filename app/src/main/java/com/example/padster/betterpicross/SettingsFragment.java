package com.example.padster.betterpicross;

import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by padster on 05/10/17.
 */

public class SettingsFragment extends PreferenceFragment {

    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);




        Preference musicPref = (Preference) findPreference("music_enabled");
        musicPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean music_enabled = (boolean) newValue;
                if (music_enabled) {
                    Toast.makeText(getActivity(), "Music turned on, downloading wall", Toast.LENGTH_SHORT).show();
//                    downloadTask.execute("https://images.unsplash.com/photo-1446770145316-10a05382c470");

                    if (Build.VERSION.SDK_INT >= 23)
                    {
                        if (checkPermission())
                        {
                            // Code for above or equal 23 API Oriented Device
                            // Your Permission granted already .Do next code
                            downloadFile("https://images.unsplash.com/photo-1446770145316-10a05382c470", "wall.jpg");
                        } else {
                            requestPermission(); // Code for permission
                            //TODO - in the callback at the bottom...
                        }
                    }
                    else
                    {
                        // Code for Below 23 API Oriented Device
                        // Do next code
                        downloadFile("https://images.unsplash.com/photo-1446770145316-10a05382c470", "wall.jpg");
                    }




                } else {
                    Toast.makeText(getActivity(), "Music turned off", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });

    }

    private void downloadFile(String url, String name) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription("Some description");
        request.setTitle(name);
        // in order for this if to run, you must use the android 3.2 to compile your app
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, name);

        // get download service and enqueue file
        DownloadManager manager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(getActivity(), "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                    downloadFile("https://images.unsplash.com/photo-1446770145316-10a05382c470", "wall.jpg");

                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");

                }
                break;
        }
    }



}
