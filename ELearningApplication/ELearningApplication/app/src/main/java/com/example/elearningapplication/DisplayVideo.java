package com.example.elearningapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.elearningapplication.View.Multimedia;


public class DisplayVideo extends AppCompatActivity {

    VideoView videoView;

    String videoUrl;
    Uri uri;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        videoView = findViewById(R.id.video_View);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

    }

    @Override
    protected void onStart() {
        super.onStart();
        videoUrl = getIntent().getStringExtra("VideoUrl");

        uri = Uri.parse(videoUrl);
        videoView.setVideoURI(uri);
        videoView.start();
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setOnPreparedListener(mp -> progressDialog.dismiss());
    }

    int pressed = 0;
    @Override
    public void onBackPressed() {
        pressed++;
        if (pressed == 1){
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
        }else{
            startActivity(new Intent(this, Multimedia.class));
        }
    }
}