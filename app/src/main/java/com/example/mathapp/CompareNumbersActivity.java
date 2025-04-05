package com.example.mathapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class CompareNumbersActivity extends AppCompatActivity {

    private int num1, num2;
    private boolean askGreater; // true = ask for greater, false = ask for smaller
    private TextView txtQuestion, txtFeedback;
    private Button btnLeft, btnRight, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        txtQuestion = findViewById(R.id.txtQuestion);
        txtFeedback = findViewById(R.id.txtFeedback);
        btnLeft = findViewById(R.id.btnLeft);
        btnRight = findViewById(R.id.btnRight);
        btnNext = findViewById(R.id.btnNext);

        generateQuestion();

        btnLeft.setOnClickListener(v -> checkAnswer(true));
        btnRight.setOnClickListener(v -> checkAnswer(false));
        btnNext.setOnClickListener(v -> generateQuestion());
    }

    private void generateQuestion() {
        Random rand = new Random();
        num1 = rand.nextInt(999) + 1;
        num2 = rand.nextInt(999) + 1;

        while (num1 == num2) {
            num2 = rand.nextInt(999) + 1;
        }

        askGreater = rand.nextBoolean(); // randomly decide whether to ask for greater or smaller

        String prompt = askGreater ? "Which number is Greater?" : "Which number is Smaller?";
        txtQuestion.setText(prompt);

        // Set button texts directly as numbers
        btnLeft.setText(String.valueOf(num1));
        btnRight.setText(String.valueOf(num2));

        txtFeedback.setText("");
    }

    private void checkAnswer(boolean choseLeft) {
        boolean isCorrect;

        if (askGreater) {
            isCorrect = (choseLeft && num1 > num2) || (!choseLeft && num2 > num1);
        } else {
            isCorrect = (choseLeft && num1 < num2) || (!choseLeft && num2 < num1);
        }

        String result = isCorrect ? "✅ Correct!" : "❌ Wrong!";
        String explanation;

        if (askGreater) {
            explanation = (num1 > num2) ? num1 + " is greater than " + num2
                    : num2 + " is greater than " + num1;
        } else {
            explanation = (num1 < num2) ? num1 + " is smaller than " + num2
                    : num2 + " is smaller than " + num1;
        }

        txtFeedback.setText(result + "\n" + explanation);
    }
}
