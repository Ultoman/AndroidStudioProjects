package edu.niu.cs.z1764108.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText num1ET, num2ET;
    TextView resultTV;
    Integer num1, num2, result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connect items from the screen to java variables
        num1ET = (EditText)findViewById(R.id.num1Text);
        num2ET = (EditText)findViewById(R.id.num2Text);

        resultTV = (TextView)findViewById(R.id.resultText);

        //Create Buttons
        Button addBtn = (Button)findViewById(R.id.addButton);
        Button subtractBtn = (Button)findViewById(R.id.subtractButton);
        Button multBtn = (Button)findViewById(R.id.multiplyButton);
        Button divideBtn = (Button)findViewById(R.id.divideButton);
        Button clearBtn = (Button)findViewById(R.id.clearButton);

        //Clear button
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1ET.setText("");
                num2ET.setText("");
                resultTV.setText("");
            }
        });

        //Add button
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num1ET.getText().toString().matches("") || num2ET.getText().toString().matches(""))
                {
                    Toast.makeText(v.getContext(), "You cannot have an empty field", Toast.LENGTH_LONG).show();
                    return;
                }

                num1 = Integer.parseInt(num1ET.getText().toString());
                num2 = Integer.parseInt(num2ET.getText().toString());

                result = num1 + num2;

                resultTV.setText(result.toString());

            }
        });

        //Sutract button
        subtractBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num1ET.getText().toString().matches("") || num2ET.getText().toString().matches(""))
                {
                    Toast.makeText(v.getContext(), "You cannot have an empty field", Toast.LENGTH_LONG).show();
                    return;
                }

                num1 = Integer.parseInt(num1ET.getText().toString());
                num2 = Integer.parseInt(num2ET.getText().toString());

                result = num1 - num2;

                resultTV.setText(result.toString());
            }
        });

        //Multiply Button
        multBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num1ET.getText().toString().matches("") || num2ET.getText().toString().matches(""))
                {
                    Toast.makeText(v.getContext(), "You cannot have an empty field", Toast.LENGTH_LONG).show();
                    return;
                }

                num1 = Integer.parseInt(num1ET.getText().toString());
                num2 = Integer.parseInt(num2ET.getText().toString());

                result = num1 * num2;

                resultTV.setText(result.toString());
            }
        });

        //Divide Button
        divideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num1ET.getText().toString().matches("") || num2ET.getText().toString().matches(""))
                {
                    Toast.makeText(v.getContext(), "You cannot have an empty field", Toast.LENGTH_LONG).show();
                    return;
                }

                num1 = Integer.parseInt(num1ET.getText().toString());
                num2 = Integer.parseInt(num2ET.getText().toString());

                //Check if dividing by zero
                if (num2 == 0)
                {
                    Toast.makeText(v.getContext(), "You cannot divide by zero", Toast.LENGTH_LONG).show();
                    return;
                }

                result = num1 / num2;

                resultTV.setText(result.toString());
            }
        });
    }// onCreate
}// MainActivity
