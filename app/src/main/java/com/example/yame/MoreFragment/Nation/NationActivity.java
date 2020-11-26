package com.example.yame.MoreFragment.Nation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.example.yame.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NationActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Nation> nations;
    private RecyclerView mRecyclerView;
    private NationAdapter nationAdapter;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nation);

        initViews();
        initData();
        config();
        handlerEvents();
    }

    private void handlerEvents() {
        btnBack.setOnClickListener(this);
    }

    private void initData() {
        nations = new ArrayList<>();

        List<String> list = Arrays.asList(getResources().getStringArray(R.array.countries_list));

        for (String c : list) {
            Nation nation = new Nation(c);

            if (c.equals("Vietnam")) {
                nation.setCheck(true);
            }
            nations.add(nation);
        }

        nationAdapter = new NationAdapter(this, nations, R.layout.nation_custom_row);
        mRecyclerView.setAdapter(nationAdapter);
    }

    private void initViews() {
        mRecyclerView = findViewById(R.id.nationRecyclerView);
        btnBack = findViewById(R.id.imgBtnBack);
    }

    private void config() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(nationAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}