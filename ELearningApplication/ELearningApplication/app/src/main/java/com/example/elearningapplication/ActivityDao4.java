package com.example.elearningapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ActivityDao4 {

    @Query("SELECT * FROM activity_week4_questions")
    LiveData<List<ActivityQuestions4>> getAllQuestions();

    @Insert
    void insert(ActivityQuestions4 activityQuestions);

}
