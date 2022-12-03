package com.example.elearningapplication.Model;

import com.google.firebase.firestore.Exclude;

public class UsersModel {

    String myid;
    public String username, password, name, imageurl, imagename;

    public UsersModel(){

    }

    public UsersModel(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    @Exclude
    public String getMyid() {
        return myid;
    }

    public void setMyid(String documentid) {
        this.myid = myid;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getImagename() {
        return imagename;
    }
}

