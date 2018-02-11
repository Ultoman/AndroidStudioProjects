package edu.niu.cs.z1764108.candystore;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {

    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseManager = new DatabaseManager(this);
        updateView();
    }//onCreate

    public void updateView() {

        ArrayList<Candy> candies = databaseManager.selectAll();

        int numCandies = candies.size();

        if (numCandies > 0) {
            RelativeLayout layout = new RelativeLayout(this); //parent layout

            ScrollView scrollView = new ScrollView(this);  // to hold lots of data

            GridLayout gridLayout = new GridLayout(this);
            gridLayout.setRowCount(numCandies);
            gridLayout.setColumnCount(4);

            TextView[] ids = new TextView[numCandies];
            EditText[][] nameAndPrices = new EditText[numCandies][2];

            Button[] buttons = new Button[numCandies];

            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;

            int sub = 0;

            for (Candy candy : candies) {
                ids[sub] = new TextView(this);
                ids[sub].setGravity(Gravity.CENTER);
                ids[sub].setText("" + candy.getId());

                //fill the edit text fields
                nameAndPrices[sub][0] = new EditText(this);
                nameAndPrices[sub][1] = new EditText(this);
                nameAndPrices[sub][0].setText(candy.getName());
                nameAndPrices[sub][1].setText("" + candy.getPrice());
                nameAndPrices[sub][1].setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                nameAndPrices[sub][0].setId(10 * candy.getId());
                nameAndPrices[sub][1].setId(10 * candy.getId() + 1);

                //fill the buttons
                buttons[sub] = new Button(this);
                buttons[sub].setText("Update");
                buttons[sub].setId(candy.getId());
                buttons[sub].setOnClickListener(buttonHandler);

                gridLayout.addView(ids[sub], width / 10, ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(nameAndPrices[sub][0], (int) (width * .4), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(nameAndPrices[sub][1], (int) (width * .15), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(buttons[sub], (int) (width * .35), ViewGroup.LayoutParams.WRAP_CONTENT);

                sub++;
            }//for

            Button backButton = new Button(this);
            backButton.setText("Back");
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            scrollView.addView(gridLayout);

            layout.addView(scrollView);

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                                                                                RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            params.setMargins(0,0,0,50);

            layout.addView(backButton, params);

            setContentView(layout);
        }//if
    }//updateView

    public View.OnClickListener buttonHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int candyID = v.getId();

            EditText nameET = (EditText)findViewById(10 * candyID),
                     priceET = (EditText)findViewById( 10 * candyID + 1);

            String nameStr = nameET.getText().toString(),
                   priceStr = priceET.getText().toString();

            try
            {
                double price = Double.parseDouble(priceStr);
                databaseManager.updateById(candyID, nameStr, price);
                Toast.makeText(UpdateActivity.this, "Candy updated: " + nameStr, Toast.LENGTH_SHORT).show();
                updateView();
            }
            catch (NumberFormatException e)
            {
                Toast.makeText(UpdateActivity.this, "Candy NOT updated: price error", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    };

}