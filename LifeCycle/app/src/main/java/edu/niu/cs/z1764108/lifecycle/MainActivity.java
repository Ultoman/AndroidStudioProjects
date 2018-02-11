package edu.niu.cs.z1764108.lifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String createMsg, startMsg, resumeMsg, pauseMsg, stopMsg, restartMsg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createMsg = (String)getResources().getText(R.string.createMsg);
        startMsg = (String)getResources().getText(R.string.startMsg);
        resumeMsg = (String)getResources().getText(R.string.resumeMsg);
        pauseMsg = (String)getResources().getText(R.string.pauseMsg);
        restartMsg = (String)getResources().getText(R.string.restartMsg);
        stopMsg = (String)getResources().getText(R.string.stopMsg);

        Toast.makeText(this, createMsg, Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", createMsg);

    }//OnCreate

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, startMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, resumeMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, restartMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, stopMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, pauseMsg, Toast.LENGTH_SHORT).show();
    }
}//end of Main
