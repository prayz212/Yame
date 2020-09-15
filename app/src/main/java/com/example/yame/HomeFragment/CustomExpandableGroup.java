package com.example.yame.HomeFragment;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class CustomExpandableGroup extends ExpandableGroup<Type> {

    private int imgRes;

    public CustomExpandableGroup(String title, int img, List<Type> items) {
        super(title, items);
        this.imgRes = img;
    }

    public String getTitle() {
        return super.getTitle();
    }

    public List<Type> getItems() {
        return super.getItems();
    }

    public int getItemCount() {
        return super.getItems() == null ? 0 : super.getItems().size();
    }
}
