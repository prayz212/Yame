package com.example.yame.Activitys.ProductDetailActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yame.R;

import java.util.ArrayList;
import java.util.List;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvProductName, tvProductPrice, tvTitleProduct, tvAvailable, tvInstruction, tvDetails;
    private String productName, productPrice;
    private ImageButton btnBack;
    private List<Integer> imgR;
    private ImageAdapter adapter;
    private RecyclerView recyclerView_imageProduct;
    private CardView cardViewExpand;
    private Button btnAvailable, btnInstruction, btnDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initViews();
        receiveData();
        initData();
        expandClicked();

        btnBack.setOnClickListener((v) -> {
            finish();
        });
    }

    private void expandClicked() {
        btnAvailable.setOnClickListener(this);
        btnInstruction.setOnClickListener(this);
        btnDetails.setOnClickListener(this);
    }

    private void expand(TextView tv, Button btn) {
        // Dùng để xuất hiện phần nội dung được ẩn
        if (tv.getVisibility() == View.GONE) {
            TransitionManager.beginDelayedTransition(cardViewExpand, new AutoTransition());
            tv.setVisibility(View.VISIBLE);
            btn.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_baseline_expand_less, 0);
        }
        // Dùng để thu lại các nội dung đang hiển thị
        else {
            TransitionManager.beginDelayedTransition(cardViewExpand, new AutoTransition());
            tv.setVisibility(View.GONE);
            btn.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_baseline_expand_more, 0);
        }
    }

    private void initData() {
        tvProductName.setText(productName);
        tvProductPrice.setText(productPrice);
        tvTitleProduct.setText(productName);

        imgR = new ArrayList<>();

        imgR.add(R.raw.demo_img);
        imgR.add(R.raw.demo_img);
        imgR.add(R.raw.demo_img);

        adapter = new ImageAdapter(this, imgR, R.layout.image_recyclerview);
        recyclerView_imageProduct.setHasFixedSize(true);
        recyclerView_imageProduct.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView_imageProduct.setAdapter(adapter);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView_imageProduct);
    }

    private void initViews() {
        tvProductName = findViewById(R.id.tvProductName);
        tvProductPrice = findViewById(R.id.tvProductPrice);
        recyclerView_imageProduct = findViewById(R.id.recyclerView_imageProduct);
        tvTitleProduct = findViewById(R.id.tvTitleProduct);
        btnBack = findViewById(R.id.imgBtnBackProduct);
        cardViewExpand = findViewById(R.id.cardViewExpand);
        tvAvailable = findViewById(R.id.tvAvailableStore);
        tvDetails = findViewById(R.id.tvDetails);
        tvInstruction = findViewById(R.id.tvInstruction);
        btnAvailable = findViewById(R.id.btnAvailableStore);
        btnInstruction = findViewById(R.id.btnInstruction);
        btnDetails = findViewById(R.id.btnDetails);
    }

    private void receiveData() {
        Intent intent = getIntent();

        productName = intent.getStringExtra("Name");
        productPrice = intent.getStringExtra("Price");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAvailableStore:
                expand(tvAvailable,btnAvailable);
                break;
            case R.id.btnInstruction:
                expand(tvInstruction,btnInstruction);
                break;
            case R.id.btnDetails:
                expand(tvDetails,btnDetails);
                break;
        }
    }
}