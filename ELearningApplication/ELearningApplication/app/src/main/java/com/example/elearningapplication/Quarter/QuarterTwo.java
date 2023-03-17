package com.example.elearningapplication.Quarter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

import com.example.elearningapplication.DisplayName;
import com.example.elearningapplication.R;
import com.example.elearningapplication.StartActivityClass;
import com.example.elearningapplication.View.Login;
import com.example.elearningapplication.View.Profile;
import com.example.elearningapplication.RecyclerView.RecyclerViewQ2;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import de.hdodenhof.circleimageview.CircleImageView;

public class QuarterTwo extends AppCompatActivity {

    DrawerLayout drawerLayout;
    CardView learningpdf;

    VideoView videoView;

    TextView nameofuser;
    CircleImageView circleImageViewProfile;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = db.collection("ELearningUsers");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quarter_two);

        drawerLayout = findViewById(R.id.mydrawer_layout);
        nameofuser = findViewById(R.id.nameofuser);
        circleImageViewProfile = findViewById(R.id.profile);
        videoView = (VideoView) findViewById(R.id.videoview);

        String fetchUserName = QuarterOne.sharedPreferences.getString("Username","");
        nameofuser.setText(fetchUserName);

        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.samplevideo));
        videoView.start();

        DisplayName.RetrieveName(this, "ELearningUsers", "username", Login.Email_Login, nameofuser);
        Glide.with(getApplicationContext()).load(Profile.outputimageurl).placeholder(R.drawable.ic_user_circle).into(circleImageViewProfile);

//        String uname = Login.Email_Login;
//        collectionReference.whereEqualTo("username",uname).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                String outputname = "";
//                for (QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){
//                    UsersModel usersModel = documentSnapshot.toObject(UsersModel.class);
//                    usersModel.setMyid(documentSnapshot.getId());
//
//                    String set_uname = usersModel.getUsername();
//                    String set_name = usersModel.getName();
//
//                    outputname += set_name;
//
//                }
//                nameofuser.setText(outputname);
//                Glide.with(getApplicationContext()).load(Profile.outputimageurl).placeholder(R.drawable.ic_user_circle).into(circleImageViewProfile);
//                //Picasso.with(getApplicationContext()).load(Profile.outputimageurl).into(circleImageViewProfile);
//            }
//        });

    }

    public void clickmenu(View view){
        QuarterOne.openDrawer(drawerLayout);
    }

    public void clicklogo(View view){
        QuarterOne.closeDrawer(drawerLayout);
    }

    public void clickquarterone(View view){
        QuarterOne.redirectActivity(this, QuarterOne.class);
    }

    public void clickquartertwo(View view){
        recreate();
    }

    public void clickquarterthree(View view){
        QuarterOne.redirectActivity(this, QuarterThree.class);
    }

    public void clickquarterfour(View view){
        QuarterOne.redirectActivity(this, QuarterFour.class);
    }

    public void clickprofile(View view){
        startActivity(new Intent(QuarterTwo.this, Profile.class));
    }

    public void clicklogout(View view){
        QuarterOne.logout(this);
    }

    protected void onPause() {
        super.onPause();

        QuarterOne.closeDrawer(drawerLayout);
    }

    public void learningmodule(View view) {
        startActivity(new Intent(QuarterTwo.this, RecyclerViewQ2.class));
    }

    public void GoToActivity(View view) {
        startActivity(new Intent(QuarterTwo.this, StartActivityClass.class));
    }
}