package com.example.yame.HomeFragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.yame.R;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.listeners.OnGroupClickListener;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

public class TypeAdapter extends ExpandableRecyclerViewAdapter<CategoryViewHolder, TypeViewHolder> implements OnGroupClickListener {

    private View groupView, childView;
    private RecyclerView recyclerView;

    public TypeAdapter(List<? extends ExpandableGroup> groups, RecyclerView recyclerView) {
        super(groups);
        this.recyclerView = recyclerView;
    }

    @Override
    public CategoryViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        groupView = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_recyclerview_item, parent, false);
        return new CategoryViewHolder(groupView);
    }

    @Override
    public TypeViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        childView = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_recyclerview_subitem, parent, false);
        return new TypeViewHolder(childView);
    }

    @Override
    public void onBindChildViewHolder(TypeViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final Type type = (Type) group.getItems().get(childIndex);
        holder.bind(type);
    }

    @Override
    public void onBindGroupViewHolder(CategoryViewHolder holder, int flatPosition, ExpandableGroup group) {
        final Category category = (Category) group;
        holder.bind(category);
    }

    @Override
    public boolean onGroupClick(int flatPos) {
        int beforeClick = super.getItemCount();
        boolean result = super.onGroupClick(flatPos);
        int afterClick = super.getItemCount();

        if (!result) {
            if (beforeClick < afterClick) {
                recyclerView.smoothScrollToPosition(flatPos + (afterClick - beforeClick));
            }
        }

        return result;
    }
}
