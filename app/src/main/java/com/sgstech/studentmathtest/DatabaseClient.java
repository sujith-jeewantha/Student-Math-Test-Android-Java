package com.sgstech.studentmathtest;

import static com.sgstech.studentmathtest.Database.AppDatabase.MIGRATION_1_2;

import android.content.Context;

import androidx.room.Room;

import com.sgstech.studentmathtest.Database.AppDatabase;


public class DatabaseClient {


    private Context mCtx;
    private static DatabaseClient mInstance;

    //our app database object
    private AppDatabase appDatabase;

    private DatabaseClient(Context mCtx) {
        this.mCtx = mCtx;

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabase = Room.databaseBuilder(mCtx,
                AppDatabase.class,                "StudentMathTest")
                .addMigrations(MIGRATION_1_2)
                .build();
    }

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
