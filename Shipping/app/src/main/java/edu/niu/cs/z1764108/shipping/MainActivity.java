package edu.niu.cs.z1764108.shipping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class MainActivity extends AppCompatActivity {

    //Instance variables
    ShipItem item;

    EditText weightET;
    TextView baseTV, addedTV, totalTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connect the EditText and TextView to the screen
        weightET = (EditText)findViewById(R.id.weightEditText);

        baseTV = (TextView)findViewById(R.id.baseTextView);
        addedTV = (TextView)findViewById(R.id.addedTextView);
        totalTV = (TextView)findViewById(R.id.totalTextView);

        //Create ShipItem object
        item = new ShipItem();

        //Put the focus on the EditText field
        weightET.requestFocus();

        //add a textWatcher
        weightET.addTextChangedListener(weightWatcher);
    }//end of OnCreate

    TextWatcher weightWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            try
            {
                item.setWeight(Integer.parseInt(s.toString()));
            }
            catch( NumberFormatException e )
            {
                item.setWeight(0);
            }

            DecimalFormat df = new DecimalFormat("#0.00");

            baseTV.setText("$" + df.format(item.getBaseCost() ) );
            addedTV.setText("$" + df.format(item.getAddedCost() ) );
            totalTV.setText("$" + df.format(item.getTotalCost() ) );


        }//onTextChanged

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void getHelp(View view)
    {
        Intent helpIntent = new Intent(MainActivity.this, helpActivity.class);
        startActivity(helpIntent);
    }

}//end of MainActivity
