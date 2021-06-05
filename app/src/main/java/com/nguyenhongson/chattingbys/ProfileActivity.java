package com.nguyenhongson.chattingbys;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenhongson.chattingbys.Model.Users;

import com.nguyenhongson.chattingbys.databinding.FragmentProfileBinding;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends Fragment {
    public ProfileActivity() {
        // Required empty public constructor
    }


    FragmentProfileBinding binding;

    FirebaseDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        database = FirebaseDatabase.getInstance();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull  DataSnapshot snapshot) {
                        Users users = snapshot.getValue(Users.class);
                        Picasso.get()
                                .load((users.getProfileepic()))
                                .placeholder(R.drawable.avatar)
                                .into(binding.imProfile);
                        binding.edtStatus.setText(users.getStatus());
                        binding.edtUserName.setText(users.getUserName());

                        binding.edtMail.setText(users.getMail());

                    }

                    @Override
                    public void onCancelled(@NonNull  DatabaseError error) {

                    }
                });

        return binding.getRoot();
    }
}
