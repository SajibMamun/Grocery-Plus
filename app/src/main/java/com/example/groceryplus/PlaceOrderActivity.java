package com.example.groceryplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.groceryplus.Models.MycartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlaceOrderActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();

        List<MycartModel> list= (ArrayList<MycartModel>) getIntent().getSerializableExtra("itemList");

    if(list!=null && list.size()>0)
    {
        for (MycartModel model:list)
        {

            final HashMap<String ,Object> carMap=new HashMap<>();
            carMap.put("productName",model.getProductName());
            carMap.put("productPrice",model.getProductPrice());
            carMap.put("currentDate",model.getCurrentDate());
            carMap.put("currentTime",model.getCurrentTime());
            carMap.put("totalQuantity",model.getTotalQuantity());
            carMap.put("totalprice",model.getTotalprice());
            carMap.put("productImage",model.getProductImage());

            firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                    .collection("MyOrder").add(carMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            Toast.makeText(getApplicationContext(),"Order Placed",Toast.LENGTH_SHORT).show();

                        }
                    });

        }
    }
    }
}