package com.example.groceryplus.AllActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.groceryplus.Models.UserDataModel;
import com.example.groceryplus.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationAcitivity extends AppCompatActivity {
    EditText nameet, emailet, passet, confirmpasset;
    FirebaseAuth auth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    FirebaseDatabase database;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_acitivity);
        nameet = findViewById(R.id.NameEtid);
        emailet = findViewById(R.id.EmailEtid);
        passet = findViewById(R.id.PasswordEtid);
        confirmpasset = findViewById(R.id.ConfirmPasswordEtid);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        progressBar=findViewById(R.id.progressbarid);
        progressBar.setVisibility(View.GONE);


    }


    public void createaccountbtnclicked(View view) {

        if (nameet.getText().toString().trim().isEmpty()) {
            nameet.setError("Enter Name");
        } else if (emailet.getText().toString().trim().isEmpty()) {
            emailet.setError("Enter Email");
        } else if (!emailet.getText().toString().trim().matches(emailPattern)) {
            emailet.setError("Email is not Valid");
        } else if (passet.getText().toString().trim().isEmpty()) {
            passet.setError("Enter password");
        } else if (passet.getText().toString().trim().length() < 8) {
            passet.setError("Enter at least 8 digit password");
        } else if (confirmpasset.getText().toString().trim().isEmpty()) {
            confirmpasset.setError("Enter password");
        } else if (confirmpasset.getText().toString().trim().length() < 8) {
            passet.setError("Enter at least 8 digit password");

        } else if (!passet.getText().toString().trim().equals(confirmpasset.getText().toString().trim())) {
            confirmpasset.setError("Password Doesn't Match");
        } else {
            CreateUserAccount();
            progressBar.setVisibility(View.VISIBLE);
        }
    }


    //user account creation part
    private void CreateUserAccount() {
        String userName = nameet.getText().toString().trim();
        String userEmail = emailet.getText().toString().trim();
        String userPassword = passet.getText().toString().trim();
        auth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            //Firebase database part
                            UserDataModel userDataModel = new UserDataModel(userName, userEmail, userPassword);
                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(userDataModel);
                            //////////
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_LONG).show();
                        } else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Unsuccess Regristration: " + task.getException(), Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }


    public void signinbtnclicked(View view) {
        Intent intent = new Intent(this, LoginAcitivty.class);
        startActivity(intent);
    }
}