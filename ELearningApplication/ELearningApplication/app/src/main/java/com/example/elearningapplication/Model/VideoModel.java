package com.example.elearningapplication.Model;

import com.google.firebase.firestore.Exclude;

public class VideoModel {

    String videoName;
    String videoUrl;
    int quarter;

    String myid;

    public VideoModel(){}

    public VideoModel(String videoName, String videoUrl, int quarter) {
        this.videoName = videoName;
        this.videoUrl = videoUrl;
        this.quarter = quarter;
    }

    @Exclude
    public String getMyid() {
        return myid;
    }

    public void setMyid(String documentid) {
        this.myid = myid;
    }

    public String getVideoName() {
        return videoName;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public int getQuarter() {
        return quarter;
    }
}
