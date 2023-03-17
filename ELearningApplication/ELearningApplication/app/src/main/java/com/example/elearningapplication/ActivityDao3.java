package com.example.elearningapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ActivityDao3 {

    @Query("SELECT * FROM activity_week3_questions")
    LiveData<List<ActivityQuestions3>> getAllQuestions();

    @Insert
    void insert(ActivityQuestions3 activityQuestions);

}
