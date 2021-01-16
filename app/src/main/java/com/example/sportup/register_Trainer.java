package com.example.sportup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import de.hdodenhof.circleimageview.CircleImageView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.ValueCallback;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * registration for trainer
 */
public class register_Trainer extends AppCompatActivity {

    private static final int GALARY =200 ;
    EditText etName,etPhone,etCity,etPassword,etid;
    private Button btnJoin,uplod,CV,submit;
    private Button profile_image;
    StorageReference storageManager;
    StorageReference profile;
    CheckBox box;
    int SELECT_PHOTO =1;
    Uri uri ;
    ImageView imageView;
    DatabaseReference trainerDbRef;
    DatabaseReference PDFDbRef;
    private CircleImageView ProfileImage;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    String uri_String;
    boolean is_exit= false;
    private EditText upload_pdf;
    FirebaseAuth firebaseAuto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__trainer);
        firebaseAuto = firebaseAuto.getInstance();

        upload_pdf =  findViewById(R.id.file_name1);
        etName = findViewById(R.id.editTextTextPersonName);
        etPassword = findViewById(R.id.password_trainer);
        profile_image = findViewById(R.id.take_selfi);
        submit = findViewById(R.id.submit);
        etCity = findViewById(R.id.city);
        CV = findViewById(R.id.upload_CV);
        etPhone = findViewById(R.id.phone_tranier);
        btnJoin = (Button) findViewById(R.id.button7);
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        trainerDbRef = mDatabase.getInstance().getReference("Trainer");

        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, 1);
                }
            }
        });
    submit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    });
    
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String password = etPassword.getText().toString();
                String phone = etPhone.getText().toString();
                String city = etCity.getText().toString();
                String id = trainerDbRef.push().getKey();

                    Tranier t = new Tranier(name, id, city, phone, password);

                        trainerDbRef.child(id).setValue(t);
                    Toast.makeText(register_Trainer.this, "Data inserted!", Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(register_Trainer.this, success_join.class);
   //                 startActivity(i);

                    Toast.makeText(register_Trainer.this, "UserName is exist",
                            Toast.LENGTH_LONG).show();
            }
        });
//        CV.setEnabled(false);
        upload_pdf.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                    PDFDbRef = mDatabase.getInstance().getReference("CV");
                    selectPDF();




            }




        });


    }

    private void selectPDF() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"PDF FILE SELECT"),12);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==12 && resultCode==RESULT_OK && data!=null && data.getData()!=null){

            String path = data.getDataString().substring(data.getDataString().lastIndexOf("/")+1);
            upload_pdf.setText(path);
            CV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UploadPdgFile(path,data.getData());
                }
            });


        }
    }

    private void UploadPdgFile(String path, Uri data) {
        ProgressDialog progressDialog =new ProgressDialog(this);
        progressDialog.setTitle("File is loading...");
        progressDialog.show();
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

        storageManager = FirebaseStorage.getInstance().getReference();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println(formatter.format(date));
       // StorageReference reference = storageManager.child("uploadPDF"+" "+formatter.format(date)+" "+etName.getText().toString()+".pdf");
        StorageReference reference = storageManager.child(etName.getText().toString());
        uri_String="uploadPDF"+" "+formatter.format(date)+" "+etName.getText().toString()+".pdf";
        reference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask =taskSnapshot.getStorage().getDownloadUrl();
                while(!uriTask.isComplete());
                    Uri uri = uriTask.getResult();
                   // uri_String=uri.toString();


                    //PDFDbRef.child(PDFDbRef.push().getKey()).setValue(pdf);
                    progressDialog.dismiss();
                Toast.makeText(register_Trainer.this, "File Upload!", Toast.LENGTH_SHORT).show();


            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double pross = (100.0*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                progressDialog.setTitle("File Uploaded..."+ (int)pross+"%");



            }
        });

    }


}






