package com.example.yame.Activitys.ProductListActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.yame.ProductDB;
import com.example.yame.R;
import com.example.yame.network.API;
import com.example.yame.network.Product.GetResponse;
import com.example.yame.network.Product.ProductDBApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {

    private TextView tvTitle;
    private ImageButton btnBack;
    private RecyclerView mRecyclerView;
    private CustomAdapter mAdapter;


    private List<ProductDB> productList;
    private ProductDBApi productApi;
    private API api;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        initViews();
        initData();
        config();
    }

    private void getProduct() {
        Call<GetResponse> call = productApi.getProduct(id);
        call.enqueue(new Callback<GetResponse>() {
            @Override
            public void onResponse(Call<GetResponse> call, Response<GetResponse> response) {
                if (response.isSuccessful()) {
                    GetResponse result = response.body();

                    if (result != null && result.status == 200) {
                        List<ProductDB> products = result.data;

                        productList.addAll(products);
                        mAdapter.notifyItemRangeInserted(0, products.size());
                    } else {
                        Log.e("test", "Server fail: " + result.message);
                    }
                } else {
                    Log.e("test", "response fail: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<GetResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private void initViews() {
        tvTitle = findViewById(R.id.tvTitle);
        btnBack = findViewById(R.id.imgBtnBack);
        mRecyclerView = findViewById(R.id.recyclerView);
    }

    private void initData() {
        productList = new ArrayList<>();
        mAdapter = new CustomAdapter(this, productList, R.layout.product_custom_row);
    }

    private void config() {
        //set title of activity depend on type
        String type;
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        id = intent.getIntExtra("id", -1);

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

        api = new API();
        productApi = api.getProdcutDBApi();
        getProduct();
    }
}