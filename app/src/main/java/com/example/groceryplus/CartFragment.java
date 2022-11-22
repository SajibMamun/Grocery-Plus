package com.example.groceryplus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.groceryplus.Adapters.MycartAdapter;
import com.example.groceryplus.Models.MycartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CartFragment extends Fragment {

    RecyclerView recyclerView;
    FirebaseAuth firebaseAuth;
    MycartAdapter mycartAdapter;
    List<MycartModel> mycartModelList;
    FirebaseFirestore db;
    TextView totalPriceTv;
    ProgressBar progressBar;
    int totalprice = 0;
    Button placeorderbtn;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_cart, container, false);

        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();


        progressBar = root.findViewById(R.id.progressbarid);
        progressBar.setVisibility(View.VISIBLE);


        totalPriceTv = root.findViewById(R.id.totalpriceTv);
        mycartModelList = new ArrayList<>();



    /*    LocalBroadcastManager.getInstance(getActivity())
                .registerReceiver(mMessageReciver, new IntentFilter("TotalAllAmmount"));

     */
        placeorderbtn=root.findViewById(R.id.placeorderbtnid);
        placeorderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PlaceOrderActivity.class);
                 intent.putExtra("itemList", (Serializable) mycartModelList);
                startActivity(intent);

            }
        });


        recyclerView = root.findViewById(R.id.cartRecyclerviewid);
        recyclerView.setVisibility(View.GONE);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mycartAdapter = new MycartAdapter(getActivity(), mycartModelList);
        recyclerView.setAdapter(mycartAdapter);

        db.collection("CurrentUser").document(firebaseAuth.getUid())
                .collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                            MycartModel mycartModel = documentSnapshot.toObject(MycartModel.class);
                            mycartModelList.add(mycartModel);

                            totalprice += mycartModel.getTotalprice();

                            totalPriceTv.setText("Total Bill: " + totalprice + " ৳");


                            mycartAdapter.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                    }
                });


        return root;
    }


  /*  public BroadcastReceiver mMessageReciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int totalBill = intent.getIntExtra("totalAmount", 0);

        }
    }; */



}