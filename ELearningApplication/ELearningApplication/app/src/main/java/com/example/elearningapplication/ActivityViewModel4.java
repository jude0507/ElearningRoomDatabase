package com.example.elearningapplication;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ActivityViewModel4 extends AndroidViewModel {
    private ActivityRepo4 activityRepo;

    private LiveData<List<ActivityQuestions4>> mAllQuestions;

    public ActivityViewModel4(Application application){
        super(application);
        activityRepo = new ActivityRepo4(application);
        mAllQuestions = activityRepo.getmAllQuestions();

    }

    LiveData<List<ActivityQuestions4>> getmAllQuestions()
    {
        return mAllQuestions;
    }
}
