package com.example.sportup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * this class show the screen Represents the screen for contacting managers
 */
public class Contact_Us extends AppCompatActivity {
    private EditText user;
    private EditText phone;
    private Button send;
    DatabaseReference DbSupport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("hhiii!!!!");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__us);
        user = findViewById(R.id.user_name_contact);
        phone = findViewById(R.id.user_phone_contact);
        send = findViewById(R.id.button12);

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DbSupport = mDatabase.getInstance().getReference("Support");
        send.setEnabled(true);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sname = user.getText().toString();
                String sphone = phone.getText().toString();
                System.out.println("phone is:"+ sphone);
                String id = DbSupport.push().getKey();
                Support support = new Support(sname,id,sphone);
                DbSupport.child(id).setValue(support);
                Intent i = new Intent(Contact_Us.this,MainActivity.class);
                startActivity(i);

            }
        });


    }

}