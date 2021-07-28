package com.trivia.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.trivia.Deo.HistoryDeo;
import com.trivia.Deo.OptionDeo;
import com.trivia.Deo.QuestionDeo;
import com.trivia.Deo.TempDeo;
import com.trivia.model.History;
import com.trivia.model.Option;
import com.trivia.model.Question;
import com.trivia.model.TempExam;

@Database(entities = {History.class, TempExam.class, Question.class, Option.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HistoryDeo cartDeo();
    public abstract TempDeo tempDeo();
    public abstract QuestionDeo questionDeo();
    public abstract OptionDeo optionDeo();
}