package com.example.sportup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * this class represent the option to register as a user or trainer
 */
public class register_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__screen);
    }
    public void register_Trainer(View v){
        Intent intent = new Intent(this,register_Trainer.class);
        startActivity(intent);

    }
    public void register_User(View v){
        Intent intent = new Intent(this,register_User.class);
        startActivity(intent);

    }

}