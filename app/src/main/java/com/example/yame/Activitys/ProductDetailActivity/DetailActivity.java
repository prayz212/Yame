package com.example.yame.Activitys.ProductDetailActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;

import android.os.Bundle;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.yame.ChangeCurrency;
import com.example.yame.ProductDetailDB;
import com.example.yame.R;
import com.example.yame.network.API;
import com.example.yame.network.Cart.CartDBApi;
import com.example.yame.network.Product.GetDetailResponse;
import com.example.yame.network.Product.ProductDBApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener, ItemClickListener {

    private TextView tvProductName, tvProductPrice, tvTitleProduct, tvInstruction, tvDetails;
    private ImageButton btnBack;
    private Button btnBuy;
    private CardView cardViewExpand;
    private Button btnInstruction, btnDetails;
    private ScrollView scrollViewDetails;
    private ImageSlider imageSlider;

    private API api;
    private ProductDBApi productApi;
    private CartDBApi cardApi;
    private List<ProductDetailDB> products;
    private long id_product;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initViews();
        receiveData();
        handlerClicked();

        btnBack.setOnClickListener((v) -> {
            finish();
        });
    }

    private void handlerClicked() {
        btnInstruction.setOnClickListener(this);
        btnDetails.setOnClickListener(this);
        btnBuy.setOnClickListener(this);
    }

    private void expand(TextView tv, Button btn, String data) {
        if (tv.getVisibility() == View.GONE) {
            //expand animation
            TransitionManager.beginDelayedTransition(cardViewExpand, new Fade());

            tv.setVisibility(View.VISIBLE);
            tv.setText(data);
            btn.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_baseline_expand_less, 0);

            scrollViewDetails.requestChildFocus(tv, tv);
        }
        else {
            //collapse animation
            TransitionManager.beginDelayedTransition(cardViewExpand);

            tv.setVisibility(View.GONE);
            btn.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_baseline_expand_more, 0);
        }
    }

    private void initData() {
        ChangeCurrency format = new ChangeCurrency();
        tvProductName.setText(products.get(0).getName());
        tvProductPrice.setText((format.formatCurrency(products.get(0).getPrice())));
        tvTitleProduct.setText(products.get(0).getName());

        List<SlideModel> slideModels = new ArrayList<>();

        for (int i = 0; i < products.size(); i++) {
            slideModels.add(new SlideModel(products.get(i).getUrl(), ScaleTypes.FIT));
        }

        imageSlider.setImageList(slideModels, ScaleTypes.CENTER_CROP);
        imageSlider.setItemClickListener(this);
    }

    private void initViews() {
        tvProductName = findViewById(R.id.tvProductName);
        tvProductPrice = findViewById(R.id.tvProductPrice);
        imageSlider = findViewById(R.id.imgProduct);
        tvTitleProduct = findViewById(R.id.tvTitleProduct);
        btnBack = findViewById(R.id.imgBtnBackProduct);
        cardViewExpand = findViewById(R.id.cardViewExpand);
        tvDetails = findViewById(R.id.tvDetails);
        tvInstruction = findViewById(R.id.tvInstruction);
        btnInstruction = findViewById(R.id.btnInstruction);
        btnDetails = findViewById(R.id.btnDetails);
        scrollViewDetails = findViewById(R.id.scrollViewDetails);
        btnBuy = findViewById(R.id.btnCart);
    }

    private void receiveData() {
        Intent intent = getIntent();

        id_product = intent.getLongExtra("id", -1);

        api = new API();
        productApi = api.getProdcutDBApi();
        cardApi = api.getCartDBApi();

        getProductDetail();
    }

    private void getProductDetail() {
        Call<GetDetailResponse> call = productApi.getProductDetail(id_product);
        call.enqueue(new Callback<GetDetailResponse>() {
            @Override
            public void onResponse(Call<GetDetailResponse> call, Response<GetDetailResponse> response) {
                if (response.isSuccessful()) {
                    GetDetailResponse result = response.body();

                    if (result != null && result.status == 200) {
                        products = result.data;
                        initData();

                    } else {
                        Log.e("test", "Server fail: " + result.message);
                    }
                } else {
                    Log.e("test", "response fail: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<GetDetailResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnInstruction:
                expand(tvInstruction,btnInstruction, products.get(0).getInstruction());
                break;
            case R.id.btnDetails:
                expand(tvDetails,btnDetails, products.get(0).getDetail());
                break;
            case R.id.btnCart:
                addToCart();
                break;
        }
    }

    private void addToCart() {
        //Tam thoi de id_user la 1
        Call<com.example.yame.network.Response> call = cardApi.addToCart(1, id_product);
        call.enqueue(new Callback<com.example.yame.network.Response>() {
            @Override
            public void onResponse(Call<com.example.yame.network.Response> call, Response<com.example.yame.network.Response> response) {
                if (response.isSuccessful()) {
                    com.example.yame.network.Response result = response.body();

                    if (result != null && result.status == 200) {
                        Toast.makeText(getApplication(), "Đã thêm vào giỏ hàng.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplication(), "Đã tồn tại trong giỏ hàng.", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onItemSelected(int i) {
        String[] urls = new String[products.size()];

        urls[0] = products.get(i).getUrl();
        int ind = 1;
        for (int x = 0; x < products.size(); x++) {
            if (x != i) {
                urls[ind] = products.get(x).getUrl();
                ind++;
            }
        }

        Intent intent = new Intent(this, ShowImageActivity.class);
        intent.putExtra("url", urls);
        startActivity(intent);
    }
}