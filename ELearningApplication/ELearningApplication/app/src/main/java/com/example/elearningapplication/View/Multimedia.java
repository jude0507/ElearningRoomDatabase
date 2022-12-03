package com.example.elearningapplication.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

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

import de.hdodenhof.circleimageview.CircleImageView;

public class Multimedia extends AppCompatActivity {

    DrawerLayout drawerLayout;
    TextView nameofuser;
    CircleImageView circleImageViewProfile;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = db.collection("ELearningUsers");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia);

        drawerLayout = findViewById(R.id.mydrawer_layout);
        nameofuser = findViewById(R.id.nameofuser);
        circleImageViewProfile = findViewById(R.id.profile);

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
        recreate();
    }

    public void clickquarterfour(View view){
        QuarterOne.redirectActivity(this, QuarterFour.class);
    }

    public void clicklogout(View view){
        QuarterOne.logout(this);
    }

    protected void onPause() {
        super.onPause();

        QuarterOne.closeDrawer(drawerLayout);
    }

    public void clickprofile(View view){
        startActivity(new Intent(Multimedia.this, Profile.class));
    }

}