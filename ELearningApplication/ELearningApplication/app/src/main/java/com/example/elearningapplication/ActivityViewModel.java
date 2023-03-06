package com.example.elearningapplication;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ActivityViewModel extends AndroidViewModel {
    private ActivityRepo activityRepo;

    private LiveData<List<ActivityQuestions>> mAllQuestions;

    public ActivityViewModel(Application application){
        super(application);
        activityRepo = new ActivityRepo(application);
        mAllQuestions = activityRepo.getmAllQuestions();

    }

    LiveData<List<ActivityQuestions>> getmAllQuestions()
    {
        return mAllQuestions;
    }
}
