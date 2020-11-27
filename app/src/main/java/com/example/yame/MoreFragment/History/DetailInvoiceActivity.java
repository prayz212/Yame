package com.example.yame.MoreFragment.History;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import com.example.yame.CartProductDB;
import com.example.yame.R;
import com.example.yame.network.API;
import com.example.yame.network.Cart.GetCartProductResponse;
import com.example.yame.network.Invoice.InvoiceDBApi;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailInvoiceActivity extends AppCompatActivity implements View.OnClickListener {

    private List<CartProductDB> products;
    private RecyclerView mRecyclerView;
    private DetailInvoiceAdapter mAdapter;
    private ImageButton btnBack;

    private API api;
    private InvoiceDBApi invoiceApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_invoice);

        receiveData();
        initViews();
        handlerEvents();
    }

    private void receiveData() {
        Intent intent = getIntent();
        long invoice = intent.getLongExtra("id_invoice", -1);
        long cart = intent.getLongExtra("id_cart", -1);

        Log.e("test", invoice + "-" + cart);

        api = new API();
        invoiceApi = api.getInvoiceDBApi();

        getInvoiceProducts(cart, invoice);
    }

    private void getInvoiceProducts(long cart, long invoice) {
        Call<GetCartProductResponse> call = invoiceApi.getDetailInvoice(cart, invoice);
        call.enqueue(new Callback<GetCartProductResponse>() {
            @Override
            public void onResponse(Call<GetCartProductResponse> call, Response<GetCartProductResponse> response) {
                if (response.isSuccessful()) {
                    GetCartProductResponse result = response.body();

                    if (result != null && result.status == 200) {
                        products = result.data;
                        initData();
                    } else {
                        Log.e("test", "Server fail: " + result.message);
                    }
                } else {
                    Log.e("test", "response fail: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<GetCartProductResponse> call, Throwable t) {
                Log.e("test", "failure: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    private void handlerEvents() {
        btnBack.setOnClickListener(this);
    }

    private void initData() {
        mAdapter = new DetailInvoiceAdapter(this, products, R.layout.detail_invoice_custom_row);

        config();
    }

    private void initViews() {
        mRecyclerView = findViewById(R.id.detailInvoiceRecyclerView);
        btnBack = findViewById(R.id.imgBtnBack);
    }

    private void config() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}