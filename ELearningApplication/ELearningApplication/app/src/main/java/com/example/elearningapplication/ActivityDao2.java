package com.example.elearningapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ActivityDao2 {

    @Query("SELECT * FROM activity_week2_questions")
    LiveData<List<ActivityQuestions2>> getAllQuestions();

    @Insert
    void insert(ActivityQuestions2 activityQuestions);

}
