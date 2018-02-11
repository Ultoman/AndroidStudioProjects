package edu.niu.cs.z1764108.exam1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Triangle extends AppCompatActivity {

    EditText baseET, heightET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triangle);

        baseET = (EditText)findViewById(R.id.baseEditText);
        heightET = (EditText)findViewById(R.id.heightEditText);

    }

    public void calcTri(View view)
    {
        if (baseET.getText().toString().matches("") || heightET.getText().toString().matches(""))
        {
            Toast.makeText(this, "Please enter values", Toast.LENGTH_SHORT).show();
            return;
        }

        float base = Integer.parseInt(baseET.getText().toString());
        float height = Integer.parseInt(heightET.getText().toString());
        double area = (base * height) / 2;

        Intent intent = getIntent();
        intent.putExtra("area", area);
        ((Activity)view.getContext()).setResult(RESULT_OK, intent);
        finish();
    }
}
