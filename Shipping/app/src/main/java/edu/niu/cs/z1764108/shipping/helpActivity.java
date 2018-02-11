package edu.niu.cs.z1764108.shipping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class helpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);


    }//onCreate

    public void goBack(View view)
    {
        finish();
    }

}
