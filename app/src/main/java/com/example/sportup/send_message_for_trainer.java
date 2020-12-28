package com.example.sportup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class send_message_for_trainer extends AppCompatActivity {
    private TextView details_trainer ;
    private TextView  detailes_message;
    private Tranier chooch;
    private User user_to_want;
    private Button button_send_message;
    private DatabaseReference mesaageDbref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message_for_trainer);
        details_trainer= findViewById(R.id.detailes_trainer);
        detailes_message= findViewById(R.id.send_detailes_user);
        button_send_message= findViewById(R.id.send_to_spicipic_trainer);
        Intent i = getIntent();
        this.chooch = (Tranier)i.getSerializableExtra("trainer");
        this.user_to_want = (User) i.getSerializableExtra("user");
        details_trainer.setText("Your chosen Trainer: "+chooch.getName());
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        mesaageDbref = mDatabase.getInstance().getReference("Message");
        button_send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = mesaageDbref.push().getKey();
                Message m = new Message(detailes_message.getText().toString(),chooch.getId_system(),user_to_want.getId_system(),id,false);
                mesaageDbref.child(id).setValue(m);
                Toast.makeText(send_message_for_trainer.this, "Message send !", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(send_message_for_trainer.this , user_Home.class);
                i.putExtra("name",user_to_want);
                startActivity(i);
            }
        });




//        Message m = new Message(detailes_message.getText().toString(),user_to_want,false);
//        this.chooch.messages.add(m);

    }
}