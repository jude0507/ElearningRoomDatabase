package com.example.elearningapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuestionnaireDao {
    @Query("SELECT * FROM question_table")
    LiveData<List<Questionnaire>> getAllQuestions();

    @Insert
    void insert(Questionnaire questionnaire);
}
