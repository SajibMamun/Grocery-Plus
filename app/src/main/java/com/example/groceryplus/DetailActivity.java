package com.example.groceryplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.groceryplus.Models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailActivity extends AppCompatActivity {
    ImageView detailImg, addItem, removeItem;
    TextView price, rating, description, quantity;
    Toolbar mtoolbar;
    int totalQuantity = 1;
    Button addtocartbtn;
    int totalprice = 0;
    FirebaseFirestore firestore;
    FirebaseAuth auth;


    ViewAllModel viewAllModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mtoolbar = findViewById(R.id.toolbarid);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();




        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof ViewAllModel) {
            viewAllModel = (ViewAllModel) object;

        }

        description = findViewById(R.id.detail_descripid);
        price = findViewById(R.id.detail_priceid);
        rating = findViewById(R.id.detailed_ratingid);
        detailImg = findViewById(R.id.detailed_img);
        addItem = findViewById(R.id.add_productbtnid);
        removeItem = findViewById(R.id.remove_productbtnid);
        quantity = findViewById(R.id.quantitytvid);
        addtocartbtn = findViewById(R.id.add_to_cartbtn);


        if (viewAllModel != null) {
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(detailImg);
            price.setText("Price: " + viewAllModel.getPrice() + "৳");

            totalprice = viewAllModel.getPrice() * totalQuantity;
            rating.setText(viewAllModel.getRating());
            description.setText(viewAllModel.getDescription());
        }


        addtocartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addtocart();
            }
        });


        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity < 10) {
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalprice = viewAllModel.getPrice() * totalQuantity;
                }

            }
        });
        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity > 1) {
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalprice = viewAllModel.getPrice() * totalQuantity;
                }
            }
        });


    }

    private void addtocart() {
        String saveCurrentTime, saveCurrentDate;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String ,Object> carMap=new HashMap<>();
        carMap.put("productName",viewAllModel.getName());
        carMap.put("productPrice",price.getText().toString());
        carMap.put("currentDate",saveCurrentDate);
        carMap.put("currentTime",saveCurrentTime);
        carMap.put("totalQuantity",quantity.getText().toString());
        carMap.put("totalprice",totalprice);
        carMap.put("productImage",viewAllModel.getImg_url());
        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("CurrentUser").add(carMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(getApplicationContext(),"Added to cart",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

    }
}



