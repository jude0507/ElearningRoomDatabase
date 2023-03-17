package com.example.elearningapplication;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ActivityRepo5 {

    private ActivityDao5 activityDao;
    private LiveData<List<ActivityQuestions5>> mAllQuestions;


    public ActivityRepo5(Application application){
        ActivityRoomDb5 db = ActivityRoomDb5.getInstance(application);
        activityDao = db.activityDao();
        mAllQuestions = activityDao.getAllQuestions();
    }


    public LiveData<List<ActivityQuestions5>> getmAllQuestions(){
        return mAllQuestions;
    }

}
