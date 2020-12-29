package com.example.sportup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class contact_with_us extends AppCompatActivity {
        private EditText user;
        private EditText phone;
        private Button send;
        private DatabaseReference DbSupport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_with_us);
        user = findViewById(R.id.user_name_contact);
        phone = findViewById(R.id.user_phone_contact);
        send = findViewById(R.id.send);
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DbSupport = mDatabase.getInstance().getReference("Support");

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sname = user.getText().toString();
                String sphone = phone.getText().toString();
                System.out.println("phone is:"+ sphone);
                Support support = new Support(sname,sphone);
                String id = DbSupport.push().getKey();
                DbSupport.child(id).setValue(support);
                Intent i = new Intent(contact_with_us.this,MainActivity.class);
                startActivity(i);

            }
        });


    }
}