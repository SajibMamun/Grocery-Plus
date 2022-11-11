package com.example.groceryplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ImageSlider imageSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        imageSlider =findViewById(R.id.image_slider);

        ArrayList<SlideModel> imageList = new ArrayList<>();


        imageList.add(new SlideModel(R.drawable.chaldal, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.cosmetics,ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.fishimages,ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.harpic,ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.vegetable,ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.fruits,ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(imageList);
    }

    public void Signinbtnclicked(View view) {
        Intent intent=new Intent(this,LoginAcitivty.class);
        startActivity(intent);
    }

    public void createnwaccpuntbtnclicked(View view) {
        Intent intent=new Intent(this, RegistrationAcitivity.class);
        startActivity(intent);
    }
}