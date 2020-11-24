package com.example.yame.Activitys.ProductListActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yame.Activitys.ProductDetailActivity.DetailActivity;
import com.example.yame.ProductDB;
import com.example.yame.R;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;

    private List<ProductDB> productList;
    private int layout;

    public CustomAdapter(Context context, List<ProductDB> productList, int layout) {
        this.context = context;
        this.productList = productList;
        this.layout = layout;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {

        ProductDB product = productList.get(position);

        Glide.with(context)
                .load(product.getUrl())
                .into(holder.imageView);

        holder.tvName.setText(product.getName());

        holder.tvPrice.setText(String.valueOf(product.getPrice()));


        //add directly to cart fragment
        holder.imageButton.setOnClickListener((v) -> {
            Toast.makeText(context, "clicked add to cart", Toast.LENGTH_SHORT).show();
        });

        //open product detail activity
        holder.viewGroup.setOnClickListener((v) -> {
            openProductDetailActivity(product.getId());
        });
    }

    private void openProductDetailActivity(long id) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvName, tvPrice;
        ImageButton imageButton;
        ConstraintLayout viewGroup;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img);
            tvName = itemView.findViewById(R.id.tvName);
            imageButton = itemView.findViewById(R.id.btn_add_to_cart);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            viewGroup = itemView.findViewById(R.id.product_layout);
        }
    }

}
