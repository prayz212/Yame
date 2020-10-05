package com.example.yame.SearchFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.example.yame.R;

public class SearchFragment extends Fragment {

    private View view;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private List<GroupItem> group;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);

        initViews();
        initData();

        return view;
    }

    private void initData() {

        group = new ArrayList<>();

        List<SubItem> clothing = new ArrayList<>();
        clothing.add(new SubItem("Test", 11));
        clothing.add(new SubItem("Test", 12));
        clothing.add(new SubItem("Test", 13));

        group.add(new GroupItem("quan ao", clothing));

        Adapter adapter = new Adapter(group, recyclerView, R.layout.expandable_search_recyclerview_item, R.layout.expandable_search_recyclerview_subitem);
        recyclerView.setAdapter(adapter);
    }

    private void initViews() {
        recyclerView = view.findViewById(R.id.searchRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        searchView = view.findViewById(R.id.searchView);
    }
}

//Thinh's Task
