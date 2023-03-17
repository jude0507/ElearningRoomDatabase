package com.example.elearningapplication;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ActivityViewModel3 extends AndroidViewModel {
    private ActivityRepo3 activityRepo;

    private LiveData<List<ActivityQuestions3>> mAllQuestions;

    public ActivityViewModel3(Application application){
        super(application);
        activityRepo = new ActivityRepo3(application);
        mAllQuestions = activityRepo.getmAllQuestions();

    }

    LiveData<List<ActivityQuestions3>> getmAllQuestions()
    {
        return mAllQuestions;
    }
}
