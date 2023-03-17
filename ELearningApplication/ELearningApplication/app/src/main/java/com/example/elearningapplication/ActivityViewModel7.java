package com.example.elearningapplication;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ActivityViewModel7 extends AndroidViewModel {
    private ActivityRepo7 activityRepo;

    private LiveData<List<ActivityQuestions7>> mAllQuestions;

    public ActivityViewModel7(Application application){
        super(application);
        activityRepo = new ActivityRepo7(application);
        mAllQuestions = activityRepo.getmAllQuestions();

    }

    LiveData<List<ActivityQuestions7>> getmAllQuestions()
    {
        return mAllQuestions;
    }
}
