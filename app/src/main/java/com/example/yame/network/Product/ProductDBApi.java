package com.example.yame.network.Product;

import com.example.yame.network.Cart.GetCartProductResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ProductDBApi {

    @POST("get_all_product.php")
    @FormUrlEncoded
    Call<GetResponse> getProduct(@Field("id_type") long id);

    @POST("get_product_by_id.php")
    @FormUrlEncoded
    Call<GetDetailResponse> getProductDetail(@Field("id") long id);


}
