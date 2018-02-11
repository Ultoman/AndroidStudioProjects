package edu.niu.cs.z1764108.exam1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Ellipse extends AppCompatActivity {

    EditText minorET, majorET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ellipse);

        minorET = (EditText)findViewById(R.id.minorEditText);
        majorET = (EditText)findViewById(R.id.majorEditText);

    }

    public void calcEll(View view)
    {
        if (minorET.getText().toString().matches("") || majorET.getText().toString().matches(""))
        {
            Toast.makeText(this, "Please enter values", Toast.LENGTH_SHORT).show();
            return;
        }

        float minor = Integer.parseInt(minorET.getText().toString());
        float major = Integer.parseInt(majorET.getText().toString());
        double area = minor * major * 3.14159;

        Intent intent = getIntent();
        intent.putExtra("area", area);
        ((Activity)view.getContext()).setResult(RESULT_OK, intent);
        finish();
    }
}
