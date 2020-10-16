package com.example.yame.Activitys.ProductDetailActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.yame.R;

public class DetailActivity extends AppCompatActivity {

    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initViews();
        receiveData();
    }

    private void initViews() {
        tvContent = findViewById(R.id.tvContent);
    }

    private void receiveData() {
        Intent intent = getIntent();
        String receive = intent.getStringExtra("from");

        tvContent.setText(receive);
    }
}