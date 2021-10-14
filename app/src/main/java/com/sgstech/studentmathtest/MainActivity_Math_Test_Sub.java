package com.sgstech.studentmathtest;

import android.app.ProgressDialog;
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
import com.sgstech.studentmathtest.Utills.DatabaseClient;
import com.sgstech.studentmathtest.adapter.MathTestSubAdapter;

import java.io.File;
import java.util.List;

public class MainActivity_Math_Test_Sub extends AppCompatActivity {


    private String serviceRegion = "";
    private String service_site_id = "";
    private String service_site_name = "";



    SwipeRefreshLayout pullToRefresh;
    private FloatingActionButton buttonAddServiceSummary;
    private RecyclerView recyclerView;

    int k=1;
    int count =0;
    ProgressDialog progressDialog;
    File image_file_global = null;
    Button btnUploadImage, btnServiceBackToHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_math_test_sub);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        final MathTest mathTest = (MathTest) getIntent().getSerializableExtra("mathTest");


        /**
         * for check db-----------------------------------------------------------------------------
         */

        DebugDB.getAddressLog();

        /**
         * db check over----------------------------------------------------------------------------
         */

        /**
         * retrieve cache data----------------------------------------------------------------------
         */

        /**
         * retrieve cache data over-----------------------------------------------------------------
         */

        pullToRefresh = (SwipeRefreshLayout) findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                serviceRegion       = managerCacheUpdateServiceSummary.getServiceRegionName();
//                service_site_id     = managerCacheUpdateServiceSummary.getServiceSiteID();
//                service_site_name   = managerCacheUpdateServiceSummary.getServiceSiteName();
//                updateServiceSummary(service);
                refreshContent(); // your code

                //pullToRefresh.setRefreshing(false);
            }
        });

        //////////////////////////////////

        //delete database

//        DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
//                .serviceGalleryDao()
//                .delete();

        //////////////////////////////////

//        btnUploadImage          = (Button)findViewById(R.id.button_upload_service_gallery);
        btnServiceBackToHome    = (Button)findViewById(R.id.button_back_service_main);


        recyclerView = findViewById(R.id.recyclerview_tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        buttonAddServiceSummary = findViewById(R.id.floating_button_add_service_summary);
        buttonAddServiceSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_Math_Test_Sub.this, AddMathTestActivity.class);
                startActivity(intent);
            }
        });

        btnServiceBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                finish();
//                Intent intent = new Intent(getApplicationContext(), MathTestMainActivity.class);
//                startActivity(intent);
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

    private void updateServiceSummary(final MathTest mathTest) {

        final String sRegion = serviceRegion;
        final String sSiteId = service_site_id;
        final String sSiteName = service_site_name;


        class UpdateServiceSummary extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .mathTestDao()
                        .update(mathTest);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
//                finish();
//                startActivity(new Intent(MainActivity_Service_Summary.this, MainActivity_Service_Summary.class));
            }
        }

        UpdateServiceSummary uss = new UpdateServiceSummary();
        uss.execute();
    }

//    @Override
//    public void onBackPressed() {
//        finish();
//        startActivity(getIntent());
//    }

    private void getServiceGallerys() {

        class GetServiceGallerys extends AsyncTask<Void, Void, List<MathTest>> {

            @Override
            protected List<MathTest> doInBackground(Void... voids) {
                List<MathTest> mathTestList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .mathTestDao()
                        .getAll();
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

    public String getCurrentTimeStamp()
    {
        Long tsLong     = System.currentTimeMillis()/1000;
        String ts       = tsLong.toString();
        return ts;
    }

}
