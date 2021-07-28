package com.trivia.Deo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.trivia.model.History;
import com.trivia.model.TempExam;

import java.util.List;

@Dao
public interface HistoryDeo {

    @Query("SELECT * FROM History")
    List<History> getAll();

    @Query("SELECT * FROM History")
    List<History> getCount();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(History task);

    @Delete
    void delete(History task);
    @Query("DELETE FROM History")
    void deleteAll();

    @Update
    void update(History task);

}