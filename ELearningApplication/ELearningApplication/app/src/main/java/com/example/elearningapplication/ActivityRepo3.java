package com.example.elearningapplication;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ActivityRepo3 {

    private ActivityDao3 activityDao;
    private LiveData<List<ActivityQuestions3>> mAllQuestions;


    public ActivityRepo3(Application application){
        ActivityRoomDb3 db = ActivityRoomDb3.getInstance(application);
        activityDao = db.activityDao();
        mAllQuestions = activityDao.getAllQuestions();
    }


    public LiveData<List<ActivityQuestions3>> getmAllQuestions(){
        return mAllQuestions;
    }

}
