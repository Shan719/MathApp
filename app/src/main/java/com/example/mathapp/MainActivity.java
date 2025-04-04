package com.example.mathapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCompare = findViewById(R.id.btnCompare);
        Button btnOrder = findViewById(R.id.btnOrder);
        Button btnCompose = findViewById(R.id.btnCompose);

        btnCompare.setOnClickListener(view -> startActivity(new Intent(this, CompareNumbersActivity.class)));
        btnOrder.setOnClickListener(view -> startActivity(new Intent(this, OrderingNumbersActivity.class)));
        btnCompose.setOnClickListener(view -> startActivity(new Intent(this, ComposingNumbersActivity.class)));
    }
}