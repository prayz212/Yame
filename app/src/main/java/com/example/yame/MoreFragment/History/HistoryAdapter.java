package com.example.yame.MoreFragment.History;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yame.ChangeCurrency;
import com.example.yame.InvoiceDB;
import com.example.yame.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private Context context;
    private List<InvoiceDB> invoices;
    private int layout;

    public HistoryAdapter(Context context, List<InvoiceDB> invoices, int layout) {
        this.context = context;
        this.invoices = invoices;
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
        InvoiceDB invoice = invoices.get(position);
        ChangeCurrency format = new ChangeCurrency();

        holder.tvInvoiceID.setText("Mã hoá đơn: " + invoice.getId());
        holder.tvTotalPrice.setText("Tổng tiền: " + format.formatCurrency(invoice.getTotal()));
        holder.tvDate.setText("Ngày đặt: " + invoice.getDate());

        int status = invoice.getStatus();

        switch (status) {
            case 0:
                holder.imgStatus.setImageDrawable(context.getDrawable(R.drawable.ic_delivery));
                break;
            case 1:
                holder.imgStatus.setImageDrawable(context.getDrawable(R.drawable.ic_check));
                break;
            case 2:
                holder.imgStatus.setImageDrawable(context.getDrawable(R.drawable.error));
                break;
        }

        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailInvoiceActivity.class);
            intent.putExtra("id_invoice", invoice.getId());
            intent.putExtra("id_cart", invoice.getId_cart());

            Log.e("test", invoice.getId() + "-" + invoice.getId_cart());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return invoices.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView tvInvoiceID, tvTotalPrice, tvDate;
        private ImageView imgStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardViewInvoice);
            tvInvoiceID = itemView.findViewById(R.id.tvTitle);
            tvTotalPrice = itemView.findViewById(R.id.tvTotalPrice);
            tvDate = itemView.findViewById(R.id.tvDate);
            imgStatus = itemView.findViewById(R.id.imgStatus);
        }
    }
}
