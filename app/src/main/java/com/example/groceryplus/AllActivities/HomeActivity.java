package com.example.groceryplus.AllActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.groceryplus.MainActivity;
import com.example.groceryplus.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    ImageSlider imageSlider;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbarid);
        progressBar.setVisibility(View.GONE);

        if (firebaseAuth.getCurrentUser() != null) {
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
            Toast.makeText(getApplicationContext(), "Already Logged In", Toast.LENGTH_LONG).show();
            finish();
        }

        imageSlider = findViewById(R.id.image_slider);


        ArrayList<SlideModel> imageList = new ArrayList<>();


        imageList.add(new SlideModel(R.drawable.chaldal, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.cosmetics, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.fishimages, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.harpic, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.vegetable, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.fruits, ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(imageList);
    }

    public void Signinbtnclicked(View view) {
        Intent intent = new Intent(this, LoginAcitivty.class);
        startActivity(intent);
    }

    public void createnwaccpuntbtnclicked(View view) {
        Intent intent = new Intent(this, RegistrationAcitivity.class);
        startActivity(intent);
    }
}