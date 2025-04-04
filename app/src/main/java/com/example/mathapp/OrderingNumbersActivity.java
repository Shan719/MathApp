package com.example.mathapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;
import java.util.Random;

public class OrderingNumbersActivity extends AppCompatActivity {
    private TextView txtOrder;
    private int[] numbers = new int[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        txtOrder = findViewById(R.id.txtOrder);
        Button btnGenerate = findViewById(R.id.btnGenerateOrder);
        Button btnSort = findViewById(R.id.btnSort);

        btnGenerate.setOnClickListener(v -> generateNumbers());
        btnSort.setOnClickListener(v -> sortNumbers());
    }

    private void generateNumbers() {
        Random rand = new Random();
        for (int i = 0; i < 3; i++) {
            numbers[i] = rand.nextInt(999) + 1;
        }
        txtOrder.setText("Numbers: " + Arrays.toString(numbers));
    }

    private void sortNumbers() {
        Arrays.sort(numbers);
        txtOrder.setText("Sorted: " + Arrays.toString(numbers));
    }
}
