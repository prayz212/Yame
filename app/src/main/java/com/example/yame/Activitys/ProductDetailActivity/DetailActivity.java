package com.example.yame.Activitys.ProductDetailActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;

import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.yame.R;

import java.util.ArrayList;
import java.util.List;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvProductName, tvProductPrice, tvTitleProduct, tvAvailable, tvInstruction, tvDetails;
    private String productName, productPrice;
    private ImageButton btnBack;
    private CardView cardViewExpand;
    private Button btnAvailable, btnInstruction, btnDetails;
    private ScrollView scrollViewDetails;
    private ImageSlider imageSlider;

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
        if (tv.getVisibility() == View.GONE) {
            //expand animation
            TransitionManager.beginDelayedTransition(cardViewExpand, new Fade());

            tv.setVisibility(View.VISIBLE);
            btn.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_baseline_expand_less, 0);

            scrollViewDetails.requestChildFocus(tv, tv);
        }
        else {
            //collapse animation
            TransitionManager.beginDelayedTransition(cardViewExpand);

            tv.setVisibility(View.GONE);
            btn.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_baseline_expand_more, 0);
        }
    }

    private void initData() {
        tvProductName.setText(productName);
        tvProductPrice.setText(productPrice);
        tvTitleProduct.setText(productName);

        List<SlideModel> slideModels = new ArrayList<>();

        // slideModels.add(new SlideModel(R.raw... or "https://...", "title", ScaleTypes.FIT));
        // the title is visible
        // slideModels.add(new SlideModel(R.raw.demo_img, "title",ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.raw.demo_img, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.raw.demo_img, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.raw.demo_img, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
    }

    private void initViews() {
        tvProductName = findViewById(R.id.tvProductName);
        tvProductPrice = findViewById(R.id.tvProductPrice);
        imageSlider = findViewById(R.id.imgProduct);
        tvTitleProduct = findViewById(R.id.tvTitleProduct);
        btnBack = findViewById(R.id.imgBtnBackProduct);
        cardViewExpand = findViewById(R.id.cardViewExpand);
        tvAvailable = findViewById(R.id.tvAvailableStore);
        tvDetails = findViewById(R.id.tvDetails);
        tvInstruction = findViewById(R.id.tvInstruction);
        btnAvailable = findViewById(R.id.btnAvailableStore);
        btnInstruction = findViewById(R.id.btnInstruction);
        btnDetails = findViewById(R.id.btnDetails);
        scrollViewDetails = findViewById(R.id.scrollViewDetails);
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