package com.example.yame.CartFragment;

import android.os.Bundle;
import android.text.TextUtils;
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

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.yame.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class CartFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private List<Item> itemList;
    private MyCartAdapter adapter;
    private TextView tvCountItem, tvTotal;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_cart, container, false);

        initView();
        initData();
        config();
        handlerEvents();

        return view;
    }

    private void handlerEvents() {

    }

    private void initView() {
        tvCountItem = view.findViewById(R.id.tvCountItem);
        recyclerView = view.findViewById(R.id.recyclerView);
        tvTotal = view.findViewById(R.id.tvTotalPrice);
    }

    private void initData() {
        itemList = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            itemList.add(new Item(R.drawable.cart_demo, "T-shirt " + i, "ID T-shirt " + i, 100000, 1));
        }

        adapter = new MyCartAdapter(getContext(), itemList, R.layout.cart_custom_row_item);
    }

    private void config() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        tvCountItem.setText("Giỏ hàng (" + itemList.size() + ")");
        calTotalPrice();
    }

    private void calTotalPrice() {
        int total = 0;
        for (Item i : itemList) {
            total += i.getTotalPrice();
        }

        tvTotal.setText(formatCurrency(String.valueOf(total)));
    }

    private String formatCurrency(String price) {
        NumberFormat format = new DecimalFormat("#,##0.00");// #,##0.00 ¤ (¤:// Currency symbol)
        format.setCurrency(Currency.getInstance(Locale.US));//Or default locale

        price = (!TextUtils.isEmpty(price)) ? price : "0";
        price = price.trim();
        price = format.format(Double.parseDouble(price));
        price = price.replaceAll(",", "\\.");

        if (price.endsWith(".00")) {
            int centsIndex = price.lastIndexOf(".00");
            if (centsIndex != -1) {
                price = price.substring(0, centsIndex);
            }
        }
        price = String.format("%s đ", price);

        return price;
    }
}

//Tri's Task
