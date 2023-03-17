package com.example.elearningapplication;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ActivityViewModel6 extends AndroidViewModel {
    private ActivityRepo6 activityRepo;

    private LiveData<List<ActivityQuestions6>> mAllQuestions;

    public ActivityViewModel6(Application application){
        super(application);
        activityRepo = new ActivityRepo6(application);
        mAllQuestions = activityRepo.getmAllQuestions();

    }

    LiveData<List<ActivityQuestions6>> getmAllQuestions()
    {
        return mAllQuestions;
    }
}
