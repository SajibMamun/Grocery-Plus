package com.example.groceryplus.ui.catagories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryplus.Adapters.NavCatagoryAdapter;
import com.example.groceryplus.Adapters.PopularAdapter;
import com.example.groceryplus.Models.NavCatagoryModel;
import com.example.groceryplus.Models.PopularModel;
import com.example.groceryplus.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CatagoryFragment extends Fragment {

    RecyclerView recyclerView;
    List<NavCatagoryModel> navCatagoryModelList;
    NavCatagoryAdapter navCatagoryAdapter;
    FirebaseFirestore db;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fargment_catagory, container, false);
        recyclerView = root.findViewById(R.id.nav_cat_recyclerviewid);
        navCatagoryModelList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();



        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        navCatagoryAdapter = new NavCatagoryAdapter(getActivity(), navCatagoryModelList);
        recyclerView.setAdapter(navCatagoryAdapter);

//nav Cat data load form firestore
        db.collection("NavCatagory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                NavCatagoryModel navCatagoryModel = document.toObject(NavCatagoryModel.class);
                                navCatagoryModelList.add(navCatagoryModel);
                                navCatagoryAdapter.notifyDataSetChanged();



                            }
                        } else {
                            Toast.makeText(getActivity(), "Error: " + task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });


        return root;
    }

}