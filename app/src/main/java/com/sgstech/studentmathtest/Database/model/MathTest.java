package com.sgstech.studentmathtest.Database.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class MathTest implements Serializable {



    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "scores")
    public String scores;

    @ColumnInfo(name = "time_of_beginning")
    public String time_of_beginning;

    @ColumnInfo(name = "total_time_of_the_test")
    public String total_time_of_the_test;



    /**
     * Getters and Setters----------------------------------------------------------------------
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }

    public String getTime_of_beginning() {
        return time_of_beginning;
    }

    public void setTime_of_beginning(String time_of_beginning) {
        this.time_of_beginning = time_of_beginning;
    }

    public String getTotal_time_of_the_test() {
        return total_time_of_the_test;
    }

    public void setTotal_time_of_the_test(String total_time_of_the_test) {
        this.total_time_of_the_test = total_time_of_the_test;
    }



}
