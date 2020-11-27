package com.example.yame.MoreFragment.Help;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.yame.R;

public class HelpActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnInstruction1, btnInstruction2, btnInstruction3;
    private TextView tvInstruction1, tvInstruction2, tvInstruction3;
    private ImageButton imgbtnBackHelp;
    private CardView cardviewHelp;
    private ScrollView scrollViewHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        initViews();
        expandClicked();

        imgbtnBackHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void expandClicked() {
        btnInstruction1.setOnClickListener(this);
        btnInstruction2.setOnClickListener(this);
        btnInstruction3.setOnClickListener(this);
    }

    private void initViews() {
        btnInstruction1 = findViewById(R.id.btnInstruction1);
        btnInstruction2 = findViewById(R.id.btnInstruction2);
        btnInstruction3 = findViewById(R.id.btnInstruction3);
        tvInstruction1 = findViewById(R.id.tvInstruction1);
        tvInstruction2 = findViewById(R.id.tvInstruction2);
        tvInstruction3 = findViewById(R.id.tvInstruction3);
        imgbtnBackHelp = findViewById(R.id.imgbtnBackHelp);
        cardviewHelp = findViewById(R.id.cardviewHelp);
//        scrollViewHelp = findViewById(R.id.scrollViewHelp);
    }

    private void expand(TextView tv, Button btn) {
        if (tv.getVisibility() == View.GONE) {
            //expand animation
            TransitionManager.beginDelayedTransition(cardviewHelp, new Fade());

            tv.setVisibility(View.VISIBLE);
//            tv.setText(data);
            btn.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_baseline_expand_less, 0);

//            scrollViewHelp.requestChildFocus(tv, tv);
        }
        else {
            //collapse animation
            TransitionManager.beginDelayedTransition(cardviewHelp);

            tv.setVisibility(View.GONE);
            btn.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_baseline_expand_more, 0);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnInstruction1:
                expand(tvInstruction1, btnInstruction1);
                break;
            case R.id.btnInstruction2:
                expand(tvInstruction2, btnInstruction2);
                break;
            case R.id.btnInstruction3:
                expand(tvInstruction3, btnInstruction3);
                break;
        }
    }
}