package com.example.elearningapplication;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class QuestionRepository {

    private QuestionnaireDao mQuestionnaireDao;
    private LiveData<List<Questionnaire>> mAllQuestions;

    public QuestionRepository(Application application){
        QuestionRoomDatabase db= QuestionRoomDatabase.getInstance(application);
        mQuestionnaireDao=db.questionnaireDao();
        mAllQuestions=mQuestionnaireDao.getAllQuestions();
    }

    public LiveData<List<Questionnaire>> getmAllQuestions(){
        return mAllQuestions;
    }
}
