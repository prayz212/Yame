package com.example.yame.SearchFragment;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class GroupItem extends ExpandableGroup<com.example.yame.SearchFragment.SubItem> {
    public GroupItem(String title, List<SubItem> items) {
        super(title, items);
    }
}
