package edu.niu.cs.z1764108.fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            finish();
            return;
        }

        setContentView(R.layout.fragment_information);
        Intent intent = getIntent();
        String shadeInfo = intent.getStringExtra("Information");

        TextView infoTV = (TextView) findViewById(R.id.colorInfoTextView);
        infoTV.setText(shadeInfo);

        Button backbtn = (Button)findViewById(R.id.backButton);
        backbtn.setVisibility(View.VISIBLE);
    }

    public void goBack(View view)
    {
        finish();
        view.setVisibility(View.INVISIBLE);
    }

}
