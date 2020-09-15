package com.example.yame.HomeFragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yame.R;
import com.example.yame.TypeActivitys.CoatHoodie;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class TypeViewHolder extends ChildViewHolder {
    private TextView mTextview;
    private View v;

    public TypeViewHolder(View itemView) {
        super(itemView);
        v = itemView;
        mTextview = itemView.findViewById(R.id.tv_recyclerview_subitem);
    }

    public void bind(final Type type) {
        mTextview.setText(type.getType());

        mTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    //chi la demo mo activity moi
                switch (type.getId()) {
                    case 11:
                        startNewActivity(CoatHoodie.class);
                        break;
                    case 21:
                        startNewActivity(CoatHoodie.class);
                        break;
                    case 22:
                        startNewActivity(CoatHoodie.class);
                        break;
                    case 23:
                        startNewActivity(CoatHoodie.class);
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
