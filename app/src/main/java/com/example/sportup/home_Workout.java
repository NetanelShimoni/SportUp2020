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
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;

/**
 * this class represent the screen to the users with all the excersise that the trainers upload
 */
public class home_Workout extends AppCompatActivity {
DatabaseReference exeref;
   public List<Exersice> list;
    Intent intent = getIntent();
    Button back;
   // User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__workout);
        this.list= new ArrayList<>();



        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        exeref = mDatabase.getInstance().getReference("Exersice");
        exeref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = new User("","","","","","","");
                Intent in= getIntent();
                user = (User) in.getSerializableExtra("name");
                String user_hight= user.getHigh();
                String user_withe= user.getWeight();
                System.out.println("user name"+ user.name + "wihat"+user.weight+"hight"+user.high);
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Object object = ds.getValue(Object.class);
                    String json = new Gson().toJson(object);
                    Exersice e= new Gson().fromJson(json, Exersice.class);
                    String exe_hight= e.getHight();
                    String exe_withe= e.getWight();
                    System.out.println("e.hight: "+e.hight + "e.wight"+e.wight);
                    if(exe_hight.equals("ALL") && exe_withe.equals("ALL") ) { // FILTER!!
                        list.add(e);
                        System.out.println(e.link);
                    }if(user_withe.equals(exe_withe) && user_hight.equals(exe_hight)){
                        list.add(e);
                    }
                }
                //System.out.println("size in homw eorkout"+list.size());
                Intent intent = getIntent();
                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(home_Workout.this));

                MyAdepter_exe MyexeAdapter = new MyAdepter_exe(list,home_Workout.this,user);
                recyclerView.setAdapter(MyexeAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }




}
