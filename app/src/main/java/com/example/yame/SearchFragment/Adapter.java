package com.example.yame.SearchFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.yame.R;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.listeners.OnGroupClickListener;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class Adapter extends ExpandableRecyclerViewAdapter<GroupVH, SubVH> implements OnGroupClickListener {

    private View groupView, childView;
    private RecyclerView recyclerView;

    public Adapter(List<? extends ExpandableGroup> groups, RecyclerView recyclerView) {
        super(groups);
        this.recyclerView = recyclerView;
    }

    @Override
    public GroupVH onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        groupView = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_search_recyclerview_item, parent, false);
        return new GroupVH(groupView);
    }

    @Override
    public SubVH onCreateChildViewHolder(ViewGroup parent, int viewType) {
        childView = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_search_recyclerview_subitem, parent, false);
        return new SubVH(childView);
    }

    @Override
    public void onBindChildViewHolder(SubVH holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final SubItem subItem = (SubItem) group.getItems().get(childIndex);
        holder.bind(subItem);
    }

    @Override
    public void onBindGroupViewHolder(GroupVH holder, int flatPosition, ExpandableGroup group) {
        final GroupItem groupItem = (GroupItem) group;
        holder.bind(groupItem);
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
