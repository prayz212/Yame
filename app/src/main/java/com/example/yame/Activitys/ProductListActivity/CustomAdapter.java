package com.example.yame.Activitys.ProductListActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.example.yame.ChangeCurrency;
import com.example.yame.ProductDB;
import com.example.yame.R;
import com.example.yame.network.API;
import com.example.yame.network.ProductDBApi;
import com.example.yame.network.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;

    private List<ProductDB> productList;
    private ProductDBApi api;
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
        ChangeCurrency format = new ChangeCurrency();

        Glide.with(context)
                .load(product.getUrl())
                .into(holder.imageView);

        holder.tvName.setText(product.getName());

        holder.tvPrice.setText(format.formatCurrency(product.getPrice()));


        //add directly to cart fragment
        holder.imageButton.setOnClickListener((v) -> {
            addToCart(product.getId());
        });

        //open product detail activity
        holder.viewGroup.setOnClickListener((v) -> {
            openProductDetailActivity(product.getId());
        });
    }

    private void addToCart(long id_product) {
        api = API.getProdcutDBApi();
        Call<Response> call = api.addToCart(1, id_product);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<com.example.yame.network.Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    com.example.yame.network.Response result = response.body();

                    if (result != null && result.status == 200) {
                        Toast.makeText(context, "Đã thêm vào giỏ hàng.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Đã tồn tại trong giỏ hàng.", Toast.LENGTH_SHORT).show();
                        Log.e("test", "Server fail: " + result.message);
                    }
                } else {
                    Log.e("test", "response fail: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<com.example.yame.network.Response> call, Throwable t) {
                t.printStackTrace();
            }
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
