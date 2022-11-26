package com.example.groceryplus;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.groceryplus.API.APICall;
import com.example.groceryplus.API.ProductModelClass;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GlobalProductFragment extends Fragment {
    RecyclerView recyclerView;
    List<ProductModelClass> productlist;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_global_product, container, false);
        recyclerView=root.findViewById(R.id.recyclerviewid);
        productlist=new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://makeup-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        APICall apiCall=retrofit.create(APICall.class);

        Call<List<ProductModelClass>> call=apiCall.getProductData();


        call.enqueue(new Callback<List<ProductModelClass>>() {
            @Override
            public void onResponse(Call<List<ProductModelClass>> call, Response<List<ProductModelClass>> response) {
                if(response.code()!=200)
                {
                    return;
                }

                List<ProductModelClass> ProductDataList=response.body();


                for (ProductModelClass productDataList: ProductDataList){

                    productlist.add(productDataList);

                    recyclerView.setAdapter(new ProductAdapter(getContext(),productlist));



                }

            }

            @Override
            public void onFailure(Call<List<ProductModelClass>> call, Throwable t) {

            }
        });







        return  root;
    }
}