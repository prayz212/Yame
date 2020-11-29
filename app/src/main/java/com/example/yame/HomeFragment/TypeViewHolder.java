package com.example.yame.HomeFragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.yame.R;
import com.example.yame.Activitys.ProductListActivity.ProductActivity;
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
                switch (type.getId()) {
                    //T-shirts
                case 111:
                    startNewActivity(ProductActivity.class, v.getResources().getString(R.string.tshirt_1), 111);
                    break;
                case 112:
                    startNewActivity(ProductActivity.class, v.getResources().getString(R.string.tshirt_2), 112);
                    break;
                case 113:
                    startNewActivity(ProductActivity.class, v.getResources().getString(R.string.tshirt_3), 113);
                    break;
                case 114:
                    startNewActivity(ProductActivity.class, v.getResources().getString(R.string.tshirt_4), 114);
                    break;

                //Coats
                case 311:
                    startNewActivity(ProductActivity.class, v.getResources().getString(R.string.coat_1), 311);
                    break;
                case 312:
                    startNewActivity(ProductActivity.class, v.getResources().getString(R.string.coat_2), 312);
                    break;

                //Dressshirts
                case 213:
                    startNewActivity(ProductActivity.class, v.getResources().getString(R.string.dshirt_3), 213);
                    break;
                case 214:
                    startNewActivity(ProductActivity.class, v.getResources().getString(R.string.dshirt_4), 214);
                    break;

                //Trousers
                case 411:
                    startNewActivity(ProductActivity.class, v.getResources().getString(R.string.trouser_1), 411);
                    break;
                case 413:
                    startNewActivity(ProductActivity.class, v.getResources().getString(R.string.trouser_3), 413);
                    break;
                case 414:
                    startNewActivity(ProductActivity.class, v.getResources().getString(R.string.trouser_4), 414);
                    break;

                //Accessories
                case 511:
                    startNewActivity(ProductActivity.class, v.getResources().getString(R.string.accessory_1), 511);
                    break;
                case 512:
                    startNewActivity(ProductActivity.class, v.getResources().getString(R.string.accessory_2), 512);
                    break;
                case 513:
                    startNewActivity(ProductActivity.class, v.getResources().getString(R.string.accessory_3), 513);
                    break;
                case 514:
                    startNewActivity(ProductActivity.class, v.getResources().getString(R.string.accessory_4), 514);
                    break;
                case 515:
                    startNewActivity(ProductActivity.class, v.getResources().getString(R.string.accessory_5), 515);
                    break;
                case 516:
                    startNewActivity(ProductActivity.class, v.getResources().getString(R.string.accessory_6), 516);
                    break;
                }
            }
        });
    }

    private void startNewActivity(Class toActivity, String type, int id) {
        Context context = v.getContext();
        Intent intent = new Intent(context, toActivity);
        intent.putExtra("type", type);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }
}
