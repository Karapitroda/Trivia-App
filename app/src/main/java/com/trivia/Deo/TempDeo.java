package com.trivia.Deo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.trivia.model.TempExam;

import java.util.List;

@Dao
public interface TempDeo {

    @Query("SELECT * FROM TempExam")
    List<TempExam> getAll();

    @Query("SELECT * FROM TempExam")
    List<TempExam> getCount();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(TempExam task);

    @Delete
    void delete(TempExam task);

    @Query("DELETE FROM TempExam")
    void deleteAll();

    @Query("UPDATE TempExam SET qustionOne = :question, optioneOne = :ans WHERE id=:id")
    void updateQueOne(String question, String ans, long id);


    @Query("UPDATE TempExam SET qustionTwo = :question, optioneTwo = :ans WHERE id=:id")
    void updateQueTwo(String question, String ans, long id);

    @Query("SELECT * FROM TempExam WHERE id == :id")
    public  TempExam getTour(long id);


}