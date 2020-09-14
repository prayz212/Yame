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

        tshirts.add(new Type(getString(R.string.tshirt_1)));
        tshirts.add(new Type(getString(R.string.tshirt_2)));
        tshirts.add(new Type(getString(R.string.tshirt_3)));

        /*----------------------*/

        List<Type> coats = new ArrayList<>();

        coats.add(new Type(getString(R.string.coat_1)));
        coats.add(new Type(getString(R.string.coat_2)));
        coats.add(new Type(getString(R.string.coat_3)));
        coats.add(new Type(getString(R.string.coat_4)));

        /*----------------------*/

        List<Type> shirts = new ArrayList<>();

        shirts.add(new Type(getString(R.string.shirt_1)));
        shirts.add(new Type(getString(R.string.shirt_1)));

        /*----------------------*/
        categoryList.add(new Category(getString(R.string.category_1), tshirts));
        categoryList.add(new Category(getString(R.string.category_2), coats));
        categoryList.add(new Category(getString(R.string.category_3), shirts));

        TypeAdapter adapter = new TypeAdapter(categoryList);
        recyclerView.setAdapter(adapter);

    }
}

//Vi's Task