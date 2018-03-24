package jab.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

/*
James Bonasera
Z1764108
Assignment 1 - Trivia App
 */

/*
Holds all of the buttons which call intents to the other activities
 */
public class MainActivity extends AppCompatActivity {

    // Counts
    private int triviaCount;
    private int scoreCount;

    // Database declaration
    private DatabaseManager databaseManager;

    // Widgets
    private Button newGameB;
    private Button addQuestionB;
    private Button deleteQuestionsB;
    private Button deleteScoresB;
    private Button scoreB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect widgets
        databaseManager = new DatabaseManager(this);
        newGameB = (Button) findViewById(R.id.newGameButton);
        addQuestionB = (Button)findViewById(R.id.addQuestionButton);
        deleteQuestionsB = (Button) findViewById(R.id.deleteQuestionsButton);
        scoreB = (Button)findViewById(R.id.scoreButton);
        deleteScoresB = (Button)findViewById(R.id.deleteScoresButton);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // update buttons in onResume so when coming back from other activities the buttons update with correct information
        // Get number of scores and trivia questions from database
        triviaCount = databaseManager.getTriviaCount();
        scoreCount = databaseManager.getScoreCount();
        // Set button text with counts
        newGameB.setText("New Game: " + triviaCount);
        scoreB.setText("Scores: " + scoreCount);
        // Check if questions are in the database and enable or disable buttons accordingly
        if (triviaCount == 0)
        {
            newGameB.setEnabled(false);
            addQuestionB.setEnabled(true);
            deleteQuestionsB.setEnabled(false);
        }
        else
        {
            newGameB.setEnabled(true);
            addQuestionB.setEnabled(false);
            deleteQuestionsB.setEnabled(true);
        }
        // Check if scores are in the database and enable or disable buttons accordingly
        if (scoreCount == 0)
        {
            deleteScoresB.setEnabled(false);
        }
        else
        {
            deleteScoresB.setEnabled(true);
        }
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

    public void deleteQuestions(View v)
    {
        //Delete Questions was pressed
        databaseManager.deleteTrivia();
        this.recreate();
    }

    public void deleteScores(View v)
    {
        //Delete scores was pressed
        databaseManager.deleteScores();
        this.recreate();
    }

}
