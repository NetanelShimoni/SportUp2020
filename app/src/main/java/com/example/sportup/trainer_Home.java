package com.example.sportup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class trainer_Home extends AppCompatActivity {
    String s1[],s2[];
    int image []= {R.drawable.chest,R.drawable.six_pack,R.drawable.sulders,R.drawable.triceps1,R.drawable.kidmit};
    RecyclerView recyclerView;
    TextView welcome;
    Tranier mover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer__home);
        Intent intent = getIntent();
        welcome = findViewById(R.id.textView7);
        this.mover = (Tranier)intent.getSerializableExtra("trainer");
        welcome.setText("Welcome "+mover.getName()+"\nPLEASE ADD EXERCISE");

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyMuscles[] MyMuscles = new MyMuscles[]{
                new MyMuscles("Six-pack",R.drawable.six_pack),
                new MyMuscles("Chest",R.drawable.chest),
                new MyMuscles("Sulders",R.drawable.sulders),
                new MyMuscles("Legs",R.drawable.legs),
                new MyMuscles("Triceps",R.drawable.triceps1),
                new MyMuscles("Biceps",R.drawable.kidmit),
        };

        MyAdapter myMusclesAdapter = new MyAdapter(MyMuscles,trainer_Home.this,mover);
        recyclerView.setAdapter(myMusclesAdapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menue){
        getMenuInflater().inflate(R.menu.menu_trainer,menue);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.profile) {
            // setContentView(R.layout.activity_sentences);
            Intent i = new Intent(this,Edit_profile_trainer.class);
            i.putExtra("trainer",this.mover);
            startActivity(i);
        }else if(item.getItemId()==R.id.view){
            //setContentView(R.layout.activity_contact_with_us);
            Intent i = new Intent(this,view_Message.class);
            i.putExtra("trainer",this.mover);
            startActivity(i);

        }else if(item.getItemId()==R.id.logout) {
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        }else{
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

}