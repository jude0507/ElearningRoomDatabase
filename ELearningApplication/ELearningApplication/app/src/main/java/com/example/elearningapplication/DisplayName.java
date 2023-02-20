package com.example.elearningapplication;

import android.content.Context;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.elearningapplication.Model.UsersModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import de.hdodenhof.circleimageview.CircleImageView;

public class DisplayName {

    public static void RetrieveName(Context context, String CollectionPath, String field, String ObjectValue, TextView textView){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection(CollectionPath).whereEqualTo(field, ObjectValue).get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    String name = "";
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                        UsersModel imageInfo = documentSnapshot.toObject(UsersModel.class);
                        imageInfo.setMyid(documentSnapshot.getId());
                        name += imageInfo.getName();

                    }

                    textView.setText(name);


                    //Glide.with(context.getApplicationContext()).load(imageDisplay).placeholder(R.drawable.ic_user_circle).into(circleImageView);

                });

    }

}
