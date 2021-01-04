package com.example.sportup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class ditaiels_trainer extends AppCompatActivity {
    private Tranier tranier;
    private TextView p_name,p_phone,p_city;
    private CheckBox accept;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;
    ProgressDialog  pd ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ditaiels_trainer);
        Button button=findViewById(R.id.download);
        Button up_date=findViewById(R.id.up_date);
        pd =new ProgressDialog(this);
        p_name = findViewById(R.id.d_name);
        p_phone = findViewById(R.id.d_phone);
        p_city = findViewById(R.id.d_city);
        accept= findViewById(R.id.checkBox_accept);
        Intent i = getIntent();
        tranier= (Tranier) i.getSerializableExtra("trainer");
        p_name.setText(tranier.getName());
        p_phone.setText(tranier.getPhone_num());
        p_city.setText(tranier.getCity());

        up_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(accept.isClickable()){
                    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference DBTrainer = mDatabase.getInstance().getReference("Trainer");
                    DBTrainer.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            tranier.setIs_verify(true);
                            DBTrainer.child(tranier.getId_system()).setValue(tranier);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.setTitle(tranier.getName()+".pdf");
                pd.setMessage("Downloading Please Wait!");
                //pd.setIndeterminate(true);
                pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pd.show();
                downloadFile();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        pd.dismiss();
                        Toast.makeText(ditaiels_trainer.this, "Download " +tranier.getName()+".pdf",
                                Toast.LENGTH_LONG).show();                    }
                }, 3000);
               // pd.dismiss();
            }
        });
    }

    private void downloadFile() {


        storageReference=firebaseStorage.getInstance().getReference();
         ref =storageReference.child(tranier.getName());
         ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
             @Override
             public void onSuccess(Uri uri) {
                 String url = uri.toString();
                 //downloadfile1();

               downloadPdf(ditaiels_trainer.this,tranier.getName(),".pdf",DIRECTORY_DOWNLOADS,url);
               //pd.dismiss();

             }


         }).addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {

             }
         });


//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference storageRef = storage.getReferenceFromUrl(tranier.getUri_certificate());
//       StorageReference  islandRef = storageRef.child("file.txt");

/*
        File rootPath = new File(Environment.getExternalStorageDirectory(), "file_name");
        if(!rootPath.exists()) {
            rootPath.mkdirs();
        }

        final File localFile = new File(rootPath,"imageName.pdf");

        islandRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                Log.e("firebase ",";local tem file created  created " +localFile.toString());
                //  updateDb(timestamp,localFile.toString(),position);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.e("firebase ",";local tem file not created  created " +exception.toString());
            }
        });*/
    }
    public long downloadPdf (Context context, String fileName, String fileExtension, String destinationDirectory, String url) {


        DownloadManager downloadmanager = (DownloadManager) context.
                getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);
           // pd.dismiss();
        return downloadmanager.enqueue(request);
    }





//    private void downloadfile1() {
//
//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference storageRef = storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/sportup2020-c354d.appspot.com/o/Daniel%20Twito?alt=media&token=154084e1-db60-476a-84ef-10a59b1f30cb");
//
//        ProgressDialog  pd = new ProgressDialog(this);
//        pd.setTitle("Nature.jpg");
//        pd.setMessage("Downloading Please Wait!");
//        pd.setIndeterminate(true);
//        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        pd.show();
//
//
//        final File rootPath = new File(Environment.getExternalStorageDirectory(), "MADBO DOWNLOADS");
//
//        if (!rootPath.exists()) {
//            rootPath.mkdirs();
//        }
//
//
//        final File localFile = new File(rootPath, "Nature.jpg");
//
//        storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener <FileDownloadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                Log.e("firebase ", ";local tem file created  created " + localFile.toString());
////
////                if (!isVisible()){
////                    return;
////                }
//
//                if (localFile.canRead()){
//
//                    pd.dismiss();
//                }
//
//                Toast.makeText(ditaiels_trainer.this, "Download Completed", Toast.LENGTH_SHORT).show();
//                Toast.makeText(ditaiels_trainer.this, "Internal storage/MADBO/Nature.jpg", Toast.LENGTH_LONG).show();
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                Log.e("firebase ", ";local tem file not created  created " + exception.toString());
//                Toast.makeText(ditaiels_trainer.this, "Download Incompleted", Toast.LENGTH_LONG).show();
//            }
//        });
//    }



    }
