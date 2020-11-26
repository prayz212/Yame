package com.example.yame.network.Invoice;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InvoiceDBApi {

    //PHU
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
}
