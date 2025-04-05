package com.example.mathapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;
import java.util.Random;

public class OrderingNumbersActivity extends AppCompatActivity {
    private TextView txtOrder;
    private int[] numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        txtOrder = findViewById(R.id.txtOrder);
        Button btnGenerate = findViewById(R.id.btnGenerateOrder);
        Button btnSortAscending = findViewById(R.id.btnSortAscending);
        Button btnSortDescending = findViewById(R.id.btnSortDescending);

        btnGenerate.setOnClickListener(v -> generateNumbers());
        btnSortAscending.setOnClickListener(v -> sortNumbersAscending());
        btnSortDescending.setOnClickListener(v -> sortNumbersDescending());
    }

    private void generateNumbers() {
        Random rand = new Random();
        int count = rand.nextBoolean() ? 3 : 4;
        numbers = new int[count];
        for (int i = 0; i < count; i++) {
            numbers[i] = rand.nextInt(999) + 1;
        }
        txtOrder.setText("Order the following numbers:\n" + Arrays.toString(numbers));
    }

    private void sortNumbersAscending() {
        int[] sorted = numbers.clone();
        Arrays.sort(sorted);
        txtOrder.setText("Numbers in Ascending Order:\n" + Arrays.toString(sorted));
    }

    private void sortNumbersDescending() {
        int[] sorted = numbers.clone();
        Arrays.sort(sorted);
        // Reverse the array for descending order
        for (int i = 0; i < sorted.length / 2; i++) {
            int temp = sorted[i];
            sorted[i] = sorted[sorted.length - 1 - i];
            sorted[sorted.length - 1 - i] = temp;
        }
        txtOrder.setText("Numbers in Descending Order:\n" + Arrays.toString(sorted));
    }
}
