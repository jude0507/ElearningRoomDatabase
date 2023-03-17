package com.example.elearningapplication;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ActivityViewModel5 extends AndroidViewModel {
    private ActivityRepo5 activityRepo;

    private LiveData<List<ActivityQuestions5>> mAllQuestions;

    public ActivityViewModel5(Application application){
        super(application);
        activityRepo = new ActivityRepo5(application);
        mAllQuestions = activityRepo.getmAllQuestions();

    }

    LiveData<List<ActivityQuestions5>> getmAllQuestions()
    {
        return mAllQuestions;
    }
}
