package com.example.yame.SearchFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yame.R;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private View view;
    private SearchView searchView;
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

        tshirts.add(new SubItem("Áo ngắn tay", 111));
        tshirts.add(new SubItem("Áo dài tay", 112));
        tshirts.add(new SubItem("Áo có cổ", 113));
        tshirts.add(new SubItem("Áo ba lỗ", 114));

        /*----------------------*/

        List<SubItem> dressshirts = new ArrayList<>();

        dressshirts.add(new SubItem("Sơ mi nam", 211));
        dressshirts.add(new SubItem("Sơ mi nữ", 212));

        /*----------------------*/

        List<SubItem> coats = new ArrayList<>();

        coats.add(new SubItem("Jacket", 311));
        coats.add(new SubItem("Hoodie", 312));

        /*----------------------*/

        List<SubItem> trousers = new ArrayList<>();

        trousers.add(new SubItem("Quân tây",411));
        trousers.add(new SubItem("Quân jean",412));
        trousers.add(new SubItem("Quân short",413));
        trousers.add(new SubItem("Quân kaki",414));

        /*----------------------*/

        List<SubItem> accessories = new ArrayList<>();

        accessories.add(new SubItem("Giày",511));
        accessories.add(new SubItem("Nón",512));
        accessories.add(new SubItem("Ví",513));
        accessories.add(new SubItem("Thắt lưng",514));
        accessories.add(new SubItem("Balo",515));
        accessories.add(new SubItem("Túi đeo",516));

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
