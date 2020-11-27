package com.example.yame.network.Cart;

import com.example.yame.network.Response;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CartDBApi {

    @POST("update_cart_status.php")
    @FormUrlEncoded
    Call<Response> updateCartStatus(@Field("id_cart") long id_cart, @Field("id_user") long id_user);

    @POST("add_cart_product.php")
    @FormUrlEncoded
    Call<Response> addToCart(@Field("id") long id_user, @Field("id_product") long id_product);

    @POST("update_cart_product.php")
    @FormUrlEncoded
    Call<Response> updateCartProductQuanlity(@Field("id_product") long id_product, @Field("id_cart") long id_cart, @Field("value") int value);

    @POST("delete_cart_product.php")
    @FormUrlEncoded
    Call<Response> deleteCartProduct(@Field("id_product") long id_product, @Field("id_cart") long id_cart);

    @POST("get_cart_products.php")
    @FormUrlEncoded
    Call<GetCartProductResponse> getCartProduct(@Field("id") long id_user);

}
