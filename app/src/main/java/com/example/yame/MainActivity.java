package com.example.yame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.yame.Frament.CartFragment;
import com.example.yame.HomeFragment.HomeFragment;
import com.example.yame.MoreFragment.MoreFragment;
import com.example.yame.Frament.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews(); //ham khoi tao
        handleEvents(); //ham xu ly su kien
    }

    private void initViews() {
        bottomNav = findViewById(R.id.bottom_navigation);
    }

    private void handleEvents() {
        bottomNav.setOnNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    //ham xu ly khi click vao tung item trong bottom navigation
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment selectedFragment = null;

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                selectedFragment = new HomeFragment();
                break;
            case R.id.navigation_search:
                selectedFragment = new SearchFragment();
                break;
            case R.id.navigation_cart:
                selectedFragment = new CartFragment();
                break;
            case R.id.navigation_more:
                selectedFragment = new MoreFragment();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        return true;
    }
}