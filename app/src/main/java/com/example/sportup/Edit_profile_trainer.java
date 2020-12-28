package com.example.sportup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
///ffoooorrkkkkkkkkkkkkkk
public class Edit_profile_trainer extends AppCompatActivity {
    TextView welcome;
    Intent intent;
    Tranier tranier;
    EditText etphone, etpassword, etcity;
    Button button_update_trainer;
    DatabaseReference trianerDbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_trainer);
        this.intent = getIntent();
        this.tranier = (Tranier) this.intent.getSerializableExtra("trainer");
        welcome = findViewById(R.id.detailes_trainer_change);
        welcome.setText("Hello " + tranier.getName() + "\n Update details");
        this.button_update_trainer = findViewById(R.id.change_details_trainer);
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        trianerDbRef = mDatabase.getInstance().getReference("Trainer");
        this.button_update_trainer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                trianerDbRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        isPasswordchanged();
                        isPhonechanged();
                        iscitychanged();

                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                    private boolean isPasswordchanged() {
                        etpassword = findViewById(R.id.new_password_trainer);
                        if(etpassword.getText().toString().trim().length() != 0) {
                            String password=etpassword.getText().toString();
                            tranier.setPassword(password);
                            trianerDbRef.child(tranier.getId_system()).child("password").setValue(password);
                            tranier.setPassword(password);
                            return true;
                        } else
                            return false;
                    }

                    private boolean isPhonechanged() {
                        etphone = findViewById(R.id.new_phone_trainer);
                        if(etphone.getText().toString().trim().length() != 0) {
                            String phone = etphone.getText().toString();
                            trianerDbRef.child(tranier.getId_system()).child("phone_num").setValue(phone);
                            tranier.setPhone_num(phone);
                            return true;
                        }
                        return false;
                    }


                    private boolean iscitychanged() {
                        etcity = findViewById(R.id.new_city_trainer);
                        if(etcity.getText().toString().trim().length() != 0) {
                            String city=etcity.getText().toString();
                            tranier.setCity(city);
                            trianerDbRef.child(tranier.getId_system()).child("city").setValue(city);
                            tranier.setCity(city);
                            return true;
                        } else
                            return false;
                    }
                });
            }
        });


    }




}




