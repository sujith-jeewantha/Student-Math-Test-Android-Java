package com.sgstech.studentmathtest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

    private EditText
            editTextStudentNo,
            editTextFirstName,
            editTextLastName,

            editTextPhoneNoOne,
            editTextPhoneNoTwo,
            editTextPhoneNoThree,
            editTextPhoneNoFour,
            editTextPhoneNoFive,
            editTextPhoneNoSix,
            editTextPhoneNoSeven,
            editTextPhoneNoEight,
            editTextPhoneNoNine,
            editTextPhoneNoTen,

            editTextEmailOne,
            editTextEmailTwo,
            editTextEmailThree,
            editTextEmailFour,
            editTextEmailFive,
            editTextEmailSix,
            editTextEmailSeven,
            editTextEmailEight,
            editTextEmailNine,
            editTextEmailTen;

    Button btnUploadImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        editTextStudentNo = findViewById(R.id.student_u_no);
        editTextFirstName = findViewById(R.id.enter_student_first_name);
        editTextLastName = findViewById(R.id.enter_student_last_name);

        editTextPhoneNoOne = findViewById(R.id.entertpNo_one);
        editTextPhoneNoTwo = findViewById(R.id.entertpNo_two);
        editTextPhoneNoThree = findViewById(R.id.entertpNo_three);
        editTextPhoneNoFour = findViewById(R.id.entertpNo_four);
        editTextPhoneNoFive = findViewById(R.id.entertpNo_five);
        editTextPhoneNoSix = findViewById(R.id.entertpNo_six);
        editTextPhoneNoSeven = findViewById(R.id.entertpNo_seven);
        editTextPhoneNoEight = findViewById(R.id.entertpNo_eight);
        editTextPhoneNoNine = findViewById(R.id.entertpNo_nine);
        editTextPhoneNoTen = findViewById(R.id.entertpNo_ten);

        editTextEmailOne = findViewById(R.id.enterEmail_one);
        editTextEmailTwo = findViewById(R.id.enterEmail_two);
        editTextEmailThree = findViewById(R.id.enterEmail_three);
        editTextEmailFour = findViewById(R.id.enterEmail_four);
        editTextEmailFive = findViewById(R.id.enterEmail_five);
        editTextEmailSix = findViewById(R.id.enterEmail_six);
        editTextEmailSeven = findViewById(R.id.enterEmail_seven);
        editTextEmailEight = findViewById(R.id.enterEmail_eight);
        editTextEmailNine = findViewById(R.id.enterEmail_nine);
        editTextEmailTen = findViewById(R.id.enterEmail_ten);

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
        editTextServiceUnitNo.setText(student.getStudent_no());
//        String [] capacityData = {service.getCapacity().toString()};
//        String index                            = capacityData[0];
//        capacitySpinner.setSelection(Integer.parseInt(service.getCapacity(Integer.parseInt(String.valueOf(0)))));
//        brandSpinner.setSelection(adapterBrand.getPosition(service.getBrand()));
//        modelSpinner.setSelection(adapterModel.getPosition(service.getModel()));

//        editTextEnterIDSN.setText(student.getIDSN());
//
//        String serviceSummaryODSN = student.getODSN();

//        if (serviceSummaryODSN.equals("Not Visible"))
//        {
//            editTextEnterODSN.setText(serviceSummaryODSN);
//            editTextEnterODSN.setEnabled(false);
//        }
//        else
//        {
//            editTextEnterODSN.setText(serviceSummaryODSN);
//            editTextEnterODSN.setEnabled(true);
//        }
//
//        String serviceSummaryRemark = student.getSummaryRemark();
//
//
//        if (serviceSummaryRemark.equals("No Issue Found in Unit"))
//        {
//            editTextEnterServiceSummaryRemarks.setText(serviceSummaryRemark);
//            editTextEnterServiceSummaryRemarks.setEnabled(false);
//        }
//        else
//        {
//            editTextEnterServiceSummaryRemarks.setText(serviceSummaryRemark);
//            editTextEnterServiceSummaryRemarks.setEnabled(true);
//        }

//        String serviceSummaryMaterialUsage = student.getSummaryMaterialUsage();


//        if (serviceSummaryMaterialUsage.equals("No Material Usage in Unit"))
//        {
//            editTextEnterServiceSummaryMaterialUsage.setText(serviceSummaryMaterialUsage);
////            chbServiceSummaryNoMaterialUsage.setChecked(true);
//            editTextEnterServiceSummaryMaterialUsage.setEnabled(false);
//        }
//        else
//        {
//            editTextEnterServiceSummaryMaterialUsage.setText(serviceSummaryMaterialUsage);
//            editTextEnterServiceSummaryMaterialUsage.setEnabled(true);
//        }

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

                student.setStudent_no(sRegion);
                student.setStudent_first_name(sSiteId);
                student.setStudent_last_name(sSiteName);
                student.setStudent_phone_one(sTeamName);
                student.setStudent_phone_two(sTeamName);
                student.setStudent_phone_three(sTeamName);
                student.setStudent_phone_four(sTeamName);
                student.setStudent_phone_five(sTeamName);
                student.setStudent_phone_six(sTeamName);
                student.setStudent_phone_seven(sTeamName);
                student.setStudent_phone_eight(sTeamName);
                student.setStudent_phone_nine(sTeamName);
                student.setStudent_phone_ten(sTeamName);
                student.setStudent_email_one(sTeamName);
                student.setStudent_email_two(sTeamName);
                student.setStudent_email_three(sTeamName);
                student.setStudent_email_four(sTeamName);
                student.setStudent_email_five(sTeamName);
                student.setStudent_email_six(sTeamName);
                student.setStudent_email_seven(sTeamName);
                student.setStudent_email_eight(sTeamName);
                student.setStudent_email_nine(sTeamName);
                student.setStudent_email_ten(sTeamName);

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
