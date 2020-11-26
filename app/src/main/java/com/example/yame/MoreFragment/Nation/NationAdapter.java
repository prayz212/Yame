package com.example.yame.MoreFragment.Nation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.yame.R;

import java.util.List;

public class NationAdapter extends RecyclerView.Adapter<NationAdapter.ViewHolder> {

    private Context context;
    private List<Nation> countries;
    private int layout;

    public NationAdapter(Context context, List<Nation> countries, int layout) {
        this.context = context;
        this.countries = countries;
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
        Nation nation = countries.get(position);
        holder.tvCountry.setText(nation.getName());
        holder.imgTick.setVisibility(nation.isCheck() ? View.VISIBLE : View.INVISIBLE);

        holder.item.setOnClickListener(v -> {
            if (!nation.getName().equals("Vietnam")) {
                Toast.makeText(context, "Ứng dụng chưa hỗ trợ ngôn ngữ này.",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Đã đặt ngôn ngữ này.",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvCountry;
        private ImageView imgTick;
        private ConstraintLayout item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item = itemView.findViewById(R.id.item_layout);
            tvCountry = itemView.findViewById(R.id.tvNationName);
            imgTick = itemView.findViewById(R.id.imgTick);
        }
    }
}
