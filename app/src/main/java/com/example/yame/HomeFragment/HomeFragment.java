package com.example.yame.HomeFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yame.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private List<Category> categoryList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        initViews();
        initData();


        return view;
    }

    private void initViews() {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initData() {

        categoryList = new ArrayList<>();

        /*----------------------*/

        List<Type> tshirts = new ArrayList<>();

        tshirts.add(new Type(getResources().getString(R.string.tshirt_1), 111));
        tshirts.add(new Type(getResources().getString(R.string.tshirt_2), 112));
        tshirts.add(new Type(getResources().getString(R.string.tshirt_3), 113));
        tshirts.add(new Type(getResources().getString(R.string.tshirt_4), 114));

        /*----------------------*/

        List<Type> coats = new ArrayList<>();

        coats.add(new Type(getResources().getString(R.string.coat_1), 311));
        coats.add(new Type(getResources().getString(R.string.coat_2), 312));

        /*----------------------*/

        List<Type> dressshirts = new ArrayList<>();

        dressshirts.add(new Type(getResources().getString(R.string.dshirt_3), 213));
        dressshirts.add(new Type(getResources().getString(R.string.dshirt_4), 214));

        /*----------------------*/

        List<Type> trousers = new ArrayList<>();

        trousers.add(new Type(getResources().getString(R.string.trouser_1),411));
        trousers.add(new Type(getResources().getString(R.string.trouser_3),413));
        trousers.add(new Type(getResources().getString(R.string.trouser_4),414));

        /*----------------------*/

        List<Type> accessories = new ArrayList<>();

        accessories.add(new Type(getResources().getString(R.string.accessory_1),511));
        accessories.add(new Type(getResources().getString(R.string.accessory_2),512));
        accessories.add(new Type(getResources().getString(R.string.accessory_3),513));
        accessories.add(new Type(getResources().getString(R.string.accessory_4),514));
        accessories.add(new Type(getResources().getString(R.string.accessory_5),515));
        accessories.add(new Type(getResources().getString(R.string.accessory_6),516));

        /*----------------------*/
        categoryList.add(new Category(getResources().getString(R.string.category_1), R.raw.ao_thun, tshirts));
        categoryList.add(new Category(getResources().getString(R.string.category_2), R.raw.ao_so_mi, dressshirts));
        categoryList.add(new Category(getResources().getString(R.string.category_3), R.raw.ao_khoac, coats));
        categoryList.add(new Category(getResources().getString(R.string.category_4), R.raw.quan, trousers));
        categoryList.add(new Category(getResources().getString(R.string.category_5), R.raw.phu_kien, accessories));

        TypeAdapter adapter = new TypeAdapter(categoryList, recyclerView);
        recyclerView.setAdapter(adapter);
    }
}

//Vi's Task