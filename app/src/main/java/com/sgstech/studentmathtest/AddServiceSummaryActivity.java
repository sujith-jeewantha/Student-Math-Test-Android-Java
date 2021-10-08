package com.sgstech.studentmathtest;

import static android.R.layout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sgstech.studentmathtest.Database.model.Student;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class AddServiceSummaryActivity extends AppCompatActivity {

    private Manager_Cache managerCacheAddServiceSummary;


    private String serviceRegion = "";
    private String service_site_id = "";
    private String service_site_name = "";
    private String service_team_name = "";




    private EditText editTextServiceUnitNo, editTextEnterODSN, editTextEnterIDSN, editTextEnterServiceSummaryRemarks, editTextEnterServiceSummaryMaterialUsage;



    ProgressDialog progressDialog;



    /**
     * Camera features init over
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service_summary);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);




        editTextServiceUnitNo = findViewById(R.id.service_summary_unit_no);
        editTextEnterODSN = findViewById(R.id.enter_od_sn);
        editTextEnterIDSN = findViewById(R.id.enter_id_sn);
        editTextEnterServiceSummaryRemarks = findViewById(R.id.enterServiceSummaryRemarks);
        editTextEnterServiceSummaryMaterialUsage = findViewById(R.id.enterServiceSummaryMaterialUsage);









        findViewById(R.id.button_save_service_summary).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                serviceRegion       = managerCacheAddServiceSummary.getServiceRegionName();
//                service_site_id     = managerCacheAddServiceSummary.getServiceSiteID();
//                service_site_name   = managerCacheAddServiceSummary.getServiceSiteName();
//                service_team_name   = managerCacheAddServiceSummary.getServiceTeamName();
                saveServiceSummary();
            }
        });
    }



    private void saveServiceSummary() {

        final String sRegion = serviceRegion;
        final String sSiteId = service_site_id;
        final String sSiteName = service_site_name;
        final String sTeamName = service_team_name;
        final String sUnitNo = editTextServiceUnitNo.getText().toString().trim();
        final String sSummaryODSN = editTextEnterODSN.getText().toString().trim();
        final String sSummaryIDSN = editTextEnterIDSN.getText().toString().trim();
        final String sSummaryRemarks = editTextEnterServiceSummaryRemarks.getText().toString().trim();
        final String sSummaryMaterialUsage = editTextEnterServiceSummaryMaterialUsage.getText().toString().trim();


        try {
            if (sUnitNo.isEmpty()) {
                editTextServiceUnitNo.setError("Unit No required");
                editTextServiceUnitNo.requestFocus();
                return;

//                if(email_global.equals("") || password_global.equals("")){
//
//                    Toast.makeText(getBaseContext(),"Please Fill the blanks to Continue!",Toast.LENGTH_SHORT).show();
//
//                }
            }
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Unit No required", Toast.LENGTH_LONG).show();
        }



        class SaveServiceSummary extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                Student student = new Student();
                student.setServiceRegion(sRegion);
                student.setServiceSiteId(sSiteId);
                student.setServiceSiteName(sSiteName);
                student.setServiceTeamName(sTeamName);
                student.setUnitNo(sUnitNo);
                student.setODSN(sSummaryODSN);
                student.setIDSN(sSummaryIDSN);
                student.setSummaryRemark(sSummaryRemarks);
                student.setSummaryMaterialUsage(sSummaryMaterialUsage);


                student.setFinished(false);

                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .studentDao()
                        .insert(student);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity_Service_Summary.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveServiceSummary ssg = new SaveServiceSummary();
        ssg.execute();
    }








    public String getCurrentTimeStamp()
    {
        Long tsLong     = System.currentTimeMillis()/1000;
        String ts       = tsLong.toString();
        return ts;
    }


}
