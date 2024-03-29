package com.example.groceryplus.AllActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.groceryplus.Models.UserDataModel;
import com.example.groceryplus.R;
import com.example.groceryplus.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.maintoolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_profile, R.id.nav_catagory,
                R.id.nav_myorders,R.id.nav_cart,R.id.nav_offers,R.id.nav_globalproduct)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


   View headerview=navigationView.getHeaderView(0);
        TextView Headername=headerview.findViewById(R.id.nav_header_name);
        TextView Headeremail=headerview.findViewById(R.id.nav_header_email);
        firebaseDatabase=FirebaseDatabase.getInstance();
        CircleImageView HeaderImg=headerview.findViewById(R.id.nav_header_img);


        firebaseDatabase.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserDataModel userDataModel=snapshot.getValue(UserDataModel.class);
                        Headername.setText(userDataModel.getName());
                        Headeremail.setText(userDataModel.getEmail());
                      Glide.with(MainActivity.this).load(userDataModel.getProfileImg()).into(HeaderImg);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


    }




    //Menu Option
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.accountlogout:

               Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
               FirebaseAuth.getInstance().signOut();
               startActivity(intent);
               finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }





    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}