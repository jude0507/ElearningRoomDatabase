package com.example.elearningapplication;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ActivityRepo7 {

    private ActivityDao7 activityDao;
    private LiveData<List<ActivityQuestions7>> mAllQuestions;


    public ActivityRepo7(Application application){
        ActivityRoomDb7 db = ActivityRoomDb7.getInstance(application);
        activityDao = db.activityDao();
        mAllQuestions = activityDao.getAllQuestions();
    }


    public LiveData<List<ActivityQuestions7>> getmAllQuestions(){
        return mAllQuestions;
    }

}
