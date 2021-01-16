package com.example.sportup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
/**
 * see all the people that send mesaage to contact and And a mark that we took care of them
 */
public class Support_message extends AppCompatActivity {
    private TextView tname,tphone;
    private CheckBox box;
    private boolean is_care;
    private Support support;
    private Button update;
    DatabaseReference dbSupport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_message);
        tname= findViewById(R.id.name_support);
        update= findViewById(R.id.button11);
        tphone= findViewById(R.id.phone_support);
        box= findViewById(R.id.checkBox_supoort);
        Intent i = getIntent();
        support= (Support) i.getSerializableExtra("Support");
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        dbSupport = mDatabase.getInstance().getReference("Support");
        tname.setText("Name: "+support.getName());
        tphone.setText("Phone: "+support.getPhone());

        if(box.isClickable()){
            is_care=true;
        }
    update.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            System.out.println("boolean "+is_care );
            if (is_care == true) {
                dbSupport.child(support.getSystem_id()).removeValue();
                Intent i = new Intent(Support_message.this,Support_Admin.class);
                startActivity(i);
            }
        }
    });
    }


}