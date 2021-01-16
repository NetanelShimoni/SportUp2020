package com.example.sportup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * this class show the screen of the manger of the application
 */
public class Admin_Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__home);
    }

    public void Support(View v){
        Intent i = new Intent(Admin_Home.this,Support_Admin.class);
        startActivity(i);

    }
    public void our_Trainers(View v){
        Intent i = new Intent(Admin_Home.this,our_Trainer.class);
        startActivity(i);

    }
}