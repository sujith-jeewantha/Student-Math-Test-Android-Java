package com.sgstech.studentmathtest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sgstech.studentmathtest.Database.model.Student;

public class UpdateServiceSummaryActivity extends AppCompatActivity {




    private String serviceRegion = "";
    private String service_site_id = "";
    private String service_site_name = "";
    private String service_team_name = "";

    Student studentCheckBox = new Student();


    String selectedCapacity = "";
    String selectedBrand ="";
    String selectedModel  = "";

    private EditText editTextServiceUnitNo, editTextEnterODSN, editTextEnterIDSN, editTextEnterServiceSummaryRemarks, editTextEnterServiceSummaryMaterialUsage;
    private CheckBox checkBoxFinished, chbNotVisible, chbServiceSummaryNoRemarks, chbServiceSummaryNoMaterialUsage;


    Button btnUploadImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_service_summary);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        editTextServiceUnitNo = findViewById(R.id.service_unit_no);
        editTextEnterODSN = findViewById(R.id.enter_od_sn);
        editTextEnterIDSN = findViewById(R.id.enter_id_sn);
        editTextEnterServiceSummaryRemarks = findViewById(R.id.enterServiceSummaryRemarks);
        editTextEnterServiceSummaryMaterialUsage = findViewById(R.id.enterServiceSummaryMaterialUsage);

        checkBoxFinished = findViewById(R.id.checkBoxFinished);




        final Student student = (Student) getIntent().getSerializableExtra("studentProfile");

        loadServiceSummary(student);

        editTextEnterODSN = findViewById(R.id.enter_od_sn);






        ////////////////////////////////////////////////////////////////////////////////////////////////

        /**
         *  Camera init over
         */

        findViewById(R.id.button_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                serviceRegion       = managerCacheUpdateServiceSummary.getServiceRegionName();
//                service_site_id     = managerCacheUpdateServiceSummary.getServiceSiteID();
//                service_site_name   = managerCacheUpdateServiceSummary.getServiceSiteName();
//                service_team_name   = managerCacheUpdateServiceSummary.getServiceTeamName();
                updateServiceSummary(student);
            }
        });

        findViewById(R.id.button_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateServiceSummaryActivity.this);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteServiceSummary(student);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog ad = builder.create();
                ad.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(UpdateServiceSummaryActivity.this, MainActivity_Service_Summary.class);
        startActivity(intent);
    }



    private void loadServiceSummary(Student student) {
        editTextServiceUnitNo.setText(student.getUnitNo());
//        String [] capacityData = {service.getCapacity().toString()};
//        String index                            = capacityData[0];
//        capacitySpinner.setSelection(Integer.parseInt(service.getCapacity(Integer.parseInt(String.valueOf(0)))));
//        brandSpinner.setSelection(adapterBrand.getPosition(service.getBrand()));
//        modelSpinner.setSelection(adapterModel.getPosition(service.getModel()));

        editTextEnterIDSN.setText(student.getIDSN());

        String serviceSummaryODSN = student.getODSN();

        if (serviceSummaryODSN.equals("Not Visible"))
        {
            editTextEnterODSN.setText(serviceSummaryODSN);
            editTextEnterODSN.setEnabled(false);
        }
        else
        {
            editTextEnterODSN.setText(serviceSummaryODSN);
            editTextEnterODSN.setEnabled(true);
        }

        String serviceSummaryRemark = student.getSummaryRemark();


        if (serviceSummaryRemark.equals("No Issue Found in Unit"))
        {
            editTextEnterServiceSummaryRemarks.setText(serviceSummaryRemark);
            editTextEnterServiceSummaryRemarks.setEnabled(false);
        }
        else
        {
            editTextEnterServiceSummaryRemarks.setText(serviceSummaryRemark);
            editTextEnterServiceSummaryRemarks.setEnabled(true);
        }

        String serviceSummaryMaterialUsage = student.getSummaryMaterialUsage();


        if (serviceSummaryMaterialUsage.equals("No Material Usage in Unit"))
        {
            editTextEnterServiceSummaryMaterialUsage.setText(serviceSummaryMaterialUsage);
//            chbServiceSummaryNoMaterialUsage.setChecked(true);
            editTextEnterServiceSummaryMaterialUsage.setEnabled(false);
        }
        else
        {
            editTextEnterServiceSummaryMaterialUsage.setText(serviceSummaryMaterialUsage);
            editTextEnterServiceSummaryMaterialUsage.setEnabled(true);
        }

        checkBoxFinished.setChecked(student.isFinished());
    }

    private void updateServiceSummary(final Student student) {

        final String sRegion = serviceRegion;
        final String sSiteId = service_site_id;
        final String sSiteName = service_site_name;
        final String sTeamName = service_team_name;
        final String sUnitNo = editTextServiceUnitNo.getText().toString().trim();
        final String sCapacity = selectedCapacity;
        final String sBrand = selectedBrand;
        final String sModel  = selectedModel;
        final String sSummaryODSN = editTextEnterODSN.getText().toString().trim();
        final String sSummaryIDSN = editTextEnterIDSN.getText().toString().trim();
        final String sSummaryRemarks = editTextEnterServiceSummaryRemarks.getText().toString().trim();
        final String sSummaryMaterialUsage = editTextEnterServiceSummaryMaterialUsage.getText().toString().trim();

        try {
            if (sUnitNo.isEmpty()) {
                editTextServiceUnitNo.setError("Unit No required");
                editTextServiceUnitNo.requestFocus();
                return;
            }
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Unit No required", Toast.LENGTH_LONG).show();
        }


        class UpdateServiceSummary extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                student.setServiceRegion(sRegion);
                student.setServiceSiteId(sSiteId);
                student.setServiceSiteName(sSiteName);
                student.setServiceTeamName(sTeamName);
                student.setUnitNo(sUnitNo);
                student.setODSN(sSummaryODSN);
                student.setIDSN(sSummaryIDSN);
                student.setSummaryRemark(sSummaryRemarks);
                student.setSummaryMaterialUsage(sSummaryMaterialUsage);

                student.setFinished(checkBoxFinished.isChecked());
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .studentDao()
                        .update(student);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateServiceSummaryActivity.this, MainActivity_Service_Summary.class));
            }
        }

        UpdateServiceSummary ut = new UpdateServiceSummary();
        ut.execute();
    }


    private void deleteServiceSummary(final Student student) {
        class DeleteServiceSummary extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .studentDao()
                        .delete(student);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateServiceSummaryActivity.this, MainActivity_Service_Summary.class));
            }
        }

        DeleteServiceSummary dt = new DeleteServiceSummary();
        dt.execute();

    }


    public String getCurrentTimeStamp()
    {
        Long tsLong     = System.currentTimeMillis()/1000;
        String ts       = tsLong.toString();
        return ts;
    }





}
