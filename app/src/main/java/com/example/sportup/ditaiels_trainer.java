package com.example.sportup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class ditaiels_trainer extends AppCompatActivity {
    private Tranier tranier;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ditaiels_trainer);
        Button button=findViewById(R.id.download);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadFile();
            }
        });
    }

    private void downloadFile() {
        Intent i = getIntent();
        tranier= (Tranier) i.getSerializableExtra("trainer");
        storageReference=firebaseStorage.getInstance().getReference();
        System.out.println("url iss:::"+tranier.getUri_certificate());
        System.out.println("name issss::"+tranier.getName());
         ref =storageReference.child(tranier.getName());
         ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
             @Override
             public void onSuccess(Uri uri) {
                 String url = uri.toString();
                 //downloadfile1();
               downloadPdf(ditaiels_trainer.this,tranier.getName(),".pdf",DIRECTORY_DOWNLOADS,url);

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

        ProgressDialog  pd = new ProgressDialog(this); 
        pd.setTitle(tranier.getName()+"-CV.pdf");
        pd.setMessage("Downloading Please Wait!");
        pd.setIndeterminate(true);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.show();
        DownloadManager downloadmanager = (DownloadManager) context.
                getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);
            pd.dismiss();
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
