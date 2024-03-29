package com.example.elearningapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ActivityDao {

    @Query("SELECT * FROM activity_week1_questions")
    LiveData<List<ActivityQuestions>> getAllQuestions();

    @Insert
    void insert(ActivityQuestions activityQuestions);

}
