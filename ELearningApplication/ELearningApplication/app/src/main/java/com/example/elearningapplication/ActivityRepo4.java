package com.example.elearningapplication;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ActivityRepo4 {

    private ActivityDao4 activityDao;
    private LiveData<List<ActivityQuestions4>> mAllQuestions;


    public ActivityRepo4(Application application){
        ActivityRoomDb4 db = ActivityRoomDb4.getInstance(application);
        activityDao = db.activityDao();
        mAllQuestions = activityDao.getAllQuestions();
    }


    public LiveData<List<ActivityQuestions4>> getmAllQuestions(){
        return mAllQuestions;
    }

}
