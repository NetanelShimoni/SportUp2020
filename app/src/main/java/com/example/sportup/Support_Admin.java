package com.example.sportup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Support_Admin extends AppCompatActivity {
    private List<String> all_Support;
    private ListView listView;
    private Dialog dialog;
    private Button home;
    private ImageView close;
    private List<Support> supportList;
    DatabaseReference supportDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support__admin);
        FirebaseDatabase mData = FirebaseDatabase.getInstance();
        dialog = new Dialog(this);
        this.listView = (ListView) findViewById(R.id.list_view_support);
        supportDbRef = mData.getInstance().getReference("Support");
        supportDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                supportList = new ArrayList<Support>();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Object object = ds.getValue(Object.class);
                    String json = new Gson().toJson(object);
                    Support s = new Gson().fromJson(json, Support.class);
                    if (!s.isIs_takeCare()) {
                        supportList.add(s);
                    }
                }
                all_Support = new ArrayList<String>();
                for (int i = 0; i < supportList.size(); i++) {
                    all_Support.add("Message " + (i + 1));
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(Support_Admin.this, android.R.layout.simple_list_item_activated_1, all_Support);
                listView.setAdapter(arrayAdapter);

                if (listView.getCount() == 0) {
                    dialog.setContentView(R.layout.layout_dialog);
                    home = (Button) dialog.findViewById(R.id.button_dialog);
                    close = (ImageView) dialog.findViewById(R.id.close_image);
                    dialog.show();

                    close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    home.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Support_Admin.this, Admin_Home.class);
                            startActivity(intent);
                        }
                    });

                }
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent j = new Intent(Support_Admin.this,Support_message.class);
                            j.putExtra("Support",supportList.get(i));
                            startActivity(j);
                        // Dbmessage.child(messageList.get(i).getId_system()).child("is_read").setValue(true);
                        //all_Support.remove(i);

                        ArrayAdapter arrayAdapter = new ArrayAdapter(Support_Admin.this, android.R.layout.simple_list_item_activated_1, all_Support);
                        listView.setAdapter(arrayAdapter);
                    }
                });
            }


        @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }});
            }




            //openDialog();
            //Toast.makeText(view_Message.this,"No Mesaage To Display :(",Toast.LENGTH_LONG).show();

        }





