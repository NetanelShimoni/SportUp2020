package com.example.sportup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class user_Login extends AppCompatActivity {
    EditText etname, etpassword;
    Button bJoin;
    DatabaseReference userDbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__login);
        etname= findViewById(R.id.user_name_login);
        etpassword= findViewById(R.id.user_password_login);
        bJoin = findViewById(R.id.user_button_login);
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        userDbRef = mDatabase.getInstance().getReference("User");

        bJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view==bJoin){
                userDbRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String name = etname.getText().toString();
                        String password = etpassword.getText().toString();
                        boolean flag = false;
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            if (ds.child("name").getValue().equals(name) && ds.child("password").getValue().equals(password)) {
                                Object object = ds.getValue(Object.class);
                                String json = new Gson().toJson(object);
                                User user = new Gson().fromJson(json, User.class);
                                System.out.println("id user in login is: " + user.getId_system());

                                flag = true;
                                Toast.makeText(user_Login.this, "OK!!!", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(user_Login.this, user_Home.class);
                                i.putExtra("name", user);
                                startActivity(i);
                            }
                        }
                        if (!flag) {
                            Toast.makeText(user_Login.this, "Sorry , try again :(", Toast.LENGTH_LONG).show();

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }
        });
    }

}