package com.example.elearningapplication;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class QuestionViewModel extends AndroidViewModel {

    private QuestionRepository mRepository;
    private LiveData<List<Questionnaire>> mAllQuestions;

    public QuestionViewModel(Application application){
        super(application);
        mRepository = new QuestionRepository(application);
        mAllQuestions= mRepository.getmAllQuestions();
    }

    LiveData<List<Questionnaire>> getmAllQuestions(){
        return mAllQuestions;
    }
}
