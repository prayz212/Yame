package com.example.yame.TypeActivitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.yame.R;

public class ProductActivity extends AppCompatActivity {

    private TextView tvTitle;
    private ImageButton btnBack;

    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        initViews();
        initData();
        config();
    }

    private void initViews() {
        tvTitle = findViewById(R.id.tvTitle);
        btnBack = findViewById(R.id.imgBtnBack);
    }

    private void initData() {
    }

    private void config() {
        //set title of activity depend on type
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        tvTitle.setText(type);

        //back to the previous activity
        btnBack.setOnClickListener((v) -> {
            finish();
        });
    }
}