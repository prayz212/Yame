package com.example.yame.network;

import com.example.yame.network.Cart.CartDBApi;
import com.example.yame.network.Order.OrderDBApi;
import com.example.yame.network.Product.ProductDBApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

    private Retrofit retrofit;

    public API() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/mobile/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ProductDBApi getProdcutDBApi() {
        return retrofit.create(ProductDBApi.class);
    }

    public CartDBApi getCartDBApi() {
        return retrofit.create(CartDBApi.class);
    }

    public OrderDBApi getInvoiceDBApi() {
        return retrofit.create(OrderDBApi.class);
    }

}
