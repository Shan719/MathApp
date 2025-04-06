package com.example.mathapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;
import java.util.Random;

public class OrderingNumbersActivity extends AppCompatActivity {
    private TextView txtOrder;
    private EditText edtAnswer;
    private Button btnCheckAnswer;
    private int[] numbers;
    private String currentOrder = "ascending"; // default order

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        txtOrder = findViewById(R.id.txtOrder);
        edtAnswer = findViewById(R.id.edtAnswer);
        btnCheckAnswer = findViewById(R.id.btnCheckAnswer);
        Button btnGenerate = findViewById(R.id.btnGenerateOrder);
        Button btnSortAscending = findViewById(R.id.btnSortAscending);
        Button btnSortDescending = findViewById(R.id.btnSortDescending);

        btnGenerate.setOnClickListener(v -> generateNumbers());
        btnSortAscending.setOnClickListener(v -> {
            currentOrder = "ascending";
            txtOrder.setText("Now enter the numbers in Ascending Order:\n" + Arrays.toString(numbers));
        });
        btnSortDescending.setOnClickListener(v -> {
            currentOrder = "descending";
            txtOrder.setText("Now enter the numbers in Descending Order:\n" + Arrays.toString(numbers));
        });
        btnCheckAnswer.setOnClickListener(v -> checkAnswer());
    }

    private void generateNumbers() {
        Random rand = new Random();
        int count = rand.nextBoolean() ? 3 : 4;
        numbers = new int[count];
        for (int i = 0; i < count; i++) {
            numbers[i] = rand.nextInt(999) + 1;
        }
        txtOrder.setText("Order the following numbers:\n" + Arrays.toString(numbers));
        edtAnswer.setText("");
    }

    private void checkAnswer() {
        if (numbers == null || numbers.length == 0) {
            txtOrder.setText("Please generate numbers first.");
            return;
        }

        String input = edtAnswer.getText().toString().trim();
        if (input.isEmpty()) {
            txtOrder.setText("Please enter your answer.");
            return;
        }

        String[] parts = input.split("\\s*,\\s*");  // Split by commas with optional spaces
        if (parts.length != numbers.length) {
            txtOrder.setText("Please enter " + numbers.length + " numbers.");
            return;
        }

        try {
            int[] userOrder = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                userOrder[i] = Integer.parseInt(parts[i]);
            }

            int[] correct = numbers.clone();
            Arrays.sort(correct);
            if (currentOrder.equals("descending")) {
                for (int i = 0; i < correct.length / 2; i++) {
                    int temp = correct[i];
                    correct[i] = correct[correct.length - 1 - i];
                    correct[correct.length - 1 - i] = temp;
                }
            }

            if (Arrays.equals(userOrder, correct)) {
                txtOrder.setText("✅ Great job! That's the correct order.");
            } else {
                txtOrder.setText("❌ Oops! Try again\nCorrect order was:\n" + Arrays.toString(correct));
            }
        } catch (NumberFormatException e) {
            txtOrder.setText("Please enter only numbers separated by spaces.");
        }
    }
}
