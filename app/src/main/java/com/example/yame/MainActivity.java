package com.example.yame;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.yame.CartFragment.CartFragment;
import com.example.yame.HomeFragment.HomeFragment;
import com.example.yame.MoreFragment.MoreFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (networkConnected()) {
            createProcess();
        } else {
            showDialog();
        }
    }

    private void showDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Không có kết nối mạng")
                .setMessage("Thiết bị hiện không có kết nối mạng. Vui lòng bật kết nối Wifi hoặc Dữ liệu di động để sử dụng.")
                .setNegativeButton("Thoát", (dialog, which) -> {
                    finish();
                })
                .setPositiveButton("Mở cài đặt", (dialog, which) -> {
                    startActivityForResult(new Intent(Settings.ACTION_WIRELESS_SETTINGS), 999);
                })
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 999) {
            if (!networkConnected()) {
                Toast.makeText(this, "Không có kết nối mạng. Vui lòng kiểm tra lại.", Toast.LENGTH_LONG).show();
                showDialog();
            } else {
                createProcess();
            }
        }
    }

    private void createProcess() {
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

    private boolean networkConnected() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}