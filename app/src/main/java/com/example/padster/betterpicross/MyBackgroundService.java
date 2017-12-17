package com.example.padster.betterpicross;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MyBackgroundService extends IntentService {

    /**
     * An IntentService must always have a constructor that calls the super constructor. The
     * string supplied to the super constructor is used to give a name to the IntentService's
     * background thread.
     */
    public MyBackgroundService() {
        super("MyBackgroundService");
    }


    @Override
    protected void onHandleIntent(Intent workIntent) {
        // Gets data from the incoming Intent
        String dataString = workIntent.getDataString();
        // Do work here, based on the contents of dataString

        switch (dataString) {
            case "start":
//                Toast.makeText(this, "Starting background service", Toast.LENGTH_SHORT).show();
                DownloadWebPageTask task = new DownloadWebPageTask();
                task.execute(new String[] { "https://raw.githubusercontent.com/theRealPadster/WhatToWeather/master/README.md" });
                break;
            default:
                Toast.makeText(this, "Stopping background service", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //from vogella tutorial
    private class DownloadWebPageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            // we use the OkHttp library from https://github.com/square/okhttp
            OkHttpClient client = new OkHttpClient();
            Request request =
                    new Request.Builder()
                            .url(urls[0])
                            .build();

            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    return response.body().string();
                }
                return "Download failed";
            }
            catch (IOException e) {
                return "IOException: " + e.getLocalizedMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(MyBackgroundService.this, result, Toast.LENGTH_LONG).show();
        }

//        @Override
        protected void onProgressUpdate(Integer... progress) {
//            setProgressPercent(progress[0]);
        }

//        @Override
        protected void onPostExecute(Long result) {
//            showDialog("Downloaded " + result + " bytes");
        }
    }

}
