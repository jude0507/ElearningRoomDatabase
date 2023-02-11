package com.example.elearningapplication.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.elearningapplication.Model.UsersModel;
import com.example.elearningapplication.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class QuarterOne extends AppCompatActivity {

    DrawerLayout drawerLayout;
    TextView nameofuser;
    CircleImageView circleImageViewProfile;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = db.collection("ELearningUsers");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quarter_one);

        drawerLayout = findViewById(R.id.mydrawer_layout);
        nameofuser = findViewById(R.id.nameofuser);
        circleImageViewProfile = findViewById(R.id.profile);

        showUserInformation();

    }

    public void showUserInformation(){
        String uname = Login.uname;
        collectionReference.whereEqualTo("username",uname).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                String outputname = "";
                for (QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){
                    UsersModel usersModel = documentSnapshot.toObject(UsersModel.class);
                    usersModel.setMyid(documentSnapshot.getId());

                    String set_uname = usersModel.getUsername();
                    String set_name = usersModel.getName();

                    outputname += set_name;

                }
                nameofuser.setText(outputname);
                Glide.with(getApplicationContext()).load(Profile.outputimageurl).placeholder(R.drawable.ic_user_circle).into(circleImageViewProfile);
                //Picasso.with(getApplicationContext()).load(Profile.outputimageurl).into(circleImageViewProfile);
            }
        });
    }


    public void clickmenu(View view){
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void clicklogo(View view){
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void clickquarterone(View view){
        recreate();
    }

    public void clickquartertwo(View view){
        redirectActivity(this, QuarterTwo.class);
    }

    public void clickquarterthree(View view){
        redirectActivity(this, QuarterThree.class);
    }

    public void clickquarterfour(View view){
        redirectActivity(this, QuarterFour.class);
    }

    public void clicklogout(View view){
        logout(this);
    }

    public static void logout(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Logout");
        builder.setMessage("Do you want to logout ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();
                System.exit(0);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    public static void redirectActivity(Activity activity, Class myclass) {

        Intent intent = new Intent(activity,myclass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);

    }

    protected void onPause() {
        super.onPause();

        closeDrawer(drawerLayout);
    }
    public void gotoMultimedia(View view) {
        startActivity(new Intent(QuarterOne.this, Multimedia.class));

    }

    public void gotoLearningMaterials(View view) {
        startActivity(new Intent(QuarterOne.this, LearningMaterials.class));
    }

    public void gotoStudentAssessment(View view) {
        startActivity(new Intent(QuarterOne.this, StudentAssessment.class));
    }

    public void gotoStudentProgress(View view) {
        startActivity(new Intent(QuarterOne.this, StudentProgress.class));
    }

    public void clickprofile(View view){
        startActivity(new Intent(QuarterOne.this, Profile.class));
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logout");
        builder.setMessage("Do you want to logout ?");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(QuarterOne.this, Login.class));
                finishAffinity();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }
}