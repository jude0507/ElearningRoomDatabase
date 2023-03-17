package com.example.elearningapplication;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ActivityRepo2 {

    private ActivityDao2 activityDao;
    private LiveData<List<ActivityQuestions2>> mAllQuestions;


    public ActivityRepo2(Application application){
        ActivityRoomDb2 db = ActivityRoomDb2.getInstance(application);
        activityDao = db.activityDao();
        mAllQuestions = activityDao.getAllQuestions();
    }


    public LiveData<List<ActivityQuestions2>> getmAllQuestions(){
        return mAllQuestions;
    }

}
