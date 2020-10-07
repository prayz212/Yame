package com.example.yame.CartFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yame.HomeFragment.Category;
import com.example.yame.R;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private List<Item> items;
    private MyCartAdapter adapter;
    private TextView tvCountItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_cart, container, false);

        initView();
        initItems();

        return view;
    }

    private void initItems() {
        items = Item.generate(15);

        adapter = new MyCartAdapter(getContext(), items, R.layout.custom_row_items);

        LinearLayoutManager manager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,
                false);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                LinearLayoutManager.VERTICAL));

        countItem(items.size());
    }

    private void countItem(int size) {
        tvCountItem.setText("Giỏ hàng (" + size + ")");
    }

    private void initView() {
        tvCountItem = view.findViewById(R.id.tvCountItem);
        recyclerView = view.findViewById(R.id.recyclerView);
    }
}

//Tri's Task
