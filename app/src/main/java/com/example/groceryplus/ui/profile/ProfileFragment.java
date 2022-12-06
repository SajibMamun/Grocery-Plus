package com.example.groceryplus.ui.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.groceryplus.Models.ProfileModel;
import com.example.groceryplus.Models.UserDataModel;
import com.example.groceryplus.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {

    CircleImageView profileImg;
    EditText nameet, emailet, numberet, addresset;
    Button update;
    FirebaseAuth firebaseAuth;
    FirebaseStorage storage;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
    String name,email,number,address;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        profileImg = root.findViewById(R.id.profile_imgID);
        nameet = root.findViewById(R.id.profile_name_et_ID);
        emailet = root.findViewById(R.id.profile_email_et_ID);
        numberet = root.findViewById(R.id.profile_contact_et_ID);
        addresset = root.findViewById(R.id.profile_location_et_ID);
        update=root.findViewById(R.id.updateprofilebtnid);










        firebaseDatabase.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                UserDataModel userDataModel=snapshot.getValue(UserDataModel.class);
                                Glide.with(getContext()).load(userDataModel.getProfileImg()).into(profileImg);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });



        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 33);

            }
        });




        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUSerProfile();
            }
        });
        return root;
    }

    private void updateUSerProfile() {
        String name=nameet.getText().toString().trim();
        String email=emailet.getText().toString().trim();
        String number=numberet.getText().toString().trim();
        String address=addresset.getText().toString().trim();



        firebaseDatabase=FirebaseDatabase.getInstance();
        ProfileModel obj=new ProfileModel(name,email,address,number);
        DatabaseReference DRF=firebaseDatabase.getReference("Users");
        DRF.child(firebaseAuth.getUid()).setValue(obj);

        Toast.makeText(getContext(),"Updated",Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data.getData() != null) {
            Uri profileuri = data.getData();
            profileImg.setImageURI(profileuri);

            final StorageReference reference = storage.getReference().child("profile_picture")
                    .child(FirebaseAuth.getInstance().getUid());


            reference.putFile(profileuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();

                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            firebaseDatabase.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                                    .child("profileImg").setValue(uri.toString());
                            Toast.makeText(getContext(), "Profile Picture Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });


        }
    }
}



