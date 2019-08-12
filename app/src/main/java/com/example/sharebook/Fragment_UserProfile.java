package com.example.sharebook;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sharebook.Classes.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Fragment_UserProfile extends Fragment {
//    TODO
    private TextView user_name;
    private TextView contact;
    private TextView address;
    private String full_name;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_fragment__user_profile, null);

        user_name = view.findViewById(R.id.profile_user_name);
        contact = view.findViewById(R.id.profile_contact);
        address = view.findViewById(R.id.profile_address);

        query();


        return view;
    }

    public void query() {
        FirebaseAuth mAuth;
        String uid;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        uid = currentUser.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        final Query query = reference.orderByChild("uid").equalTo(uid);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                        User user = snapshot.getValue(User.class);
                        full_name = user.getFname().substring(0, 1).toUpperCase() + user.getFname().substring(1) + " " + user.getLname().substring(0, 1).toUpperCase() + user.getLname().substring(1) ;
                        user_name.setText(full_name);
                        contact.setText(user.getContact());
                        address.setText(user.getAddress().substring(0,1).toUpperCase() + user.getAddress().substring(1));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
