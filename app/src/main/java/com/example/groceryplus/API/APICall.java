package com.example.groceryplus.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APICall {
    @GET("api/v1/products.json")
    Call<List<ProductModelClass>> getProductData();
}
