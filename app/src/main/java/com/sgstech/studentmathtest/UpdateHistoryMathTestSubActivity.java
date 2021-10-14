package com.sgstech.studentmathtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sgstech.studentmathtest.Database.model.MathTest;

public class UpdateHistoryMathTestSubActivity extends AppCompatActivity {


    private TextView
            tvScore,
            tvBeginTime,
            tvTotalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_math_test_sub);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        tvScore = findViewById(R.id.txtScore);
        tvBeginTime = findViewById(R.id.txtBeginTime);
        tvTotalTime = findViewById(R.id.txtTotalTime);

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

        tvScore.setText(mathTest.getScores());
        tvBeginTime.setText(mathTest.getTime_of_beginning());
        tvTotalTime.setText(mathTest.getTotal_time_of_the_test());

    }


}
