package com.sgstech.studentmathtest.Database.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.sgstech.studentmathtest.Database.model.Student;

import java.util.List;

@Dao
public interface StudentDao {


    @Query("SELECT * FROM student")
    List<Student> getAll();

    @Insert
    void insert(Student student);

    @Delete
    void delete(Student student);

    @Update
    void update(Student student);

    @Query("DELETE FROM student")
    void deleteService();

}
