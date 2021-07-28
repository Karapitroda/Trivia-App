package com.trivia.Deo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.trivia.model.Option;
import com.trivia.model.TempExam;

import java.util.List;

@Dao
public interface OptionDeo {

    @Query("SELECT * FROM Option")
    List<Option> getAll();

    @Query("SELECT * FROM Option WHERE questionID=:id")
    List<Option> getCount(int id);

    @Insert
    void insert(Option task);

    @Delete
    void delete(Option task);
    @Query("DELETE FROM Option")
    void deleteAll();

    @Update
    void update(Option task);

}