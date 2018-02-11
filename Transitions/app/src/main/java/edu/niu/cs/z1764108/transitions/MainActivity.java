package edu.niu.cs.z1764108.transitions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goAnswer(View view){
        Intent showAnswer = new Intent(MainActivity.this, AnswerActivity.class);
        startActivity(showAnswer);
        overridePendingTransition(R.anim.answer_on, R.anim.question_off);
    }
}
