package com.example.sportup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class trainer_add_Exersice extends AppCompatActivity {
    Spinner spinner_muselse,spinner_hight,spinner_wight;
    EditText e_name , e_dis,e_link;
    DatabaseReference exerciseDbRef;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_add__exersice);
        spinner_muselse = findViewById(R.id.spinner_trainer_muselse);
        Intent i = getIntent();
        spinner_muselse.setSelection(i.getExtras().getInt("id_m"));
            e_name=findViewById(R.id.edit_name);
            e_dis=findViewById(R.id.editText_discrpition);
            e_link=findViewById(R.id.edit_link);
            spinner_hight=findViewById(R.id.spinner_how_hight);
            spinner_wight=findViewById(R.id.spinner_how_weight);
            add = findViewById(R.id.button_add_exe);
        FirebaseDatabase mData = FirebaseDatabase.getInstance();
        exerciseDbRef = mData.getInstance().getReference("Exersice");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = e_name.getText().toString();
                String discription = e_dis.getText().toString();
                String link = e_link.getText().toString();
                String  hight = spinner_hight.getSelectedItem().toString();
                String  witht = spinner_wight.getSelectedItem().toString();
                String name_maselce = spinner_muselse.getSelectedItem().toString();
                Intent intent = getIntent();
                Tranier mover = (Tranier)intent.getSerializableExtra("trainer");
                String id = exerciseDbRef.push().getKey();

                String name_trainer=mover.getName();
                String phone_trainer=mover.getPhone_num();
                Exersice e = new Exersice(name_maselce,name,name_trainer,phone_trainer,discription,witht,hight,link);

                        exerciseDbRef.child(id).setValue(e);
                        Toast.makeText(trainer_add_Exersice.this, "Data inserted!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(trainer_add_Exersice.this, trainer_Home.class);
                        i.putExtra("trainer",mover);
                        startActivity(i);

            }});



    }
}