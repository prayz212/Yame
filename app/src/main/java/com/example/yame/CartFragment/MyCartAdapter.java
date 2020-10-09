package com.example.yame.CartFragment;

import android.content.Context;
import android.media.Image;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.yame.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.MyViewHolder> {

    private Context context;
    private List<Item> items;
    private int layout;

    private String totalPrice = "";

    public MyCartAdapter(Context context, List<Item> data, int layout) {
        this.context = context;
        this.items = data;
        this.layout = layout;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View root = inflater.inflate(layout, parent, false);
        MyViewHolder holder = new MyViewHolder(root);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Item item = items.get(position);
        holder.tvItemsType.setText(item.getType());
        holder.tvItemsID.setText(item.getID());
        holder.imgItems.setImageResource(item.getImg());

        int total = item.getPrice() * item.getQuantity();
        item.setTotalPrice(total);
        totalPrice = formatCurrency(String.valueOf(total));
        holder.tvItemsPrice.setText(totalPrice);

        holder.quantityButton.setOnValueChangeListener((view, oldValue, newValue) -> {
            item.setQuantity(newValue);
            int newTotal = newValue * item.getPrice();
            item.setTotalPrice(newTotal);
            totalPrice = formatCurrency(String.valueOf(newTotal));
            holder.tvItemsPrice.setText(totalPrice);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgItems;
        TextView tvItemsType, tvItemsID, tvItemsPrice;
        ElegantNumberButton quantityButton;

        public MyViewHolder(@NonNull View item) {
            super(item);
            imgItems = item.findViewById(R.id.imgItems);
            tvItemsType = item.findViewById(R.id.tvItemsType);
            tvItemsID = item.findViewById(R.id.tvItemsID);
            tvItemsPrice = item.findViewById(R.id.tvItemsPrice);
            quantityButton = item.findViewById(R.id.numberButton);
        }
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
