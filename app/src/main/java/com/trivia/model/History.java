package com.trivia.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class History implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "qustionOne")
    private String qustionOne;

    @ColumnInfo(name = "qustionTwo")
    private String qustionTwo;

    @ColumnInfo(name = "optioneOne")
    private String optioneOne;

    @ColumnInfo(name = "optioneTwo")
    private String optioneTwo;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQustionOne() {
        return qustionOne;
    }

    public void setQustionOne(String qustionOne) {
        this.qustionOne = qustionOne;
    }

    public String getQustionTwo() {
        return qustionTwo;
    }

    public void setQustionTwo(String qustionTwo) {
        this.qustionTwo = qustionTwo;
    }

    public String getOptioneOne() {
        return optioneOne;
    }

    public void setOptioneOne(String optioneOne) {
        this.optioneOne = optioneOne;
    }

    public String getOptioneTwo() {
        return optioneTwo;
    }

    public void setOptioneTwo(String optioneTwo) {
        this.optioneTwo = optioneTwo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
