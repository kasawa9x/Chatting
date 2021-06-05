package com.nguyenhongson.chattingbys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.nguyenhongson.chattingbys.Model.Users;

import com.nguyenhongson.chattingbys.databinding.ActivitySettingBinding;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class SettingActivity extends AppCompatActivity {
    ActivitySettingBinding binding;
    FirebaseAuth auth;
    FirebaseStorage storage;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();

        binding.imBach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = binding.edtStatus.getText().toString();
                String username = binding.edtUserName.getText().toString();
                String mail = binding.edtMail.getText().toString();
                String pass = binding.edtPass.getText().toString();


                HashMap<String , Object> obj = new HashMap<>();
                obj.put("userName",username);
                obj.put("status",status);
                obj.put("mail",mail);
                obj.put("password",pass);

                database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                        .updateChildren(obj);
                Toast.makeText(SettingActivity.this,
                        "Lưu thành công",Toast.LENGTH_SHORT).show();
            }
        });

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
                        binding.edtPass.setText(users.getPassword());
                        binding.edtMail.setText(users.getMail());

                    }

                    @Override
                    public void onCancelled(@NonNull  DatabaseError error) {

                    }
                });
        binding.imPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,33);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data.getData() != null ){
            Uri sFile = data.getData();
            binding.imProfile.setImageURI(sFile);
            final StorageReference reference = storage.getReference()
                    .child("profile pictures")
                    .child(FirebaseAuth.getInstance().getUid());
            reference.putFile(sFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            database.getReference()
                                    .child("Users")
                                    .child(FirebaseAuth
                                    .getInstance().getUid())
                                    .child("profileepic")
                                    .setValue(uri.toString());
                         Toast.makeText(SettingActivity.this,
                                 "Cập nhật ảnh đại diện thành công",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });


        }
    }
}