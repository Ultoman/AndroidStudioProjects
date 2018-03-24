package jab.trivia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;

public class GameActivity extends AppCompatActivity {

    // Declare widget variables
    private TextView questionTV, scoreTV;
    private RadioButton answer1RB, answer2RB, answer3RB, answer4RB;
    private RadioGroup answersRG;

    // variables for keeping track of the game progress
    private int questionNum;
    private String pickedAnswer;
    private int score;

    // Containers for trivia questions and answers
    private ArrayList<Trivia> trivias;
    private ArrayList<String> answers;

    // Database declaration
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        databaseManager = new DatabaseManager(this);
        answers = new ArrayList<String>();

        // Connect widgets
        questionTV = (TextView)findViewById(R.id.questionTextView);
        answer1RB = (RadioButton)findViewById(R.id.answer1RadioButton);
        answer2RB = (RadioButton)findViewById(R.id.answer2RadioButton);
        answer3RB = (RadioButton)findViewById(R.id.answer3RadioButton);
        answer4RB = (RadioButton)findViewById(R.id.answer4RadioButton);
        answersRG = (RadioGroup)findViewById(R.id.answersRadioGroup);
        scoreTV = (TextView) findViewById(R.id.scoreText);

        // Get trivia questions from database
        trivias = databaseManager.selectAllTrivia();

        // Set initial values
        questionNum = 0;
        score = 0;

        // Populate UI
        loadTrivia();

        answersRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.answer1RadioButton:
                        pickedAnswer = answer1RB.getText().toString();
                        break;
                    case R.id.answer2RadioButton:
                        pickedAnswer = answer2RB.getText().toString();
                        break;
                    case R.id.answer3RadioButton:
                        pickedAnswer = answer3RB.getText().toString();
                        break;
                    case R.id.answer4RadioButton:
                        pickedAnswer = answer4RB.getText().toString();
                        break;
                }
            }
        });



    }

    public void loadTrivia()
    {
        // Add answers to array
        answers.add(trivias.get(questionNum).getCorrect_ans());
        answers.add(trivias.get(questionNum).getInc_ans_1());
        answers.add(trivias.get(questionNum).getInc_ans_2());
        answers.add(trivias.get(questionNum).getInc_ans_3());

        //Randomize answers
        Collections.shuffle(answers);

        // Set text in activity
        try {
            questionTV.setText(Html.fromHtml(trivias.get(questionNum).getQuestion()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.d("decode", e.toString());
        }
        answer1RB.setText(answers.get(0));
        answer2RB.setText(answers.get(1));
        answer3RB.setText(answers.get(2));
        answer4RB.setText(answers.get(3));

        scoreTV.setText("Score: " + score + " out of " + questionNum);
    }



    public void submitAnswer(View v)
    {
        // If no radio buttons are checked
        if (answersRG.getCheckedRadioButtonId() == -1)
        {
            Toast.makeText(getApplicationContext(), "Choose an answer!", Toast.LENGTH_SHORT).show();
            return;
        }
        // If an answer is choosen and the submit button is pressed
        else
        {
            // if the picked answer it the correct answer
            if (pickedAnswer == trivias.get(questionNum).getCorrect_ans())
            {
                Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
                score++;
            }
            // else the answer is wrong
            else
            {
                Toast.makeText(getApplicationContext(), "Incorrect! Answer - " + trivias.get(questionNum).getCorrect_ans(), Toast.LENGTH_SHORT).show();
            }


            questionNum++;

            // Check if the question was the last one
            if (questionNum == trivias.size() - 1) //Quit if its the last
            {
                // Add score as float to database table scores
                databaseManager.insertScore((float)score / trivias.size());
                // Drop questions from database
                databaseManager.deleteTrivia();
                // Quit
                finish();
            }
            else { // else continue game
                // clear Radio Group checks
                answersRG.clearCheck();
                // Clear answers array list
                answers.clear();
                // load new trivia with updated questionNum
                loadTrivia();
            }
        }

    }

    public void quitGame(View v)
    {
        databaseManager.deleteTrivia();
        finish();
    }
}
