package com.example.mathapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class ComposingNumbersActivity extends AppCompatActivity {
    private TextView txtCompose;
    private EditText edtPart1, edtPart2;
    private RadioGroup radioGroupMode;
    private RadioButton radioAdd, radioSubtract;
    private int targetNumber;
    private String mode = "add"; // default to addition

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        txtCompose = findViewById(R.id.txtCompose);
        edtPart1 = findViewById(R.id.edtPart1);
        edtPart2 = findViewById(R.id.edtPart2);
        Button btnCompose = findViewById(R.id.btnComposeDigits);
        Button btnCheck = findViewById(R.id.btnCheckParts);
        radioGroupMode = findViewById(R.id.radioGroupMode);
        radioAdd = findViewById(R.id.radioAdd);
        radioSubtract = findViewById(R.id.radioSubtract);

        // Handle mode change
        radioGroupMode.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioAdd) {
                mode = "add";
            } else if (checkedId == R.id.radioSubtract) {
                mode = "subtract";
            }
            generateNewTarget(); // Generate new challenge on mode switch
        });

        btnCompose.setOnClickListener(v -> generateNewTarget());
        btnCheck.setOnClickListener(v -> checkUserAnswer());

        generateNewTarget(); // Load initial challenge
    }

    private void generateNewTarget() {
        Random rand = new Random();
        int num1 = rand.nextInt(900) + 100;  // 100–999 for bigger challenge
        int num2 = rand.nextInt(100);        // 0–99 to keep it simple

        if (mode.equals("add")) {
            targetNumber = num1 + num2;
            txtCompose.setText("How can we make the number: X + Y = " + targetNumber + "?");
        } else {
            if (num2 > num1) {
                // Ensure result is not negative
                int temp = num1;
                num1 = num2;
                num2 = temp;
            }
            targetNumber = num1 - num2;
            txtCompose.setText("How can we make the number: X - Y = " + targetNumber + "?");
        }

        edtPart1.setText("");
        edtPart2.setText("");
    }

    private void checkUserAnswer() {
        String input1 = edtPart1.getText().toString().trim();
        String input2 = edtPart2.getText().toString().trim();

        if (input1.isEmpty() || input2.isEmpty()) {
            txtCompose.setText("Please enter both parts.");
            return;
        }

        try {
            int num1 = Integer.parseInt(input1);
            int num2 = Integer.parseInt(input2);

            if (mode.equals("add")) {
                if (num1 + num2 == targetNumber) {
                    txtCompose.setText("✅ Yes! " + num1 + " + " + num2 + " = " + targetNumber);
                } else {
                    txtCompose.setText("❌ Not quite. Try again!\nHint: " + num1 + " + " + num2 + " = " + (num1 + num2));
                }
            } else {
                if (num1 - num2 == targetNumber) {
                    txtCompose.setText("✅ Yes! " + num1 + " - " + num2 + " = " + targetNumber);
                } else {
                    txtCompose.setText("❌ Not quite. Try again!\nHint: " + num1 + " - " + num2 + " = " + (num1 - num2));
                }
            }

        } catch (NumberFormatException e) {
            txtCompose.setText("Please enter valid numbers.");
        }
    }
}
