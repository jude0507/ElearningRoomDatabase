package com.example.elearningapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ActivityDao6 {

    @Query("SELECT * FROM activity_week6_questions")
    LiveData<List<ActivityQuestions6>> getAllQuestions();

    @Insert
    void insert(ActivityQuestions6 activityQuestions);

}
