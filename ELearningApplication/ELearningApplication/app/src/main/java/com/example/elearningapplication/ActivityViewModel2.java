package com.example.elearningapplication;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ActivityViewModel2 extends AndroidViewModel {
    private ActivityRepo2 activityRepo;

    private LiveData<List<ActivityQuestions2>> mAllQuestions;

    public ActivityViewModel2(Application application){
        super(application);
        activityRepo = new ActivityRepo2(application);
        mAllQuestions = activityRepo.getmAllQuestions();

    }

    LiveData<List<ActivityQuestions2>> getmAllQuestions()
    {
        return mAllQuestions;
    }
}
