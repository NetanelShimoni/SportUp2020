package com.example.sportup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ColorStateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.collection.LLRBNode;
import com.google.firebase.database.collection.LLRBNode.Color;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static com.google.firebase.database.collection.LLRBNode.Color.*;

/**
 * this class represnt a screent that show all the messages the send to a specific trainer from the User.
 */
public class view_Message extends AppCompatActivity {
    private List<Message> messageList;
    private  List<User> userList;
    private Tranier tranier;
    private ListView listView;
    private  ArrayList<String> text;
    private  ArrayList <String> arrayList;
    private Dialog dialog;
    private Button bt_return_home;
    private ImageView close;
    DatabaseReference Dbmessage;
    DatabaseReference DBUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__message);
        bt_return_home = findViewById(R.id.button_dialog);
        close = findViewById(R.id.close_image);
        dialog = new Dialog(this);
        Intent i = getIntent();
        this.tranier= (Tranier) i.getSerializableExtra("trainer");
        this.listView = (ListView) findViewById(R.id.list_view_message);
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        Dbmessage = mDatabase.getInstance().getReference("Message");


                Dbmessage.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        messageList = new ArrayList<Message>();
                        for (DataSnapshot ds : snapshot.getChildren()){
                                Object object = ds.getValue(Object.class);
                                String json = new Gson().toJson(object);
                                Message m= new Gson().fromJson(json, Message.class);
                                if(m.getId_trainer().equals(tranier.getId_system()) && m.isIs_read()==false) {
                                    messageList.add(m);
                                }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        DBUser = mDatabase.getInstance().getReference("User");
        DBUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userList = new ArrayList<User>();
               text = new ArrayList<>();
                for (DataSnapshot ds : snapshot.getChildren()){
                    Object object = ds.getValue(Object.class);
                    String json = new Gson().toJson(object);
                    User u= new Gson().fromJson(json, User.class);
                    for (int j = 0; j <messageList.size() ; j++) {
                        if(messageList.get(j).getId_user().equals(u.getId_system()) ){
                            userList.add(u);
                            text.add(messageList.get(j).getMessage());
                        }
                    }
                }
                arrayList = new ArrayList<>();
                System.out.println("size!!"+ userList.size());
                for (int j = 0; j <userList.size() ; j++) {
                    User u = userList.get(j);
                    System.out.println(u.getName()+"  "+u.getId_system());
                    arrayList.add("Name of user: "+u.getName()+ "\n"
                            +"Phone: "+u.getPhone()+ "\n"
                            +"Height: "+u.getHigh()+ "\n"+
                            "Weight: "+ u.getWeight()+"\n"+
                            "Message: "+text.get(j));
                }
//                ListView lst = new ListView(view_Message.this);
//                String[] arr = {"Item 1","Item 2"};
//                ArrayAdapter<String> ad = new ArrayAdapter<String>(view_Message.this,R.layout.text_view_for_send,arr);
//                lst.setAdapter(ad);
                ArrayAdapter arrayAdapter = new ArrayAdapter( view_Message.this,android.R.layout.simple_list_item_activated_1,arrayList);
                listView.setAdapter(arrayAdapter);
                if (listView.getCount()==0) {
                    dialog.setContentView(R.layout.layout_dialog);
                    bt_return_home =(Button) dialog.findViewById(R.id.button_dialog);
                    close =(ImageView) dialog.findViewById(R.id.close_image);
                    dialog.show();

                    close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    bt_return_home.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(view_Message.this,trainer_Home.class);
                            intent.putExtra("trainer",tranier);
                            startActivity(intent);
                        }
                    });



                    //openDialog();
                    //Toast.makeText(view_Message.this,"No Mesaage To Display :(",Toast.LENGTH_LONG).show();

                }
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        messageList.get(i).setIs_read(true);
                        Dbmessage.child(messageList.get(i).getId_system()).child("is_read").setValue(true);

                        arrayList.remove(i);

                        ArrayAdapter arrayAdapter = new ArrayAdapter( view_Message.this,android.R.layout.simple_list_item_activated_1,arrayList);
                        listView.setAdapter(arrayAdapter);

                    }
                });



            }

//            private void openDialog() {
//            Dialog_messgae dialog_messgae = new Dialog_messgae(view_Message.this,tranier);
//            dialog_messgae.show(getSupportFragmentManager(),"example");
//
//            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }





    }
