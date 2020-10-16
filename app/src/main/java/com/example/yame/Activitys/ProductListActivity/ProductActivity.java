package com.example.yame.Activitys.ProductListActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.yame.Product;
import com.example.yame.R;
import com.example.yame.Store;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {

    private TextView tvTitle;
    private ImageButton btnBack;
    private RecyclerView mRecyclerView;
    private CustomAdapter mAdapter;
    private List<Product> productList;

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
        mRecyclerView = findViewById(R.id.recyclerView);
    }

    private void initData() {
        productList = new ArrayList<>();

        //create list of image product
        List<Integer> imgs = new ArrayList<>();
        imgs.add(R.raw.demo_product);
        imgs.add(R.raw.demo_product);
        imgs.add(R.raw.demo_product);
        imgs.add(R.raw.demo_product);

        //create list of store have this product
        List<Store> storeList = new ArrayList<>();
        storeList.add(new Store(12340, "This is store name", "123 Duong 3/2 P.12 Q.10 TP.HCM"));
        storeList.add(new Store(12340, "This is store name", "123 Duong 3/2 P.12 Q.10 TP.HCM"));
        storeList.add(new Store(12340, "This is store name", "123 Duong 3/2 P.12 Q.10 TP.HCM"));
        storeList.add(new Store(12340, "This is store name", "123 Duong 3/2 P.12 Q.10 TP.HCM"));


        for (int i = 0; i < 15; i++) {
            productList.add(new Product(imgs, "T-shirt " + i, i, 100000, 1, "This is product detail", "This is instruction paragraph", storeList));
        }

        mAdapter = new CustomAdapter(this, productList, R.layout.product_custom_row);
    }

    private void config() {
        //set title of activity depend on type
        String type;
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        tvTitle.setText(type);

        //back to the previous activity
        btnBack.setOnClickListener((v) -> {
            finish();
        });

        GridLayoutManager manager = new GridLayoutManager(this, 2);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new CustomDecoration(2, 2, true));

    }
}