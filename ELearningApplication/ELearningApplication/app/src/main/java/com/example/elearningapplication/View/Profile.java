package com.example.elearningapplication.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.elearningapplication.Model.UsersModel;
import com.example.elearningapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {

    public static final int CAMERA_PERMISSION_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 1;
    public static final int GALLERY_REQUEST_CODE = 2;
    public static final int CROP_IMAGE_REQUEST_CODE = 203;
    TextInputEditText textInputEditText_name, textInputEditText_username, textInputEditText_password;
    TextInputLayout layout_password;
    CircleImageView circleImageViewProfilepic;
    ImageView imageViewEditPicture;
    Button btn_update;
    ProgressDialog progressDialog;

    public static Uri imageuri;
    public static String outputimageurl = "";
    String imageURL;
    FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    StorageReference storageReference = firebaseStorage.getReference();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = db.collection("ELearningUsers");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textInputEditText_name = findViewById(R.id.TxtET_fullname);
        textInputEditText_username = findViewById(R.id.TxtET_username);
        textInputEditText_password = findViewById(R.id.TxtET_password);
        circleImageViewProfilepic = findViewById(R.id.profile);
        imageViewEditPicture = findViewById(R.id.iv_editpicture);
        layout_password = findViewById(R.id.TIL_toggle);
        btn_update = findViewById(R.id.btn_update);

        textInputEditText_name.setEnabled(false);
        textInputEditText_username.setEnabled(false);
        textInputEditText_password.setEnabled(false);
        imageViewEditPicture.setEnabled(false);
        layout_password.setEnabled(false);
        btn_update.setEnabled(false);

        fetchdata();

        imageViewEditPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });

    }

    private void choosePicture(){
        AlertDialog.Builder myBuilder = new AlertDialog.Builder(Profile.this);
        LayoutInflater myinflater = getLayoutInflater();
        View mydialogView = myinflater.inflate(R.layout.camera_gallery_dialog,null);
        myBuilder.setCancelable(false);
        myBuilder.setView(mydialogView);

        ImageView imageViewCamera = mydialogView.findViewById(R.id.picFromCam);
        ImageView imageViewGallery = mydialogView.findViewById(R.id.picFromGallery);
        ImageView imageViewClose = mydialogView.findViewById(R.id.close_dialog);

        final AlertDialog alertDialogmyPicture = myBuilder.create();
        alertDialogmyPicture.show();

        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogmyPicture.dismiss();
            }
        });

        imageViewCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkAndRequestPermission()){
                    fetchfromCamera();
                    alertDialogmyPicture.cancel();
                }
            }
        });

        imageViewGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchfromGallery();
                alertDialogmyPicture.cancel();
            }
        });
    }

    private void fetchfromCamera(){
//        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (takePicture.resolveActivity(getPackageManager()) != null){
//            startActivityForResult(takePicture, 2);
//        }
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture, CAMERA_REQUEST_CODE);
    }

    private void fetchfromGallery(){
        Intent pickimage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickimage, GALLERY_REQUEST_CODE);
//        CropImage.activity().setAspectRatio(1,1).start(Profile.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            imageuri = data.getData();
            //uploadToStorage();
            circleImageViewProfilepic.setImageBitmap(bitmap);

        }if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null){
            //CropImage.ActivityResult result = CropImage.getActivityResult(data);
            //imageuri = result.getUri();
            imageuri = data.getData();
            circleImageViewProfilepic.setImageURI(imageuri);

            //uploadToStorage();
            //circleImageViewProfilepic.setImageURI(imageuri);

        }
//        else{
//            Toast.makeText(this, "Error, Try Again", Toast.LENGTH_SHORT).show();
//        }
//        switch (requestCode){
//            case 1:
//                if (resultCode == RESULT_OK){
//                    Uri selectedImageURI = data.getData();
//                    imageViewProfilepic.setImageURI(selectedImageURI);
//                }
//                break;
//
//            case 2:
//                if (resultCode == RESULT_OK){
//                    Bundle bundle = data.getExtras();
//                    Bitmap bitmap = (Bitmap) bundle.get("data");
//                    imageViewProfilepic.setImageBitmap(bitmap);
//
//                }
//                break;
//        }
    }
    private boolean checkAndRequestPermission(){
        if(Build.VERSION.SDK_INT >= 23){
            int cameraPermission = ActivityCompat.checkSelfPermission(Profile.this, Manifest.permission.CAMERA);
            if (cameraPermission == PackageManager.PERMISSION_DENIED){
                ActivityCompat.requestPermissions(Profile.this, new String[]{Manifest.permission.CAMERA},20);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 20 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            fetchfromCamera();
        }else{
            Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
        }
    }

    public void gotoquarterone(View view) {
        startActivity(new Intent(Profile.this, QuarterOne.class));
    }

    public void uploadToStorage(){
        if (imageuri != null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Updating.........");
            progressDialog.show();
            //StorageReference filepath = storageReference.child("Images/").child(imageuri.getLastPathSegment());
            storageReference = firebaseStorage.getReference().child("images/").child(imageuri.getLastPathSegment());
            storageReference.putFile(imageuri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                imageURL = uri.toString();
                                DocumentReference documentReference = db.collection("ELearningUsers").document(Login.Email_Login);
                                Map<String, Object> map = new HashMap<>();
                                map.put("imageurl", uri);
                                map.put("imagename",imageuri.toString());
                                documentReference.update(map);
                                progressDialog.dismiss();
                                Toast.makeText(Profile.this, "Updated Successfully", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Profile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress = (100.0 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                    progressDialog.setMessage(((int) progress) + "% Uploading....");
                }
            });
        }else{
            Toast.makeText(this, "No image uploaded", Toast.LENGTH_SHORT).show();
        }
    }

    public void updatedata(View view) {
        updateuserinfo();
        textInputEditText_password.setEnabled(false);
        layout_password.setEnabled(false);
        imageViewEditPicture.setEnabled(false);
    }

    public void fetchdata(){
        String uname = Login.Email_Login;
        collectionReference.whereEqualTo("username",uname).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                String outputname = "";
                String outputuser = "";
                String outputpassword = "";
                for (QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){
                    UsersModel usersModel = documentSnapshot.toObject(UsersModel.class);
                    usersModel.setMyid(documentSnapshot.getId());

                    String set_uname = usersModel.getUsername();
                    String set_name = usersModel.getName();
                    String set_pass = usersModel.getPassword();
                    String set_imageurl = usersModel.getImageurl();


                    outputuser += set_uname;
                    outputname += set_name;
                    outputpassword += set_pass;
                    outputimageurl += set_imageurl;

                }
                textInputEditText_name.setText(outputname);
                textInputEditText_username.setText(outputuser);
                textInputEditText_password.setText(outputpassword);
                //Picasso.with(getApplicationContext()).load(outputimageurl).into(circleImageViewProfilepic);
                Glide.with(getApplicationContext()).load(outputimageurl).placeholder(R.drawable.ic_user_circle).into(circleImageViewProfilepic);
            }
        });
    }

    public void getImagefromStorage(){
        
    }

    public void updateuserinfo(){
        String fetchpassword = textInputEditText_password.getText().toString();
        DocumentReference documentReference = db.collection("ELearningUsers").document(Login.Email_Login);
        documentReference.update(Login.MY_PASSWORD,fetchpassword);
        uploadToStorage();
        //Toast.makeText(this,"Updated Successfully",Toast.LENGTH_LONG).show();


    }

    public void confirmation(View view) {
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
        alBuilder.setTitle("Confirmation Message")
                .setMessage("Do you want to update your data?");
        alBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                textInputEditText_password.setEnabled(true);
                layout_password.setEnabled(true);
                imageViewEditPicture.setEnabled(true);
                btn_update.setEnabled(true);

            }
        });
        alBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alBuilder.show();
    }
}