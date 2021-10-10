package com.sgstech.studentmathtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {


    Button btnGetStart;

    String  user_id_global , user_email_global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        user_id_global          = getIntent().getStringExtra("user_id");
        user_email_global       = getIntent().getStringExtra("user_email");

        btnGetStart = (Button) findViewById(R.id.btnGetStart);

        btnGetStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent       = new Intent(getBaseContext(), StudentMain.class);
                intent.putExtra("user_id",user_id_global);
                intent.putExtra("user_email",user_email_global);
                startActivity(intent);
            }
        });


    }
}