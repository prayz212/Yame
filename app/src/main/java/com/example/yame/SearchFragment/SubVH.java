package com.example.yame.SearchFragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.yame.R;
import com.example.yame.TypeActivitys.ProductActivity;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class SubVH extends ChildViewHolder {
    private TextView mTextview;
    private View v;

    public SubVH(View itemView) {
        super(itemView);
        v = itemView;
        mTextview = itemView.findViewById(R.id.tv_search_recyclerview_subitem);
    }

    public void bind(final SubItem subItem) {
        mTextview.setText(subItem.getType());

        mTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    //chi la demo mo activity moi
                switch (subItem.getId()) {
                    case 11:
                        startNewActivity(ProductActivity.class);
                        break;
                    case 21:
                        startNewActivity(ProductActivity.class);
                        break;
                    case 22:
                        startNewActivity(ProductActivity.class);
                        break;
                    case 23:
                        startNewActivity(ProductActivity.class);
                        break;
                }

            }
        });
    }

    private void startNewActivity(Class toActivity) {
        Context context = v.getContext();
        Intent intent = new Intent(context, toActivity);
        context.startActivity(intent);
    }
}
