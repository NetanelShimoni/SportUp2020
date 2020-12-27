package com.example.sportup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class edit_profile_user extends AppCompatActivity {
    TextView welcome;
    Intent intent;
    User user;
    EditText etweight, etpassword, etcity;
    Button button_update_user;
    Spinner change_wight;
    DatabaseReference userDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_user);
        this.intent = getIntent();
        user = (User) this.intent.getSerializableExtra("name");
        welcome = findViewById(R.id.textView8);
        change_wight = findViewById(R.id.spinner_change_weight);
        int pos = getIndex(change_wight,user.getWeight());
        change_wight.setSelection(pos);
        welcome.setText("Hello " + user.name + "\n Update details");
        this.button_update_user = findViewById(R.id.change_details);
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        userDbRef = mDatabase.getInstance().getReference("User");
       this.button_update_user.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                userDbRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                            isPasswordchanged();
                            isweightchanged();
                            iscitychanged();

                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                    private boolean isPasswordchanged() {
                        etpassword = findViewById(R.id.change_password);
                        userDbRef = mDatabase.getInstance().getReference("User");
                        if(etpassword.getText().toString().trim().length() != 0) {
                            String password=etpassword.getText().toString();
                            user.setPassword(password);
                            System.out.println("id is user:"+user.getId_system());
                            userDbRef.child(user.getId_system()).setValue(user);
                            return true;
                        } else
                            return false;
                    }

                    private boolean isweightchanged() {
                            String weight=change_wight.getSelectedItem().toString();
                        userDbRef.child(user.getId_system()).child("weight").setValue(weight);
//                            user.weight = weight;
                            user.setWeight(weight);
                            return true;
                    }


                    private boolean iscitychanged() {
                        etcity = findViewById(R.id.new_city);
                        if(etcity.getText().toString().trim().length() != 0) {
                            String city=etcity.getText().toString();
                            userDbRef.child(user.getId_system()).child("city").setValue(city);
                            user.setCity(city);
                            return true;
                        } else
                            return false;
                    }
                });
            }
        });


    }
    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }



}