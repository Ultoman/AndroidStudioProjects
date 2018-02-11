package edu.niu.cs.z1764108.candystore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {

    private DatabaseManager database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = new DatabaseManager(this);

        updateView();
    }//onCreate

    public void updateView()
    {
        ArrayList<Candy> candies = database.selectAll();

        RelativeLayout relativeLayout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);
        RadioGroup radioGroup = new RadioGroup(this);

        for (Candy candy : candies)
        {
            //create radioButton
            RadioButton radioButton = new RadioButton(this);

            radioButton.setId(candy.getId());
            radioButton.setText(candy.toString());

            radioGroup.addView(radioButton);
        }

        //handle selection
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                database.deleteById(checkedId);
                Toast.makeText(DeleteActivity.this, "Deleted candy", Toast.LENGTH_SHORT).show();
                updateView();
            }
        });

        Button backBtn = new Button(this);
        backBtn.setText("Back");
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        scrollView.addView(radioGroup);
        relativeLayout.addView(scrollView);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0,0,0,50);
        relativeLayout.addView(backBtn, params);

        setContentView(relativeLayout);
    }//udpateView

}//delete Activity
