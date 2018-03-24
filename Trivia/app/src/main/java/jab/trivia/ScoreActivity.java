package jab.trivia;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {

    private DatabaseManager databaseManager;

    // Connection to linearlayout for content setting programmatically
    private LinearLayout linearLayout;

    // Score container
    private ArrayList<Float> scores;
    // TextView widget
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        databaseManager = new DatabaseManager(this);
        linearLayout = (LinearLayout)findViewById(R.id.activity_score);

        scores = new ArrayList<>();
        // Get scores
        scores = databaseManager.selectAllScores();
        // For every score create a new TextView and add to the view
        for (int i = 0; i < scores.size(); i++)
        {
            tv = new TextView(this);
            float currentScore = scores.get(i);
            tv.setText((i + 1) + ".   " + (currentScore * 100) + "%");
            tv.setTextColor(Color.BLACK);
            tv.setTextSize(20);

            linearLayout.addView(tv);
        }
    }

    public void goBack(View v)
    {
        finish();
    }
}
