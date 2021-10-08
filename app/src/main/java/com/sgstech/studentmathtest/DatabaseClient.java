package com.sgstech.studentmathtest;

import static com.sgstech.studentmathtest.Database.AppDatabase.MIGRATION_0_1;

import android.content.Context;

import androidx.room.Room;

import com.sgstech.studentmathtest.Database.AppDatabase;


public class DatabaseClient {

    /**
     *
     * Developed by : Sujith Jeewantha
     * email        : sujith@suji-tech.com
     * Init Date    : 21/06/2021
     * Updated at   : 17/06/2021
     *
     */

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
                .addMigrations(MIGRATION_0_1)
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
