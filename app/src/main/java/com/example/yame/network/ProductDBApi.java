package com.example.yame.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProductDBApi {

    @POST("get_all_product.php")
    @FormUrlEncoded
    Call<GetResponse> getProduct(@Field("id_type") long id);

    @POST("get_product_by_id.php")
    @FormUrlEncoded
    Call<GetDetailResponse> getProductDetail(@Field("id") long id);

    @POST("get_cart_products.php")
    @FormUrlEncoded
    Call<GetCartProductResponse> getCartProduct(@Field("id") long id_user);
}
