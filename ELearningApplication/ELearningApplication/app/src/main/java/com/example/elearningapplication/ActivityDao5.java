package com.example.elearningapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ActivityDao5 {

    @Query("SELECT * FROM activity_week5_questions")
    LiveData<List<ActivityQuestions5>> getAllQuestions();

    @Insert
    void insert(ActivityQuestions5 activityQuestions);

}
