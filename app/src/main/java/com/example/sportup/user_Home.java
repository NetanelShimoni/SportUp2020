package com.example.sportup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class user_Home extends AppCompatActivity {
TextView welcome;
    Intent intent ;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__home);
        this.intent=getIntent();
        user= (User) this.intent.getSerializableExtra("name");;
        welcome= findViewById(R.id.welcome_user);
        welcome.setText("Welcome "+user.name+"\n Chose option please");
    }
    public void mainActivity(View v){

        Intent i = new Intent(user_Home.this, MainActivity.class);
        startActivity(i);
    }
    public void edit_profile_user(View v){

        Intent i = new Intent(user_Home.this, edit_profile_user.class);
        i.putExtra("name",user);
        startActivity(i);
    }
    public void home_workout(View v){

        Intent i = new Intent(user_Home.this, home_Workout.class);
        i.putExtra("name",user);
        startActivity(i);
    }
    public void find_trainer(View v){

        Intent i = new Intent(user_Home.this,Find_trainer.class);
        i.putExtra("name",user);
        startActivity(i);
    }
}