package com.example.elearningapplication.Quarter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.elearningapplication.DisplayName;
import com.example.elearningapplication.R;
import com.example.elearningapplication.View.Login;
import com.example.elearningapplication.View.Profile;
import com.example.elearningapplication.RecyclerView.RecyclerViewQ4;
import com.example.elearningapplication.View.StudentAssessment;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import de.hdodenhof.circleimageview.CircleImageView;

public class QuarterFour extends AppCompatActivity {

    DrawerLayout drawerLayout;
    TextView nameofuser;
    CircleImageView circleImageViewProfile;

    LinearLayout containerQ4, studentProgressContainer;

    TextView student_progress;


    VideoView videoView;


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = db.collection("ELearningUsers");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quarter_four);

        drawerLayout = findViewById(R.id.mydrawer_layout);
        nameofuser = findViewById(R.id.nameofuser);
        circleImageViewProfile = findViewById(R.id.profile);
        videoView = (VideoView) findViewById(R.id.videoview);
        containerQ4 = (LinearLayout) findViewById(R.id.quarterFourContainer);
        studentProgressContainer = (LinearLayout) findViewById(R.id.progressStudent);
        student_progress = (TextView) findViewById(R.id.txt_StudentProgress);


        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.samplevideo));
        videoView.start();

        DisplayName.RetrieveName(this, "ELearningUsers", "username", Login.Email_Login, nameofuser);
        Glide.with(getApplicationContext()).load(Profile.outputimageurl).placeholder(R.drawable.ic_user_circle).into(circleImageViewProfile);

        containerQ4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (studentProgressContainer.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(containerQ4, new AutoTransition());
                    studentProgressContainer.setVisibility(View.VISIBLE);
                    //logoutbtn.setBackgroundResource(R.drawable.ic_arrow_up);
                }else{
                    TransitionManager.beginDelayedTransition(containerQ4, new AutoTransition());
                    studentProgressContainer.setVisibility(View.GONE);
                    //logoutbtn.setBackgroundResource(R.drawable.ic_arrow_down);
                }
            }
        });

        student_progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuarterFour.this, StudentAssessment.class));
            }
        });

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
        QuarterOne.redirectActivity(this, QuarterTwo.class);

    }

    public void clickquarterthree(View view){
        QuarterOne.redirectActivity(this, QuarterThree.class);

    }

    public void clickquarterfour(View view){
        recreate();
    }

    public void clicklogout(View view){
        QuarterOne.logout(this);
    }

    protected void onPause() {
        super.onPause();

        QuarterOne.closeDrawer(drawerLayout);
    }

    public void clickprofile(View view){
        startActivity(new Intent(QuarterFour.this, Profile.class));
    }
    public void learningmodule(View view) {
        startActivity(new Intent(QuarterFour.this, RecyclerViewQ4.class));
    }
}