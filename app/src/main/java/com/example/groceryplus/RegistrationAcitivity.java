package com.example.groceryplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegistrationAcitivity extends AppCompatActivity {
    EditText nameet,emailet,passet,confirmpasset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_acitivity);
    }

    public void createaccountbtnclicked(View view) {
    }

    public void signinbtnclicked(View view) {
        Intent intent=new Intent(this,LoginAcitivty.class);
        startActivity(intent);
    }
}