package com.example.yame.HomeFragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yame.R;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class CategoryViewHolder extends GroupViewHolder {
    private TextView mTextview;
    private ImageView mImageview;

    public CategoryViewHolder(View itemView) {
        super(itemView);

        mTextview = itemView.findViewById(R.id.tv_recyclerview_item);
        mImageview = itemView.findViewById(R.id.img_recyclerview_item);
    }

    public void bind(Category category) {
        mImageview.setImageResource(category.getImgRes());
        mTextview.setText(category.getTitle());
    }
}
