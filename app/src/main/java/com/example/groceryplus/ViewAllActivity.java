package com.example.groceryplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.groceryplus.Adapters.ViewAllAdapter;
import com.example.groceryplus.Models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {
    List<ViewAllModel> viewAllModelList;
    ViewAllAdapter viewAllAdapter;
    RecyclerView recyclerView;
    FirebaseFirestore firestore;
    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);



        toolbar=findViewById(R.id.toolbarid);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firestore=FirebaseFirestore.getInstance();

        String type=getIntent().getStringExtra("type");
        recyclerView=findViewById(R.id.view_all_rec_id);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewAllModelList=new ArrayList<>();
        viewAllAdapter=new ViewAllAdapter(this,viewAllModelList);
        recyclerView.setAdapter(viewAllAdapter);




        // for getting fruits....
        if(type!=null && type.equalsIgnoreCase("fruit"))
        {
            firestore.collection("AllProducts").whereEqualTo("type","fruit").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel=documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                    }
                }
            });
        }




        // for getting vegetable....
        if(type!=null && type.equalsIgnoreCase("vegetable"))
        {
            firestore.collection("AllProducts").whereEqualTo("type","vegetable").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel=documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                    }
                }
            });
        }

        // for getting products....
        if(type!=null && type.equalsIgnoreCase("product"))
        {
            firestore.collection("AllProducts").whereEqualTo("type","product").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel=documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                    }
                }
            });
        }

        //for getting fish
        if(type!=null && type.equalsIgnoreCase("fish"))
        {
            firestore.collection("AllProducts").whereEqualTo("type","fish").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel=documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                    }
                }
            });
        }


   //for getting chocolate
        if(type!=null && type.equalsIgnoreCase("chocolate"))
        {
            firestore.collection("AllProducts").whereEqualTo("type","chocolate").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel=documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                    }
                }
            });
        }
        //for getting cosmetics
        if(type!=null && type.equalsIgnoreCase("cosmetic"))
        {
            firestore.collection("AllProducts").whereEqualTo("type","cosmetic").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel=documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                    }
                }
            });
        }

        //for getting drinks
        if(type!=null && type.equalsIgnoreCase("drinks"))
        {
            firestore.collection("AllProducts").whereEqualTo("type","drinks").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel=documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                    }
                }
            });
        }

        //for getting egg
        if(type!=null && type.equalsIgnoreCase("egg"))
        {
            firestore.collection("AllProducts").whereEqualTo("type","egg").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel=documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                    }
                }
            });
        }

        //for getting milk
        if(type!=null && type.equalsIgnoreCase("milk"))
        {
            firestore.collection("AllProducts").whereEqualTo("type","milk").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel=documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                    }
                }
            });
        }


        //for getting plastic
        if(type!=null && type.equalsIgnoreCase("plastic"))
        {
            firestore.collection("AllProducts").whereEqualTo("type","plastic").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel=documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                    }
                }
            });
        }


    }
}