package com.example.yame.CartFragment;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.yame.ChangeCurrency;
import com.example.yame.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.MyViewHolder> {

    private Context context;
    private List<BuyProduct> productList;
    private int layout;

    private CustomClickListener listener;

    public MyCartAdapter(Context context, List<BuyProduct> data, int layout) {
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

        final BuyProduct product = productList.get(position);
        holder.tvProductName.setText(product.getName());
        holder.tvProductID.setText(product.toStringID());
        ChangeCurrency format = new ChangeCurrency();
        holder.tvProductPrice.setText(format.formatCurrency(product.getUnitPrice()));
        holder.imgProduct.setImageResource(product.getImg().get(0));
        holder.quantityButton.setNumber(String.valueOf(product.getQuantity()));

        product.setTotalPrice(product.getUnitPrice() * product.getQuantity());

        holder.quantityButton.setOnValueChangeListener((view, oldValue, newValue) -> {

            product.setQuantity(newValue);
            int newTotal = newValue * product.getUnitPrice();
            product.setTotalPrice(newTotal);

            //update total price in cart fragment
            if (listener != null) {
                listener.onQuantityChange();
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
