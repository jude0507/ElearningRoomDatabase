package com.example.elearningapplication.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.elearningapplication.DisplayName;
import com.example.elearningapplication.DisplayVideo;
import com.example.elearningapplication.Quarter.QuarterFour;
import com.example.elearningapplication.Quarter.QuarterOne;
import com.example.elearningapplication.Quarter.QuarterTwo;
import com.example.elearningapplication.R;
import com.example.elearningapplication.RecyclerViewInterface;
import com.example.elearningapplication.Adapter.VideoAdapter;
import com.example.elearningapplication.Model.VideoModel;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Multimedia extends AppCompatActivity implements RecyclerViewInterface {

    DrawerLayout drawerLayout;
    TextView nameofuser;
    CircleImageView circleImageViewProfile;
    ArrayList<VideoModel> modelArrayList;
    RecyclerView recyclerView;
    VideoAdapter videoAdapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = db.collection("ELearningUsers");
    CollectionReference videoReference = db.collection("Videos");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia);

        drawerLayout = findViewById(R.id.mydrawer_layout);
        nameofuser = findViewById(R.id.nameofuser);
        circleImageViewProfile = findViewById(R.id.profile);
        recyclerView = findViewById(R.id.VideoRecyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        modelArrayList = new ArrayList<>();
        videoAdapter = new VideoAdapter(Multimedia.this, modelArrayList, this);
        recyclerView.setAdapter(videoAdapter);
        RecyclerViewListener();

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
    private void RecyclerViewListener() {
        videoReference.whereEqualTo("quarter", 1).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (DocumentChange documentChange: value.getDocumentChanges()){
                    if (documentChange.getType() == DocumentChange.Type.ADDED){
                        modelArrayList.add(documentChange.getDocument().toObject(VideoModel.class));
                    }
                    videoAdapter.notifyDataSetChanged();
                }
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

    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(this, DisplayVideo.class);
        intent.putExtra("VideoUrl", modelArrayList.get(position).getVideoUrl());
        startActivity(intent);

    }
}