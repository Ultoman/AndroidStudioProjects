package edu.niu.cs.z1764108.async;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button downloadBtn;
    private ProgressBar downloadPB;
    private TextView downloadProgressTV, callbackTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadBtn = (Button)findViewById(R.id.downloadButton);
        downloadPB = (ProgressBar)findViewById(R.id.downloadProgressBar);
        downloadProgressTV = (TextView)findViewById(R.id.downloadTextView);
        callbackTV = (TextView)findViewById(R.id.callBackTextView);

    }//onCreate

    public void clearDisplay(View view)
    {
        callbackTV.setText(" ");
    }

    public void startDownload(View view)
    {
        downloadBtn.setEnabled(false);
        new PerformAsyncTask().execute();
    }

    //inner AsyncTask class
    private class PerformAsyncTask extends AsyncTask<Void, Integer, Void> {

        int progressStatus;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //Lock orientation
            int currentOrientation = getResources().getConfiguration().orientation;
            if (currentOrientation == Configuration.ORIENTATION_PORTRAIT)
            {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
            else
            {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
            callbackTV.setText( callbackTV.getText() + "\nOrientation Locked" );
            callbackTV.setText( callbackTV.getText() + "\nInvoked onPreExecute");

            progressStatus = 0;
            downloadProgressTV.setText("Download: 0%");

            callbackTV.setText( callbackTV.getText() + "\nFinished onPreExecute");
            callbackTV.setText( callbackTV.getText() + "\nInvoked doInBackground");

        }//onPreExecute

        @Override
        protected Void doInBackground(Void... params) {

            while(progressStatus < 100)
            {
                progressStatus += 2;
                publishProgress(progressStatus);
                SystemClock.sleep(300);
            }
            return null;
        }//end doInBackground

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            downloadPB.setProgress(values[0]);
            downloadProgressTV.setText("Download: " + values[0] + "%");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            callbackTV.setText(callbackTV.getText() + "\nCompleted Background Work");
            callbackTV.setText(callbackTV.getText() + "\nInvoked onPostExecute");

            downloadProgressTV.setText("Download Completed");
            downloadBtn.setEnabled(true);
        }//onPostExecute
    }//class

}//MainActivity
