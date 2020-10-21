package com.example.yame.Activitys.ProductDetailActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yame.R;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {
    private Context context;
    private List<Integer> imgR;
    private int layout;

    public ImageAdapter(Context context, List<Integer> imgR, int layout) {
        this.context = context;
        this.imgR = imgR;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layout, parent, false);
        ImageHolder holder = new ImageHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        holder.imgView.setImageResource(imgR.get(position));
    }

    @Override
    public int getItemCount() {
        return imgR.size();
    }

    public static class ImageHolder extends RecyclerView.ViewHolder {
        ImageView imgView;

        public ImageHolder(@NonNull View itemView) {
            super(itemView);

            imgView = itemView.findViewById(R.id.image_product);
        }
    }
}
