package com.sgstech.studentmathtest.Database.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.sgstech.studentmathtest.Database.model.MathTest;

import java.util.List;

@Dao
public interface MathTestDao {


    @Query("SELECT * FROM mathTest")
    List<MathTest> getAll();

    @Insert
    void insert(MathTest mathTest);

    @Delete
    void delete(MathTest mathTest);

    @Update
    void update(MathTest mathTest);

    @Query("DELETE FROM mathTest")
    void deleteStudent();

}
