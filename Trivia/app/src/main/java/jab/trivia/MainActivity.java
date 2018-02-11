package jab.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private int triviaCount;

    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseManager = new DatabaseManager(this);

        triviaCount = databaseManager.getTriviaCount();
        Toast.makeText(getApplicationContext(), triviaCount + " questions in database", Toast.LENGTH_LONG).show();

    }

    public void newGame(View v)
    {
        // New Game was pressed - start new game
        Intent newGameIntent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(newGameIntent);

    }

    public void addQuestion(View v)
    {
        // Add Questions was pressed
        Intent addQuestionIntent = new Intent(MainActivity.this, AddActivity.class);
        startActivity(addQuestionIntent);
    }

    public void howToPlay(View v)
    {
        //How to Play was pressed
        Intent howToPlayIntent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(howToPlayIntent);
    }

    public void showScore (View v)
    {
        //Score was pressed
        Intent scoreIntent = new Intent(MainActivity.this, ScoreActivity.class);
        startActivity(scoreIntent);
    }
}
