package com.sgstech.studentmathtest.Utills;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.sgstech.studentmathtest.R;

public class AlertNoInternet extends AppCompatActivity {



    Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_no_internet);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        try
        {

            btnOk   = (Button)findViewById(R.id.btnOk);
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent   = new Intent();
                    finish();

                }
            });

        }
        catch (Exception e){

            finish();

        }
    }


    @Override
    public void onBackPressed() {
        Intent intent   = new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }
}