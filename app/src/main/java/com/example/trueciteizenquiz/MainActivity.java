package com.example.trueciteizenquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnTrue;
    private Button btnFalse;
    private ImageButton btnNext;
    private int currentQuestion = 0;
    private Question[] questrionBank = new Question[]{
            new Question(R.string.question_declaration, false),
            new Question(R.string.question_culture, false),
            new Question(R.string.question_money, true),
            new Question(R.string.question_monument, false)
    };

    private TextView txtQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btnTrue = findViewById(R.id.btnTrue);
        btnFalse = findViewById(R.id.btnFalse);
        txtQuestion = findViewById(R.id.txtAnswer);

        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(this);

       btnFalse.setOnClickListener(this); //register our buttons to an interface class
       btnTrue.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFalse:
                checkAnswer(false);

                break;
            case R.id.btnTrue:
                checkAnswer(true);

                break;

            case R.id.btnNext:

                currentQuestion = (currentQuestion+1) % questrionBank.length; // same as condition
                UpdateQuestion();

        }


    }

    private void UpdateQuestion()
    {
        txtQuestion.setText(questrionBank[currentQuestion].getAnswerResId());
    }

    private void checkAnswer(boolean isItTrue)
    {
        boolean answerIsTrue = questrionBank[currentQuestion].isAnswerTrue();
        int toastMesageId = 0;

        if(isItTrue == answerIsTrue)
        {
            toastMesageId = R.string.correct_answer;
        }
        else
        {
            toastMesageId = R.string.wrong_answer;
        }

        Toast.makeText(MainActivity.this, toastMesageId, Toast.LENGTH_SHORT).show();
    }
}
