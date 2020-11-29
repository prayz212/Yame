package com.example.yame.CartFragment;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.yame.CartProductDB;
import com.example.yame.ChangeCurrency;
import com.example.yame.R;
import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.MyViewHolder> {

    private Context context;
    private List<CartProductDB> productList;
    private int layout;

    private CustomClickListener listener;

    public MyCartAdapter(Context context, List<CartProductDB> data, int layout) {
        this.context = context;
        this.productList = data;
        this.layout = layout;
    }

    public void setListener(CustomClickListener listener) {
        this.listener = listener;
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

        final CartProductDB product = productList.get(position);
        holder.tvProductName.setText(product.getName());
        holder.tvProductID.setText("Mã sản phẩm: " + product.getId());

        ChangeCurrency format = new ChangeCurrency();
        holder.tvProductPrice.setText(format.formatCurrency(product.getPrice()));

        Glide.with(context)
                .load(product.getUrl())
                .into(holder.imgProduct);

        holder.quantityButton.setNumber(String.valueOf(product.getQuanlity()));


        holder.quantityButton.setOnValueChangeListener((view, oldValue, newValue) -> {

            product.setQuanlity(newValue);

            //update total price in cart fragment
            if (listener != null) {
                listener.onQuantityChange(product.getId_cart(), product.getId(), newValue);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduct;
        TextView tvProductName, tvProductID, tvProductPrice;
        ElegantNumberButton quantityButton;

        public MyViewHolder(@NonNull View item) {
            super(item);
            imgProduct = item.findViewById(R.id.imgItems);
            tvProductName = item.findViewById(R.id.tvItemsType);
            tvProductID = item.findViewById(R.id.tvItemsID);
            tvProductPrice = item.findViewById(R.id.tvItemsPrice);
            quantityButton = item.findViewById(R.id.numberButton);
        }
    }
}
