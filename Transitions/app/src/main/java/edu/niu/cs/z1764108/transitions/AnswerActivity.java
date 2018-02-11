package edu.niu.cs.z1764108.transitions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
    }

    public void goQuestion(View view){
        finish();
        overridePendingTransition(R.anim.question_on, R.anim.answer_off);
    }
}
