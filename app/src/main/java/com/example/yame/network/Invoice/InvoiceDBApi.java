package com.example.yame.network.Invoice;

import com.example.yame.network.Cart.GetCartProductResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InvoiceDBApi {

    @POST("add_invoice.php")
    @FormUrlEncoded
    Call<AddInvoiceResponse> addInvoice(@Field("id_user") long id_user,
                                        @Field("id_cart") long id_cart,
                                        @Field("name") String name,
                                        @Field("phone") String phone,
                                        @Field("address") String address,
                                        @Field("note") String note,
                                        @Field("date") String date,
                                        @Field("total") long total);

    @POST("get_all_invoice.php")
    @FormUrlEncoded
    Call<GetInvoiceResponse> getInvoice(@Field("id") long id_user);

    @POST("get_invoice_by_id.php")
    @FormUrlEncoded
    Call<GetCartProductResponse> getDetailInvoice(@Field("id_cart") long id_cart, @Field("id_invoice") long id_invoice);

}
