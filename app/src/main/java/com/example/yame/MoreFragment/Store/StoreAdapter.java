package com.example.yame.MoreFragment.Store;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yame.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> implements Filterable {

    private Context context;
    private List<String> data;
    private List<String> temp;
    private int layout;

    public StoreAdapter(Context context, List<String> data, int layout) {
        this.context = context;
        this.data = data;
        this.layout = layout;
        temp = new ArrayList<>();
        temp.addAll(data);
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
        holder.tvStoreName.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<String> filtered = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filtered.addAll(temp);
            } else {
                for (String store: temp) {
                    if (store.toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filtered.add(store);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filtered;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            data.clear();
            data.addAll((Collection<? extends String>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvStoreName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStoreName = itemView.findViewById(R.id.tvStoreName);
        }
    }
}
