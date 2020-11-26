package com.example.yame.MoreFragment.Store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.yame.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoreLocation extends AppCompatActivity implements SearchView.OnQueryTextListener, View.OnClickListener {

    private List<String> stores;
    private RecyclerView mRecyclerView;
    private StoreAdapter storeAdapter;
    private SearchView searchView;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_location);

        initViews();
        initData();
        config();
        handlerEvents();
    }

    private void handlerEvents() {
        btnBack.setOnClickListener(this);
        searchView.setOnQueryTextListener(this);
    }

    private void initData() {
        stores = new ArrayList<>();

        List<String> list = Arrays.asList(getResources().getStringArray(R.array.store_list));
        stores.addAll(list);

        storeAdapter = new StoreAdapter(this, stores, R.layout.store_custom_row_item);
        mRecyclerView.setAdapter(storeAdapter);
    }

    private void initViews() {
        mRecyclerView = findViewById(R.id.recyclerViewStore);
        searchView = findViewById(R.id.searchViewStore);
        btnBack = findViewById(R.id.imgBtnBack);
    }

    private void config() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(storeAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        if (stores.contains(query)) {
            storeAdapter.getFilter().filter(query);
        } else {
            Toast.makeText(this, "Không tìm thấy cửa hàng này",Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        storeAdapter.getFilter().filter(newText);
        Log.e("test", newText);
        return true;
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}