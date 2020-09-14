package com.example.yame.HomeFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yame.R;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.listeners.OnGroupClickListener;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class TypeAdapter extends ExpandableRecyclerViewAdapter<CategoryViewHolder, TypeViewHolder> implements OnGroupClickListener {
    public TypeAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public CategoryViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_recyclerview_item, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public TypeViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_recyclerview_subitem, parent, false);
        return new TypeViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(TypeViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final Type type = (Type) group.getItems().get(childIndex);
        holder.bind((type));
    }

    @Override
    public void onBindGroupViewHolder(CategoryViewHolder holder, int flatPosition, ExpandableGroup group) {
        final Category category = (Category) group;
        holder.bind(category);
    }
}
