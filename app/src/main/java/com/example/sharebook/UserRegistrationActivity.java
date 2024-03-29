package com.example.sharebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sharebook.Classes.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRegistrationActivity extends AppCompatActivity {
    EditText fname,lname,address,contact;
    Button register;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        fname = findViewById(R.id.user_firstName);
        lname = findViewById(R.id.user_lastName);
        address = findViewById(R.id.user_address);
        contact = findViewById(R.id.user_contact);
        register = findViewById(R.id.register);
        user = new User();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserDetails();
            }
        });
    }
    public void saveUserDetails() {
        FirebaseAuth mAuth;
        String uid;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        uid = currentUser.getUid();
        if (uid != null) {
            DatabaseReference reference;
            reference = FirebaseDatabase.getInstance().getReference("users");
            user.setFname(fname.getText().toString().toLowerCase());
            user.setLname(lname.getText().toString().toLowerCase());
            user.setAddress(address.getText().toString().toLowerCase());
            user.setContact(contact.getText().toString().toLowerCase());
            user.setUid(currentUser.getUid());
            user.setImg_url("No Image Url");
            reference.child(uid).setValue(user);

            Intent intent = new Intent(UserRegistrationActivity.this,HomeActivity.class);
            startActivity(intent);
            Log.d("UserReg", "saveUserDetails: At last");;
        }

    }
}
