package com.example.mathapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class ComposingNumbersActivity extends AppCompatActivity {
    private TextView txtCompose;
    private int targetNumber, part1, part2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        txtCompose = findViewById(R.id.txtCompose);
        Button btnCompose = findViewById(R.id.btnComposeDigits);

        btnCompose.setOnClickListener(v -> generateComposition());

        generateComposition(); // Generate the first example when activity starts
    }

    private void generateComposition() {
        Random rand = new Random();

        // Generate a target number between 5 and 99
        targetNumber = rand.nextInt(95) + 5;

        // Generate two smaller numbers that sum to the target number
        part1 = rand.nextInt(targetNumber - 1) + 1; // At least 1
        part2 = targetNumber - part1;

        // Display as: "5 + 3 = 8"
        txtCompose.setText(part1 + " + " + part2 + " = " + targetNumber);
    }
}
