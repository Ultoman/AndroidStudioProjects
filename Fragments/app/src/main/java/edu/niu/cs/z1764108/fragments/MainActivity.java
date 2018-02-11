package edu.niu.cs.z1764108.fragments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ListFragment.OnItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onShadeItemSelected(String colorInfo) {
        InformationFragment informationFragment = (InformationFragment)getFragmentManager().findFragmentById(R.id.infoFragment);
        if (informationFragment != null && informationFragment.isInLayout())
        {
            informationFragment.setText(colorInfo);
        }
        else
        {
            Intent intent = new Intent(MainActivity.this, InformationActivity.class);
            intent.putExtra("Information", colorInfo);
            startActivity(intent);
        }
    }
}
