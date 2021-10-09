package com.sgstech.studentmathtest;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sgstech.studentmathtest.Database.model.Student;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ServiceMain extends AppCompatActivity {


    private Manager_Cache managerCacheServiceMain;

    private String upload_service_final_api_url = new Manager_API().upload_service_final_api_url;

    private String user_id_global;
    private String service_team_info_status;
    private String service_site_info_status;
    private String service_summary_status;
    private String service_gallery_status;

    private String service_gallery_status_check = "";

    private String check_service_unit;

    private String service_site_out_time;

    LinearLayout btnLinSiteInfo,btnLinTeamInfo,btnLinServiceSummary, btnLinServiceGallery ;

    Button btnUploadServiceFinal;

    ProgressDialog progressDialog;

    ImageView   image_service_site,
                image_service_team,
                image_service_summary ,
                image_service_gallery;
    TextView display_site_status,display_team_status,display_summary_status,display_gallery_status;


    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        managerCacheServiceMain = Manager_Cache.getPreferences(this);

        final Student student = (Student) getIntent().getSerializableExtra("serviceSummary");

//        loadServiceSummary(service);


        /**
         * for check db-----------------------------------------------------------------------------
         */

        DebugDB.getAddressLog();

        /**
         * db check over----------------------------------------------------------------------------
         */

        LinearLayout serviceSummaryLayout = (LinearLayout) findViewById(R.id.serviceSummaryLayout);
        LinearLayout serviceGalleryLayout = (LinearLayout) findViewById(R.id.serviceGalleryLayout);



//        if(serviceGalleryLayout.getVisibility() == View.INVISIBLE)
//        {
//            service_gallery_status_check = false;
//        }
//        else {
//            service_gallery_status_check = true;
//        }


//        getServiceGallerys();




//        service_team_info_status  = managerCacheServiceMain.getServiceTeamInfoStatus();
//        service_site_info_status  = managerCacheServiceMain.getServiceSiteInfoStatus();
//        service_summary_status    = managerCacheServiceMain.getServiceSummaryStatus();
//        service_gallery_status    = managerCacheServiceMain.getServiceGalleryStatus();

        user_id_global          = getIntent().getStringExtra("user_id");


        btnLinServiceSummary    = (LinearLayout)findViewById(R.id.btnLinServiceSummary);
        btnLinServiceGallery     = (LinearLayout)findViewById(R.id.btnLinServiceGallery);

        display_summary_status      = (TextView)findViewById(R.id.display_summary_status);
        display_gallery_status      = (TextView)findViewById(R.id.display_gallery_status);




        final Handler handlerNotoficationWindow = new Handler();
        final Timer timerNotificationWindow = new Timer();
        TimerTask doAsynchronousNotificationWindow = new TimerTask() {
            @Override
            public void run() {
                handlerNotoficationWindow.post(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    public void run() {
//                        try
//                        {
//
//
//
//
//
//                                switch (service_summary_status){
//
//                                    case "done":
//
//                                        if(service_gallery_status_check.equals("EMPTY") ){
//                                            //table is empty
//
//
//                                        }
//                                        else if(service_gallery_status_check.equals("NOTEMPTY") ){
//
//                                            // table is not empty
//
//                                        }
//
//                                        display_summary_status.setText("Finished");
//                                        image_service_summary.setImageDrawable(getResources().getDrawable(R.drawable.complete));
//
//                                        break;
//
//                                    case "not_done":
//
//                                        image_service_summary.setImageDrawable(getResources().getDrawable(R.drawable.not_complete));
//
//                                        break;
//
//                                }
//
//
//
//
//                                switch (service_gallery_status){
//
//                                    case "done":
//
//                                        image_service_gallery.setImageDrawable(getResources().getDrawable(R.drawable.complete));
//
//                                        break;
//
//                                    case "not_done":
//
//                                        image_service_gallery.setImageDrawable(getResources().getDrawable(R.drawable.not_complete));
//
//                                        break;
//
//                                }
//
//
//                        }
//                        catch (Exception e)
//                        {
//                            finish();
//                        }
                    }
                });
            }
        };

        timerNotificationWindow.schedule(doAsynchronousNotificationWindow, 0, 1000); //execute in every 10 minutes




        btnLinServiceSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    if (CheckNetworkStatus.isNetworkStatusAvialable(getBaseContext())) {

//                        if (CheckLocation.isGPSEnabled(getApplicationContext())) {

                            Intent intent   = new Intent(getBaseContext(), MainActivity_Service_Summary.class);
                            intent.putExtra("user_id",user_id_global);
                            startActivity(intent);

//                        } else {
//                            Toast.makeText(getApplicationContext(), "Location is not enabled,Please turn on location", Toast.LENGTH_LONG).show();
//                            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//                            builder.setTitle("network not enable")
//                                    .setMessage("Open location setting")
//                                    .setPositiveButton("Yes",
//                                            new DialogInterface.OnClickListener() {
//                                                public void onClick(DialogInterface dialog, int id) {
//                                                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
//                                                }
//                                            })
//                                    .setNegativeButton("Cancel",
//                                            new DialogInterface.OnClickListener() {
//                                                public void onClick(DialogInterface dialog, int id) {
//                                                    dialog.cancel();
//                                                }
//                                            });
//                            AlertDialog alert = builder.create();
//                            alert.show();
//                        }

                    } else {

                        Intent intent = new Intent(ServiceMain.this, AlertNoInternet.class);
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

//                        if (CheckLocation.isGPSEnabled(getApplicationContext())) {

                            Intent intent   = new Intent(getBaseContext(), MainActivity_Service_Gallery.class);
                            intent.putExtra("user_id",user_id_global);
                            startActivity(intent);

//                        } else {
//                            Toast.makeText(getApplicationContext(), "Location is not enabled,Please turn on location", Toast.LENGTH_LONG).show();
//                            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//                            builder.setTitle("network not enable")
//                                    .setMessage("Open location setting")
//                                    .setPositiveButton("Yes",
//                                            new DialogInterface.OnClickListener() {
//                                                public void onClick(DialogInterface dialog, int id) {
//                                                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
//                                                }
//                                            })
//                                    .setNegativeButton("Cancel",
//                                            new DialogInterface.OnClickListener() {
//                                                public void onClick(DialogInterface dialog, int id) {
//                                                    dialog.cancel();
//                                                }
//                                            });
//                            AlertDialog alert = builder.create();
//                            alert.show();
//                        }

                    } else {

                        Intent intent = new Intent(ServiceMain.this, AlertNoInternet.class);
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    Log.d("errorBtn_Go", e.toString());
                }

            }
        });





    }





    private void loadServiceSummary(Student student) {
        try {
            check_service_unit =student.getStudent_no();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"No unit", Toast.LENGTH_LONG).show();
        }

    }

    private void getServiceGallerys() {

        class GetServiceGallerys extends AsyncTask<Void, Void, List<Student>> {

            @Override
            protected List<Student> doInBackground(Void... voids) {
                List<Student> serviceList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .studentDao()
                        .getAll();

                if(serviceList.isEmpty() ){
                    //table is empty
                    service_gallery_status_check = "TEMPTY";
                }else{
                    // table is not empty
                    service_gallery_status_check = "NOTEMPTY";
                }
                return serviceList;
            }

            @Override
            protected void onPostExecute(List<Student> students) {
                super.onPostExecute(students);
//                ServiceSummaryAdapter adapter = new ServiceSummaryAdapter(MainActivity_Service_Summary.this, services);
//                recyclerView.setAdapter(adapter);
            }
        }

        GetServiceGallerys gt = new GetServiceGallerys();
        gt.execute();
    }


    private void deleteServiceSummary(final Student student) {
        class DeleteServiceSummary extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .studentDao()
                        .deleteService();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Service Deleted", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(ServiceMain.this, HomeActivity.class));
            }
        }

        DeleteServiceSummary dt = new DeleteServiceSummary();
        dt.execute();

    }


}