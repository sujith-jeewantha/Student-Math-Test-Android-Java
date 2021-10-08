package com.sgstech.studentmathtest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class CheckNetworkStatus {


    public static boolean isNetworkStatusAvialable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if (netInfos != null) {
                return netInfos.isConnected();
            }
        }
        return false;
    }
}
