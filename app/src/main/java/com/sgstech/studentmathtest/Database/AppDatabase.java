package com.sgstech.studentmathtest.Database;


import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.sgstech.studentmathtest.Database.dao.StudentDao;
import com.sgstech.studentmathtest.Database.model.Student;


@Database(entities = {Student.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract StudentDao studentDao();

    public static final Migration MIGRATION_0_1 = new Migration(0, 1) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {



        }
    };


}
