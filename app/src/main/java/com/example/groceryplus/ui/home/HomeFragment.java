package com.example.groceryplus.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groceryplus.Adapters.HomeCatagoryAdapter;
import com.example.groceryplus.Adapters.PopularAdapter;
import com.example.groceryplus.Adapters.RecomndedAdapter;
import com.example.groceryplus.Adapters.ViewAllAdapter;
import com.example.groceryplus.AllActivities.HomeActivity;
import com.example.groceryplus.Models.HomeCatagoryModel;
import com.example.groceryplus.Models.PopularModel;
import com.example.groceryplus.Models.RecomndedModel;
import com.example.groceryplus.Models.ViewAllModel;
import com.example.groceryplus.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView poprec,homecatRec,RecomRec;
    ProgressBar progressBar;
    ScrollView scrollView;

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

    //Search Item
    EditText search_box;
    private  List<ViewAllModel> viewAllModelList;
    private  RecyclerView SearchrecyclerView;
    private ViewAllAdapter viewAllAdapter;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        poprec = root.findViewById(R.id.RecyclerViewPopularID);
        db = FirebaseFirestore.getInstance();

        homecatRec=root.findViewById(R.id.RecyclerViewExploreID);
        RecomRec=root.findViewById(R.id.RecyclerViewRecommendedID);

        progressBar=root.findViewById(R.id.progressbarid);
        scrollView=root.findViewById(R.id.scrollviewid);

        setHasOptionsMenu(true);

        //// visibility
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);


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

                                progressBar.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);

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








        ///////SearchView
        search_box=root.findViewById(R.id.search_box);
        SearchrecyclerView=root.findViewById(R.id.search_recid);
        viewAllModelList=new ArrayList<>();
        viewAllAdapter=new ViewAllAdapter(getContext(),viewAllModelList);
        SearchrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SearchrecyclerView.setAdapter(viewAllAdapter);
        SearchrecyclerView.setHasFixedSize(true);
        search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().isEmpty())
                {
                    viewAllModelList.clear();
                    viewAllAdapter.notifyDataSetChanged();
                }
                else
                {
                    searchProduct(s.toString());
                }

            }
        });






        return root;
    }

    private void searchProduct(String type) {
        if(!type.isEmpty())
        {
            db.collection("AllProducts").whereEqualTo("type",type).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if(task.isSuccessful()&& task.getResult()!=null)

                            {
                                viewAllModelList.clear();
                                viewAllAdapter.notifyDataSetChanged();
                                for(DocumentSnapshot documentSnapshot: task.getResult().getDocuments())
                                {
                                    ViewAllModel viewAllModel=documentSnapshot.toObject(ViewAllModel.class);
                                    viewAllModelList.add(viewAllModel);
                                    viewAllAdapter.notifyDataSetChanged();
                                }


                            }
                        }
                    });
        }
    }




}