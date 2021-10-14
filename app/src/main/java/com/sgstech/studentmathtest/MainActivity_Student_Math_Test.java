package com.sgstech.studentmathtest;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.amitshekhar.DebugDB;
import com.sgstech.studentmathtest.Database.model.Student;
import com.sgstech.studentmathtest.Utills.DatabaseClient;
import com.sgstech.studentmathtest.adapter.StudentMathTestAdapter;

import java.util.ArrayList;
import java.util.List;



public class MainActivity_Student_Math_Test extends AppCompatActivity {



    private ArrayList<Uri> arrayList;


    SwipeRefreshLayout pullToRefresh;
    private RecyclerView recyclerView;


    Button btnServiceBackToHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_student_math_test);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        /**
         * for check db-----------------------------------------------------------------------------
         */

        DebugDB.getAddressLog();

        /**
         * db check over----------------------------------------------------------------------------
         */



        pullToRefresh = (SwipeRefreshLayout) findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent(); // your code
                //pullToRefresh.setRefreshing(false);
            }
        });


        btnServiceBackToHome    = (Button)findViewById(R.id.button_back_service_main);


        recyclerView = findViewById(R.id.recyclerview_tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        btnServiceBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), StudentMain.class);
                startActivity(intent);
            }
        });


        getServiceGallerys();


    }


    private void refreshContent(){

        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                pullToRefresh.setRefreshing(false);
                finish();
                startActivity(getIntent());
            }
        }, 3000);

    }

//    @Override
//    public void onBackPressed() {
//        finish();
//        startActivity(getIntent());
//    }

    private void getServiceGallerys() {
        class GetServiceGallerys extends AsyncTask<Void, Void, List<Student>> {

            @Override
            protected List<Student> doInBackground(Void... voids) {
                List<Student> serviceList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .studentDao()
                        .getAll();
                return serviceList;
            }

            @Override
            protected void onPostExecute(List<Student> serviceGalleries) {
                super.onPostExecute(serviceGalleries);
                StudentMathTestAdapter adapter = new StudentMathTestAdapter(MainActivity_Student_Math_Test.this, serviceGalleries);
                recyclerView.setAdapter(adapter);
            }
        }

        GetServiceGallerys gt = new GetServiceGallerys();
        gt.execute();
    }



}
