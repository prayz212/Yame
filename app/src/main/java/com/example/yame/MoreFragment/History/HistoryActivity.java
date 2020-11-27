package com.example.yame.MoreFragment.History;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.yame.OrderDB;
import com.example.yame.R;
import com.example.yame.network.API;
import com.example.yame.network.Order.GetOrderResponse;
import com.example.yame.network.Order.OrderDBApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener {

    private List<OrderDB> invoices;
    private HistoryAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ImageButton btnBack;

    private API api;
    private OrderDBApi invoiceApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        initViews();

        invoices = new ArrayList<>();

        api = new API();
        invoiceApi = api.getInvoiceDBApi();
        getInvoices();

        initData();
        config();
        handlerEvents();
    }

    private void getInvoices() {
        Call<GetOrderResponse> call = invoiceApi.getInvoice(1);
        call.enqueue(new Callback<GetOrderResponse>() {
            @Override
            public void onResponse(Call<GetOrderResponse> call, Response<GetOrderResponse> response) {
                if (response.isSuccessful()) {
                    GetOrderResponse result = response.body();

                    if (result != null && result.status == 200) {
                        invoices = result.data;
                        initData();
                    } else if (result != null && result.status == 201) {
                        invoices = result.data;
                        initData();
                        Toast.makeText(HistoryActivity.this, "Bạn chưa có hoá đơn nào, nhấn vào trang chủ để lựa sản phẩm.", Toast.LENGTH_LONG).show();
                    } else {
                        Log.e("test", "Server fail: " + result.message);
                    }
                } else {
                    Log.e("test", "response fail: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<GetOrderResponse> call, Throwable t) {
                Log.e("test", "failure: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    private void handlerEvents() {
        btnBack.setOnClickListener(this);
    }

    private void initData() {
        mAdapter = new HistoryAdapter(this, invoices, R.layout.history_custom_row);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initViews() {
        mRecyclerView = findViewById(R.id.historyRecyclerView);
        btnBack = findViewById(R.id.imgBtnBack);
    }

    private void config() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}