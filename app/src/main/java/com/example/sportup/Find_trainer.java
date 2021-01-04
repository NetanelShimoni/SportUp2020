package com.example.sportup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Find_trainer extends AppCompatActivity {
    List<Tranier> list ;
    DatabaseReference trainerdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_trainer);
        this.list= new ArrayList<>();



        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        trainerdb = mDatabase.getInstance().getReference("Trainer");
        trainerdb.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = new User("","","","","","","");
                Intent in= getIntent();
                user = (User) in.getSerializableExtra("name");
                String location_user= user.getCity();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Object object = ds.getValue(Object.class);
                    String json = new Gson().toJson(object);
                    Tranier t= new Gson().fromJson(json, Tranier.class);
                    String location_trainer= t.getCity();
                    if(location_trainer.equals(location_user) && t.isIs_verify() ) { // FILTER!!
                        list.add(t);
                    }
                }
                //System.out.println("size in homw eorkout"+list.size());
                Intent intent = getIntent();
                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(Find_trainer.this));

                Adapter_trainer trainer_AD = new Adapter_trainer(list,Find_trainer.this,user);

                recyclerView.setAdapter(trainer_AD);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }





}