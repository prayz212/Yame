package com.example.yame.HomeFragment;

import com.example.yame.HomeFragment.Type;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class Category extends ExpandableGroup<Type> {
    public Category(String title, List<Type> items) {
        super(title, items);
    }
}
