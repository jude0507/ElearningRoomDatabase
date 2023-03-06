package com.example.elearningapplication;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ActivityRepo {

    private ActivityDao activityDao;
    private LiveData<List<ActivityQuestions>> mAllQuestions;


    public ActivityRepo(Application application){
        ActivityRoomDb db = ActivityRoomDb.getInstance(application);
        activityDao = db.activityDao();
        mAllQuestions = activityDao.getAllQuestions();
    }


    public LiveData<List<ActivityQuestions>> getmAllQuestions(){
        return mAllQuestions;
    }

}
