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
    private RecyclerView searchRecyclerView;
    private List<GroupItem> groupItemList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);

        initViews();
        initData();

        return view;
    }

    private void initViews() {
        searchView = view.findViewById(R.id.searchView);
        searchRecyclerView = view.findViewById(R.id.searchRecyclerView);
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initData() {
        groupItemList = new ArrayList<>();

        /*----------------------*/

        List<SubItem> tshirts = new ArrayList<>();

        tshirts.add(new SubItem(getResources().getString(R.string.tshirt_1), 111));
        tshirts.add(new SubItem(getResources().getString(R.string.tshirt_2), 112));
        tshirts.add(new SubItem(getResources().getString(R.string.tshirt_3), 113));
        tshirts.add(new SubItem(getResources().getString(R.string.tshirt_4), 114));

        /*----------------------*/

        List<SubItem> coats = new ArrayList<>();

        coats.add(new SubItem(getResources().getString(R.string.coat_1), 311));
        coats.add(new SubItem(getResources().getString(R.string.coat_2), 312));

        /*----------------------*/

        List<SubItem> dressshirts = new ArrayList<>();

        dressshirts.add(new SubItem(getResources().getString(R.string.dshirt_1), 211));
        dressshirts.add(new SubItem(getResources().getString(R.string.dshirt_2), 212));
        dressshirts.add(new SubItem(getResources().getString(R.string.dshirt_3), 213));
        dressshirts.add(new SubItem(getResources().getString(R.string.dshirt_4), 214));

        /*----------------------*/

        List<SubItem> trousers = new ArrayList<>();

        trousers.add(new SubItem(getResources().getString(R.string.trouser_1),411));
        trousers.add(new SubItem(getResources().getString(R.string.trouser_2),412));
        trousers.add(new SubItem(getResources().getString(R.string.trouser_3),413));
        trousers.add(new SubItem(getResources().getString(R.string.trouser_4),414));

        /*----------------------*/

        List<SubItem> accessories = new ArrayList<>();

        accessories.add(new SubItem(getResources().getString(R.string.accessory_1),511));
        accessories.add(new SubItem(getResources().getString(R.string.accessory_2),512));
        accessories.add(new SubItem(getResources().getString(R.string.accessory_3),513));
        accessories.add(new SubItem(getResources().getString(R.string.accessory_4),514));
        accessories.add(new SubItem(getResources().getString(R.string.accessory_5),515));
        accessories.add(new SubItem(getResources().getString(R.string.accessory_6),516));

        /*----------------------*/

        groupItemList.add(new GroupItem("Áo", tshirts));
        groupItemList.add(new GroupItem("Sơ mi", dressshirts));
        groupItemList.add(new GroupItem("Áo khoác", coats));
        groupItemList.add(new GroupItem("Quần", trousers));
        groupItemList.add(new GroupItem("Phụ kiện", accessories));

        Adapter adapter = new Adapter(groupItemList, searchRecyclerView);
        searchRecyclerView.setAdapter(adapter);
    }

}