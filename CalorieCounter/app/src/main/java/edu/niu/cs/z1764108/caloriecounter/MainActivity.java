package edu.niu.cs.z1764108.caloriecounter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends Activity {

    private RadioGroup pattyRG, cheeseRG;
    private CheckBox baconCB;
    private SeekBar sauceSB;
    private TextView caloriesTV;

    private Burger burger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create Burger object
        burger = new Burger();

        //Connect variables
        pattyRG = (RadioGroup)findViewById(R.id.pattyRadioGroup);
        cheeseRG = (RadioGroup)findViewById(R.id.cheeseRadioGroup);

        baconCB = (CheckBox)findViewById(R.id.baconCheckBox);

        sauceSB = (SeekBar)findViewById(R.id.sauceSeekBar);

        caloriesTV = (TextView)findViewById(R.id.calorieTextView);

        //display the initial calorie count
        displayCalories();

        //setup the listeners for the bacon and sauce

        baconCB.setOnClickListener( baconListener );

        sauceSB.setOnSeekBarChangeListener( sauceListener );

        //named listener for the patties
        pattyRG.setOnCheckedChangeListener( pattyListener );

        //anon listener for the cheese
        cheeseRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.noCheeseRadioButton: burger.setCheeseCalories(0);
                        break;
                    case R.id.cheese1RadioButton: burger.setCheeseCalories(Burger.CHEDDAR);
                        break;
                    case R.id.cheese2RadioButton: burger.setCheeseCalories(Burger.MOZZ);
                        break;
                }

                displayCalories();
            }
        });



    }//onCreate

    private RadioGroup.OnCheckedChangeListener pattyListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            switch (checkedId)
            {
                case R.id.beefRadioButton: burger.setPattyCalories(Burger.BEEF);
                    break;
                case R.id.turkeyRadioButton: burger.setPattyCalories(Burger.TURKEY);
                    break;
                case R.id.veggieRadioButton: burger.setPattyCalories(Burger.VEGGIE);
                    break;
            }

            displayCalories();
        }
    };

    //

    private View.OnClickListener baconListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if( ((CheckBox)v).isChecked())
                burger.setBaconCalories(true);
            else
                burger.setBaconCalories(false);
            displayCalories();
        }
    };

    //

    private SeekBar.OnSeekBarChangeListener sauceListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            burger.setSauceCalories( seekBar.getProgress() );
            displayCalories();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private void displayCalories()
    {
        String caloriesString = "Calories: " + burger.getTotalCalories();

        caloriesTV.setText(caloriesString);
    }
}//MainActivity
