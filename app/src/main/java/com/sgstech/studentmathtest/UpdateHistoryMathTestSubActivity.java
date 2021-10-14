package com.sgstech.studentmathtest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sgstech.studentmathtest.Database.model.MathTest;
import com.sgstech.studentmathtest.Database.model.Student;
import com.sgstech.studentmathtest.Utills.DatabaseClient;

public class UpdateHistoryMathTestSubActivity extends AppCompatActivity {


    private TextView
            tvStudentNo,
            tvFirstName,
            tvLastName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_math_test_sub);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        tvStudentNo = findViewById(R.id.student_u_no);
        tvFirstName = findViewById(R.id.enter_student_first_name);
        tvLastName = findViewById(R.id.enter_student_last_name);


        final MathTest mathTest = (MathTest) getIntent().getSerializableExtra("mathTest");

        loadStudentProfile(mathTest);

        findViewById(R.id.button_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(UpdateHistoryMathTestSubActivity.this, MainActivity_Math_Test_Sub.class));

            }
        });

    }



    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(getApplicationContext(), MainActivity_Math_Test_Sub.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }




    private void loadStudentProfile(MathTest mathTest) {

        tvStudentNo.setText(mathTest.getScores());
        tvFirstName.setText(mathTest.getTime_of_beginning());
        tvLastName.setText(mathTest.getTotal_time_of_the_test());


    }





}
