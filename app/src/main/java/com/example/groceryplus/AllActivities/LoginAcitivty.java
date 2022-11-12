package com.example.groceryplus.AllActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.groceryplus.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginAcitivty extends AppCompatActivity {
    EditText emailet,passet;
    FirebaseAuth auth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitivty);

        emailet=findViewById(R.id.LoginEmailEtid);
        passet=findViewById(R.id.LoginPasswordEtid);
        auth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressbarid);
        progressBar.setVisibility(View.GONE);
    }

    public void Signinbtnclickedfunction(View view) {
        userlogin();
        progressBar.setVisibility(View.VISIBLE);
    }








    public void SignUptvfunctionclicked(View view) {
        Intent intent=new Intent(LoginAcitivty.this, RegistrationAcitivity.class);
        startActivity(intent);

    }




















    private void userlogin() {

      if (emailet.getText().toString().trim().isEmpty()) {
            emailet.setError("Enter Email");
        } else if (!emailet.getText().toString().trim().matches(emailPattern)) {
            emailet.setError("Email is not Valid");
        } else if (passet.getText().toString().trim().isEmpty()) {
            passet.setError("Enter password");
        } else if (passet.getText().toString().trim().length() < 8) {
            passet.setError("Enter at least 8 digit password");
        }
      else {
          //Loginuser

          String email=emailet.getText().toString().trim();
          String password=passet.getText().toString().trim();

          auth.signInWithEmailAndPassword(email,password)
                  .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                      @Override
                      public void onComplete(@NonNull Task<AuthResult> task) {

                          if(task.isSuccessful())
                          {
                              progressBar.setVisibility(View.GONE);
                              Toast.makeText(getApplicationContext(),"Login Done",Toast.LENGTH_LONG).show();

                          }
                          else
                          {
                              progressBar.setVisibility(View.GONE);
                              Toast.makeText(getApplicationContext(),"Error: "+task.getException(),Toast.LENGTH_LONG).show();

                          }
                      }
                  });

      }
    }
}