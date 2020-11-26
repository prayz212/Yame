package com.example.yame.network;

import com.example.yame.network.Cart.CartDBApi;
import com.example.yame.network.Cart.GetCartProductResponse;
import com.example.yame.network.Invoice.InvoiceDBApi;
import com.example.yame.network.Product.ProductDBApi;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

    private Retrofit retrofit;

    public API() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/test/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ProductDBApi getProdcutDBApi() {
        return retrofit.create(ProductDBApi.class);
    }

    public CartDBApi getCartDBApi() {
        return retrofit.create(CartDBApi.class);
    }

    public InvoiceDBApi getInvoiceDBApi() {
        return retrofit.create(InvoiceDBApi.class);
    }

}
