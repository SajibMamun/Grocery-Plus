package com.example.groceryplus.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryplus.Adapters.HomeCatagoryAdapter;
import com.example.groceryplus.Adapters.PopularAdapter;
import com.example.groceryplus.Adapters.RecomndedAdapter;
import com.example.groceryplus.Models.HomeCatagoryModel;
import com.example.groceryplus.Models.PopularModel;
import com.example.groceryplus.Models.RecomndedModel;
import com.example.groceryplus.R;
import com.example.groceryplus.databinding.FragmentHomeBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView poprec,homecatRec,RecomRec;

    //popular item
    PopularAdapter popularAdapter;
    List<PopularModel> popularModelList;
    FirebaseFirestore db;

    //CatagoryItem
    List<HomeCatagoryModel> homeCatagoryModelList;
    HomeCatagoryAdapter homeCatagoryAdapter;


    //Recomanded Item
    List<RecomndedModel> recomndedModelList;
    RecomndedAdapter recomndedAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        poprec = root.findViewById(R.id.RecyclerViewPopularID);
        db = FirebaseFirestore.getInstance();

        homecatRec=root.findViewById(R.id.RecyclerViewExploreID);
        RecomRec=root.findViewById(R.id.RecyclerViewRecommendedID);



        poprec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        popularModelList = new ArrayList<>();
        popularAdapter = new PopularAdapter(getActivity(), popularModelList);
        poprec.setAdapter(popularAdapter);

//popular data load form firestore
        db.collection("PopularProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                PopularModel popularModel = document.toObject(PopularModel.class);
                                popularModelList.add(popularModel);
                                popularAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(getActivity(), "Error: " + task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });




        //Catagoryitem

        homecatRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        homeCatagoryModelList = new ArrayList<>();
        homeCatagoryAdapter = new HomeCatagoryAdapter(getActivity(),homeCatagoryModelList);
        homecatRec.setAdapter(homeCatagoryAdapter);


//Catagory data load form firestore
        db.collection("HomeCatagories")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                HomeCatagoryModel homeCatagoryModel = document.toObject(HomeCatagoryModel.class);
                                homeCatagoryModelList.add(homeCatagoryModel);
                                homeCatagoryAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(getActivity(), "Error: " + task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });


        RecomRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        recomndedModelList = new ArrayList<>();
        recomndedAdapter = new RecomndedAdapter(getActivity(), recomndedModelList);
        RecomRec.setAdapter(recomndedAdapter);

//Recomanded data load form firestore
        db.collection("RecomandedProduct")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                RecomndedModel recomndedModel = document.toObject(RecomndedModel.class);
                                recomndedModelList.add(recomndedModel);
                                recomndedAdapter.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(getActivity(), "Error: " + task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });





        return root;
    }


}