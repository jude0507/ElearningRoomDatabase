package com.example.elearningapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ActivityDao7 {

    @Query("SELECT * FROM activity_week7_questions")
    LiveData<List<ActivityQuestions7>> getAllQuestions();

    @Insert
    void insert(ActivityQuestions7 activityQuestions);

}
