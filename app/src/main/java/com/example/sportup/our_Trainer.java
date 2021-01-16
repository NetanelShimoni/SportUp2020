package com.example.sportup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * this class represents list of all trainers for the mangers To confirm them in the app
 */
public class our_Trainer extends AppCompatActivity {
    private List<Tranier> list;
    DatabaseReference trainerdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our__trainer);
        this.list= new ArrayList<>();



        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        trainerdb = mDatabase.getInstance().getReference("Trainer");
        trainerdb.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Object object = ds.getValue(Object.class);
                    String json = new Gson().toJson(object);
                    Tranier t= new Gson().fromJson(json, Tranier.class);
                    System.out.println("t: "+t.toString());
                        list.add(t);

                }
                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(our_Trainer.this));
                Adapter_trainer trainer_AD = new Adapter_trainer(list,our_Trainer.this);

                recyclerView.setAdapter(trainer_AD);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}