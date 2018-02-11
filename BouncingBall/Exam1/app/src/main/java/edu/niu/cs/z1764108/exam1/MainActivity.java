package edu.niu.cs.z1764108.exam1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private RadioGroup shapeRG;
    private TextView areaTV;
    private int shape = 99;

    DecimalFormat df = new DecimalFormat("#0.000");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shapeRG = (RadioGroup)findViewById(R.id.shapeRadioGroup);
        areaTV = (TextView)findViewById(R.id.areaTextView);

        shapeRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.triangleRadioButton)
            {
                shape = 0;
            }
                else if (checkedId == R.id.circleRadioButton)
            {
                shape = 1;
            }
                else if (checkedId == R.id.ellipseRadioButton)
            {
                shape = 2;
            }
            }
        });

    }//onCreate

    public void doCalc(View view)
    {
        Intent i;
        if (shape == 0)
        {
            i = new Intent(MainActivity.this, Triangle.class);
            startActivityForResult(i, shape);
        }
        else if (shape == 1)
        {
            i = new Intent(MainActivity.this, Circle.class);
            startActivityForResult(i, shape);
        }
        else if (shape == 2)
        {
            i = new Intent(MainActivity.this, Ellipse.class);
            startActivityForResult(i, shape);
        }
        else
        {
            Toast.makeText(this, "Please choose a shape", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String areaString;
        double area = data.getDoubleExtra("area", 999.99);


        //triangle = 0, circle = 1, ellipse = 2
        if (requestCode == 0 && resultCode == RESULT_OK)
        {
            areaString = "The area of the Triangle is " + df.format(area);
        }
        else if (requestCode == 1 && resultCode == RESULT_OK)
        {
            areaString = "The area of the Circle is " + df.format(area);
        }
        else if (requestCode == 2 && resultCode == RESULT_OK)
        {
            areaString = "The area of the Ellipse is " + df.format(area);
        }
        else
        {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            return;
        }

        areaTV.setText(areaString);
    }
}
