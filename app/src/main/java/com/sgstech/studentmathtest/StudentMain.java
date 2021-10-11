package com.sgstech.studentmathtest;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amitshekhar.DebugDB;
import com.sgstech.studentmathtest.Database.model.Student;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class StudentMain extends AppCompatActivity {


    LinearLayout btnLinServiceSummary, btnLinServiceGallery ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        btnLinServiceSummary    = (LinearLayout)findViewById(R.id.btnLinServiceSummary);
        btnLinServiceGallery     = (LinearLayout)findViewById(R.id.btnLinServiceGallery);

        btnLinServiceSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    if (CheckNetworkStatus.isNetworkStatusAvialable(getBaseContext())) {

                            Intent intent   = new Intent(getBaseContext(), MainActivity_Student_Profile.class);
                            startActivity(intent);


                    } else {

                        Intent intent = new Intent(StudentMain.this, AlertNoInternet.class);
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    Log.d("errorBtn_Go", e.toString());
                }

            }
        });

        btnLinServiceGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    if (CheckNetworkStatus.isNetworkStatusAvialable(getBaseContext())) {

                            Intent intent   = new Intent(getBaseContext(), MainActivity_Student_Math_Test.class);
                            startActivity(intent);

                    } else {

                        Intent intent = new Intent(StudentMain.this, AlertNoInternet.class);
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    Log.d("errorBtn_Go", e.toString());
                }

            }
        });

    }

}