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
import com.example.yame.SearchFragment.SubItem;

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

        tshirts.add(new Type("Áo ngắn tay", 111));
        tshirts.add(new Type("Áo dài tay", 112));
        tshirts.add(new Type("Áo có cổ", 113));
        tshirts.add(new Type("Áo ba lỗ", 114));

        /*----------------------*/

        List<Type> coats = new ArrayList<>();

        coats.add(new Type("Jacket", 311));
        coats.add(new Type("Hoodie", 312));

        /*----------------------*/

        List<Type> dressshirts = new ArrayList<>();

        dressshirts.add(new Type("Sơ mi nam", 211));
        dressshirts.add(new Type("Sơ mi nữ", 212));
        dressshirts.add(new Type("Sơ mi tay dài", 213));
        dressshirts.add(new Type("Sơ mi tay ngắn", 214));

        /*----------------------*/

        List<Type> trousers = new ArrayList<>();

        trousers.add(new Type("Quân tây",411));
        trousers.add(new Type("Quân jean",412));
        trousers.add(new Type("Quân short",413));
        trousers.add(new Type("Quân kaki",414));

        /*----------------------*/

        List<Type> accessories = new ArrayList<>();

        accessories.add(new Type("Giày",511));
        accessories.add(new Type("Nón",512));
        accessories.add(new Type("Ví",513));
        accessories.add(new Type("Thắt lưng",514));
        accessories.add(new Type("Balo",515));
        accessories.add(new Type("Túi đeo",516));

        /*----------------------*/
        categoryList.add(new Category("Áo thun", R.drawable.ao_thun, tshirts));
        categoryList.add(new Category("Áo sơ mi", R.drawable.ao_so_mi, dressshirts));
        categoryList.add(new Category("Áo khoác", R.drawable.ao_khoac, coats));
        categoryList.add(new Category("Quần", R.drawable.quan, trousers));
        categoryList.add(new Category("Phụ kiện", R.drawable.phu_kien, accessories));

        TypeAdapter adapter = new TypeAdapter(categoryList, recyclerView);
        recyclerView.setAdapter(adapter);
    }
}

//Vi's Task