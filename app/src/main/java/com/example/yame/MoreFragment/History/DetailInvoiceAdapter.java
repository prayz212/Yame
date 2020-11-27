package com.example.yame.MoreFragment.History;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yame.CartProductDB;
import com.example.yame.ChangeCurrency;
import com.example.yame.R;

import java.util.List;

public class DetailInvoiceAdapter extends RecyclerView.Adapter<DetailInvoiceAdapter.ViewHolder> {

    private Context context;
    private List<CartProductDB> products;
    private int layout;

    public DetailInvoiceAdapter(Context context, List<CartProductDB> products, int layout) {
        this.context = context;
        this.products = products;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartProductDB product = products.get(position);
        ChangeCurrency format = new ChangeCurrency();

        Glide.with(context)
                .load(product.getUrl())
                .into(holder.img);

        holder.tvItem.setText(product.getName());
        holder.tvItemID.setText("Mã sản phẩm: " + product.getId());
        holder.tvItemPrice.setText(format.formatCurrency(product.getPrice()));
        holder.tvQuanlity.setText("Số lượng: " + product.getQuanlity());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends HistoryAdapter.ViewHolder {

        private ImageView img;
        private TextView tvItem, tvItemID, tvItemPrice, tvQuanlity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imgItems);
            tvItem = itemView.findViewById(R.id.tvItemsType);
            tvItemID = itemView.findViewById(R.id.tvItemsID);
            tvItemPrice = itemView.findViewById(R.id.tvItemsPrice);
            tvQuanlity = itemView.findViewById(R.id.tvQuanlity);
        }
    }

}
