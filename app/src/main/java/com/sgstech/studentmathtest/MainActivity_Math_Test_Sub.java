package com.sgstech.studentmathtest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.amitshekhar.DebugDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sgstech.studentmathtest.Database.model.MathTest;
import com.sgstech.studentmathtest.SharedPreferance.Manager_Cache;
import com.sgstech.studentmathtest.Utills.DatabaseClient;
import com.sgstech.studentmathtest.adapter.MathTestSubAdapter;

import java.util.List;

public class MainActivity_Math_Test_Sub extends AppCompatActivity {

    private Manager_Cache managerCacheStudentNo;

    SwipeRefreshLayout pullToRefresh;
    private FloatingActionButton buttonAddServiceSummary;
    private RecyclerView recyclerView;
    private boolean isOrderASC = false; //defined as class variable;

    String studentNo;

    Button btnSort;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_math_test_sub);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        managerCacheStudentNo = Manager_Cache.getPreferences(this);
        studentNo = getIntent().getStringExtra("student_no");
        saveCachedStudentNo();



        btnSort = (Button)findViewById(R.id.btn_sort);

        btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!isOrderASC){
                    getStudentTestDESC();
                    isOrderASC=true;
                }else {
                    getStudentTestASC();
                    isOrderASC=false;
                }

            }
        });

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

            }
        });

        /**
         * sort button
         */


        recyclerView = findViewById(R.id.recyclerview_tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        buttonAddServiceSummary = findViewById(R.id.floating_button_add_service_summary);
        buttonAddServiceSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_Math_Test_Sub.this, AddMathTestActivity.class);
                studentNo = managerCacheStudentNo.getCachedStudentNO();
                intent.putExtra("add_student_no", studentNo);
                startActivity(intent);
            }
        });

        getServiceGallerys();

    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(getApplicationContext(), MainActivity_Student_Math_Test.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
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


    private void getServiceGallerys() {

        class GetServiceGallerys extends AsyncTask<Void, Void, List<MathTest>> {

            @Override
            protected List<MathTest> doInBackground(Void... voids) {
                List<MathTest> mathTestList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .mathTestDao()
                        .getAll(studentNo);
                return mathTestList;
            }

            @Override
            protected void onPostExecute(List<MathTest> mathTests) {
                super.onPostExecute(mathTests);
                MathTestSubAdapter adapter = new MathTestSubAdapter(MainActivity_Math_Test_Sub.this, mathTests);
                recyclerView.setAdapter(adapter);
            }
        }

        GetServiceGallerys gt = new GetServiceGallerys();
        gt.execute();

    }

    private void getStudentTestASC() {

        class GetServiceGallerys extends AsyncTask<Void, Void, List<MathTest>> {

            @Override
            protected List<MathTest> doInBackground(Void... voids) {
                List<MathTest> mathTestList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .mathTestDao()
                        .getAllASC(studentNo);
                return mathTestList;
            }

            @Override
            protected void onPostExecute(List<MathTest> mathTests) {
                super.onPostExecute(mathTests);
                MathTestSubAdapter adapter = new MathTestSubAdapter(MainActivity_Math_Test_Sub.this, mathTests);
                recyclerView.setAdapter(adapter);
            }
        }

        GetServiceGallerys gt = new GetServiceGallerys();
        gt.execute();

    }

    private void getStudentTestDESC() {

        class GetServiceGallerys extends AsyncTask<Void, Void, List<MathTest>> {

            @Override
            protected List<MathTest> doInBackground(Void... voids) {
                List<MathTest> mathTestList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .mathTestDao()
                        .getAllDESC(studentNo);
                return mathTestList;
            }

            @Override
            protected void onPostExecute(List<MathTest> mathTests) {
                super.onPostExecute(mathTests);
                MathTestSubAdapter adapter = new MathTestSubAdapter(MainActivity_Math_Test_Sub.this, mathTests);
                recyclerView.setAdapter(adapter);
            }
        }

        GetServiceGallerys gt = new GetServiceGallerys();
        gt.execute();

    }

    private void saveCachedStudentNo() {
        managerCacheStudentNo.setCachedStudentNO(studentNo);
    }


}
