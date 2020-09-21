package com.example.yame.MoreFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.yame.R;

public class MoreFragment extends Fragment implements View.OnClickListener{
    private View view;
    private Button btn_login, btn_notifications, btn_stores, btn_languages_countries, btn_help;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_more, container, false);

        initViews();
        buttonClicked();

        return view;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                Toast.makeText(getActivity(), "Đăng ký",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_notifications:
                Toast.makeText(getActivity(), "Thông báo",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_stores:
                Toast.makeText(getActivity(), "Cửa hàng",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_languages_countries:
                Toast.makeText(getActivity(), "Ngôn ngữ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_help:
                Toast.makeText(getActivity(), "Giúp đỡ",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void buttonClicked() {
        btn_login.setOnClickListener(this);
        btn_notifications.setOnClickListener(this);
        btn_stores.setOnClickListener(this);
        btn_languages_countries.setOnClickListener(this);
        btn_help.setOnClickListener(this);
    }

    private void initViews() {
        btn_login = view.findViewById(R.id.btn_login);
        btn_notifications = view.findViewById(R.id.btn_notifications);
        btn_stores = view.findViewById(R.id.btn_stores);
        btn_languages_countries = view.findViewById(R.id.btn_languages_countries);
        btn_help = view.findViewById(R.id.btn_help);
    }
}

//Phu's Task
