package com.falcon.asynctaskexempla;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private View progressView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressView = findViewById(R.id.included_progress_bar);
        progressBar = findViewById(R.id.progressBarWidget);

        final int MAX_PROGRESS = 10;
        progressBar.setMax(MAX_PROGRESS);
        new AsyncTest().execute(MAX_PROGRESS);
    }

    private class AsyncTest extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected void onPreExecute() {
            progressView.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressView.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Async run done!", Toast.LENGTH_LONG).show();
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
            super.onProgressUpdate(values);
        }

        @Override
        protected Void doInBackground(Integer... integers) {

            for (int i = 0; i < integers[0]; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //onProgressUpdate
                publishProgress(i);
            }
            return null;
        }
    }
}
