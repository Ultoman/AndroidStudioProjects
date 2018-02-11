package edu.niu.cs.z1764108.exam1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Circle extends AppCompatActivity {

    EditText radiusET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);

        radiusET = (EditText)findViewById(R.id.radiusEditText);

    }

    public void calcCircle(View view)
    {
        if (radiusET.getText().toString().matches(""))
        {
            Toast.makeText(this, "Please enter values", Toast.LENGTH_SHORT).show();
            return;
        }

        float radius = Integer.parseInt(radiusET.getText().toString());
        double area = radius * radius * 3.14159;

        Intent intent = getIntent();
        intent.putExtra("area", area);
        ((Activity)view.getContext()).setResult(RESULT_OK, intent);
        finish();
    }
}
