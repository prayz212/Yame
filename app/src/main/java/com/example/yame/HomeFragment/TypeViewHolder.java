package com.example.yame.HomeFragment;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yame.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class TypeViewHolder extends ChildViewHolder {
    private TextView mTextview;
    private View v;

    public TypeViewHolder(View itemView) {
        super(itemView);
        v = itemView;
        mTextview = itemView.findViewById(R.id.tv_recyclerview_subitem);
    }

    public void bind(Type type) {
        mTextview.setText(type.getType());

        mTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
