package com.sgstech.studentmathtest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.sgstech.studentmathtest.Utills.AlertNoInternet;
import com.sgstech.studentmathtest.Utills.CheckNetworkStatus;

public class StudentMain extends AppCompatActivity {



    Button btnStudents, btnMathTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        btnStudents = (Button) findViewById(R.id.btnStudents);
        btnMathTest = (Button) findViewById(R.id.btnMathTest);

        btnStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

        btnMathTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}