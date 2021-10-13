package com.sgstech.studentmathtest.Database;


import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.sgstech.studentmathtest.Database.dao.MathTestDao;
import com.sgstech.studentmathtest.Database.dao.StudentDao;
import com.sgstech.studentmathtest.Database.model.MathTest;
import com.sgstech.studentmathtest.Database.model.Student;


@Database(entities = {Student.class , MathTest.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract StudentDao studentDao();
    public abstract MathTestDao mathTestDao();

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {



        }
    };


}
