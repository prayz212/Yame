package com.example.yame.CartFragment;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yame.R;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.MyViewHolder> {

    private Context context;
    private List<Item> items;
    private int layout;

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
        holder.tvItemsPrice.setText(String.valueOf(item.getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Đã click " + item.getType(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgItems;
        TextView tvItemsType, tvItemsID, tvItemsPrice;

        public MyViewHolder(@NonNull View item) {
            super(item);
            imgItems = item.findViewById(R.id.imgItems);
            tvItemsType = item.findViewById(R.id.tvItemsType);
            tvItemsID = item.findViewById(R.id.tvItemsID);
            tvItemsPrice = item.findViewById(R.id.tvItemsPrice);
        }
    }
}
