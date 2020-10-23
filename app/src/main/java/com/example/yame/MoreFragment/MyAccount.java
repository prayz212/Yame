package com.example.yame.MoreFragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yame.R;

public class MyAccount extends AppCompatActivity {

    private EditText line_email, line_password;
    private TextView txt_forgotPassword;
    private Button btnSignIn,btnCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
    }
}