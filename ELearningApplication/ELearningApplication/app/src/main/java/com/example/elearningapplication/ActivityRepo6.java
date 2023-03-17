package com.example.elearningapplication;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ActivityRepo6 {

    private ActivityDao6 activityDao;
    private LiveData<List<ActivityQuestions6>> mAllQuestions;


    public ActivityRepo6(Application application){
        ActivityRoomDb6 db = ActivityRoomDb6.getInstance(application);
        activityDao = db.activityDao();
        mAllQuestions = activityDao.getAllQuestions();
    }


    public LiveData<List<ActivityQuestions6>> getmAllQuestions(){
        return mAllQuestions;
    }

}
