package com.example.yame.HomeFragment;

import com.example.yame.HomeFragment.Type;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class Category extends CustomExpandableGroup {

    private int imgRes;

    public Category(String title, int img, List<Type> items) {
        super(title, img, items);
        this.imgRes = img;
    }

    public int getImgRes() {
        return imgRes;
    }
}
