package com.example.mathapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class CompareNumbersActivity extends AppCompatActivity {
    private int num1, num2;
    private TextView txtNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        txtNumbers = findViewById(R.id.txtNumbers);
        Button btnLeft = findViewById(R.id.btnLeft);
        Button btnRight = findViewById(R.id.btnRight);

        generateNumbers();

        btnLeft.setOnClickListener(v -> checkAnswer(num1 > num2));
        btnRight.setOnClickListener(v -> checkAnswer(num2 > num1));
    }

    private void generateNumbers() {
        Random rand = new Random();
        num1 = rand.nextInt(999) + 1;
        num2 = rand.nextInt(999) + 1;
        txtNumbers.setText(num1 + " vs " + num2);
    }

    private void checkAnswer(boolean isCorrect) {
        if (isCorrect) {
            txtNumbers.setText("Correct! " + num1 + " vs " + num2);
        } else {
            txtNumbers.setText("Try Again!");
        }
        generateNumbers();
    }
}
