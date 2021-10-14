package com.sgstech.studentmathtest.SharedPreferance;

import android.content.Context;
import android.content.SharedPreferences;

public class Manager_Cache {


    private static Manager_Cache managerCache;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private Manager_Cache(Context context) {
        sharedPreferences = context.getSharedPreferences(Config_cacheData.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public static Manager_Cache getPreferences(Context context) {
        if (managerCache == null) managerCache = new Manager_Cache(context);
        return managerCache;
    }

    /**
     * getters and setters
     */
    public void setCachedStudentNO(String cachedStudentNO){
        editor.putString(Config_cacheData.STUDENT_NO_CACHED, cachedStudentNO);
        editor.apply();
    }

    public String getCachedStudentNO(){
        return sharedPreferences.getString(Config_cacheData.STUDENT_NO_CACHED, "");
    }

}
