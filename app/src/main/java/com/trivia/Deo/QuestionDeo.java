package com.trivia.Deo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.trivia.model.Question;
import com.trivia.model.TempExam;

import java.util.List;

@Dao
public interface QuestionDeo {

    @Query("SELECT * FROM Question")
    List<Question> getAll();

    @Query("SELECT * FROM Question  ORDER BY id DESC LIMIT 1")
    Question getLastId();

    @Insert
    void insert(Question task);

    @Delete
    void delete(Question task);
    @Query("DELETE FROM Question")
    void deleteAll();

    @Update
    void update(Question task);

}