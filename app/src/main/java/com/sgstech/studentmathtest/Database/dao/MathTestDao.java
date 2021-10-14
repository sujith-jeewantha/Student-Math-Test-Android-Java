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


    @Query("SELECT * FROM mathTest WHERE test_student_no =:student_no")
    List<MathTest> getAll(String student_no);

    @Query("SELECT * FROM mathTest WHERE test_student_no =:student_no ORDER BY scores ASC ")
    List<MathTest> getAllASC(String student_no);

    @Query("SELECT * FROM mathTest WHERE test_student_no =:student_no ORDER BY scores DESC")
    List<MathTest> getAllDESC(String student_no);


    @Insert
    void insert(MathTest mathTest);

    @Delete
    void delete(MathTest mathTest);

    @Update
    void update(MathTest mathTest);

    @Query("DELETE FROM mathTest")
    void deleteStudent();

}
