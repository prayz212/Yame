package com.example.yame.CartFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yame.R;
import com.example.yame.network.API;
import com.example.yame.network.Cart.CartDBApi;
import com.example.yame.network.Order.AddOrderResponse;
import com.example.yame.network.Order.OrderDBApi;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgBack;
    private EditText edtFullname, edtPhone, edtAddress, edtNote;
    private Button btnPay, btnBackToBuyMore;

    private API api;
    private OrderDBApi apiInvoice;
    private CartDBApi apiCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        initViews();
        Cliked();
    }

    private void Cliked() {
        imgBack.setOnClickListener(this);
        btnPay.setOnClickListener(this);
        btnBackToBuyMore.setOnClickListener(this);
    }

    private void initViews() {
        imgBack = findViewById(R.id.imgPayBack);
        btnPay = findViewById(R.id.btnPay);
        btnBackToBuyMore = findViewById(R.id.btnBackToBuyMore);
        edtFullname = findViewById(R.id.edtFullname);
        edtPhone = findViewById(R.id.edtPhone);
        edtAddress = findViewById(R.id.edtAddress);
        edtNote = findViewById(R.id.edtNote);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgPayBack:
            case R.id.btnBackToBuyMore:
                backToCard();
                break;
            case R.id.btnPay:
                boolean check = checkInput();
                if (check) {
                    api = new API();
                    insertInvoice();
                } else {
                    Toast.makeText(getBaseContext(), "Bạn cần nhập đủ thông tin về Họ tên, Số điện thoại và Địa chỉ.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private boolean checkInput() {
        if (edtFullname.getText().toString().trim().isEmpty()
            || edtPhone.getText().toString().trim().isEmpty()
            || edtAddress.getText().toString().trim().isEmpty()) {
            return false;
        }
        return true;
    }

    private void backToCard() {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }


    private void insertInvoice() {
        apiInvoice = api.getInvoiceDBApi();

        Date current = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = simpleDateFormat.format(current);

        Intent intent = getIntent();
        int total = intent.getIntExtra("total", 0);

        long id_cart = intent.getLongExtra("id_cart", 0);

        Call<AddOrderResponse> call = apiInvoice.addInvoice(1,
                id_cart,
                edtFullname.getText().toString(),
                edtPhone.getText().toString(),
                edtAddress.getText().toString(),
                edtNote.getText().toString(),
                currentDate, total);

        Log.e("test", String.valueOf(id_cart));

        call.enqueue(new Callback<AddOrderResponse>() {
            @Override
            public void onResponse(Call<AddOrderResponse> call, Response<AddOrderResponse> response) {
                if (response.isSuccessful()) {
                    AddOrderResponse result = response.body();

                    if (result != null && result.status == 200) {
                        updateCartStatus(id_cart);
                    } else {
                        Log.e("test", "Server fail: " + result.message);
                    }
                } else {
                    Log.e("test", "response fail: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<AddOrderResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void updateCartStatus(long id_cart) {
        apiCart = api.getCartDBApi();;

        Call<com.example.yame.network.Response> call = apiCart.updateCartStatus(id_cart, 1);

        call.enqueue(new Callback<com.example.yame.network.Response>() {
            @Override
            public void onResponse(Call<com.example.yame.network.Response> call, Response<com.example.yame.network.Response> response) {
                if (response.isSuccessful()) {
                    com.example.yame.network.Response result = response.body();

                    if (result != null && result.status == 200) {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("noti", "Bạn đã đặt hàng thành công.");
                        setResult(Activity.RESULT_OK,returnIntent);
                        finish();
                    } else {
                        Log.e("test", "Server fail: " + result.message);
                    }
                } else {
                    Log.e("test", "response fail: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<com.example.yame.network.Response> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}