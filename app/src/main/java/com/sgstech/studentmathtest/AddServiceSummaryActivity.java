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

        editTextStudentNo = findViewById(R.id.student_s_no);
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






        findViewById(R.id.button_save_service_summary).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveServiceSummary();
            }
        });
    }

    private void saveServiceSummary() {

         final String sStudentNo  =  editTextStudentNo.getText().toString().trim();
         final String sFirstName  =  editTextFirstName.getText().toString().trim();
         final String sLastName  =  editTextLastName .getText().toString().trim();

         final String sPhoneNoOne  =  editTextPhoneNoOne.getText().toString().trim();
         final String sPhoneNoTwo  =  editTextPhoneNoTwo.getText().toString().trim();
         final String sPhoneNoThree  =  editTextPhoneNoThree.getText().toString().trim();
         final String sPhoneNoFour  =  editTextPhoneNoFour.getText().toString().trim();
         final String sPhoneNoFive  =  editTextPhoneNoFive.getText().toString().trim();
         final String sPhoneNoSix  =  editTextPhoneNoSix.getText().toString().trim();
         final String sPhoneNoSeven  =  editTextPhoneNoSeven.getText().toString().trim();
         final String sPhoneNoEight  =  editTextPhoneNoEight.getText().toString().trim();
         final String sPhoneNoNine  =  editTextPhoneNoNine.getText().toString().trim();
         final String sPhoneNoTen  =  editTextPhoneNoTen.getText().toString().trim();

         final String sEmailOne  =  editTextEmailOne.getText().toString().trim();
         final String sEmailTwo  =  editTextEmailTwo.getText().toString().trim();
         final String sEmailThree  =  editTextEmailThree.getText().toString().trim();
         final String sEmailFour  =  editTextEmailFour.getText().toString().trim();
         final String sEmailFive  =  editTextEmailFive.getText().toString().trim();
         final String sEmailSix  =  editTextEmailSix.getText().toString().trim();
         final String sEmailSeven  =  editTextEmailSeven.getText().toString().trim();
         final String sEmailEight  =  editTextEmailEight.getText().toString().trim();
         final String sEmailNine  =  editTextEmailNine.getText().toString().trim();
         final String sEmailTen  =  editTextEmailTen.getText().toString().trim();



        try {
            if (sStudentNo.isEmpty()) {
                editTextStudentNo.setError("Student No required");
                editTextStudentNo.requestFocus();
                return;

            }
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Student No required", Toast.LENGTH_LONG).show();
        }



        class SaveServiceSummary extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                Student student = new Student();

                student.setStudent_no(sStudentNo);
                student.setStudent_first_name(sFirstName);
                student.setStudent_last_name(sLastName);

                student.setStudent_phone_one(sPhoneNoOne);
                student.setStudent_phone_two(sPhoneNoTwo);
                student.setStudent_phone_three(sPhoneNoThree);
                student.setStudent_phone_four(sPhoneNoFour);
                student.setStudent_phone_five(sPhoneNoFive);
                student.setStudent_phone_six(sPhoneNoSix);
                student.setStudent_phone_seven(sPhoneNoSeven);
                student.setStudent_phone_eight(sPhoneNoEight);
                student.setStudent_phone_nine(sPhoneNoNine);
                student.setStudent_phone_ten(sPhoneNoTen);

                student.setStudent_email_one(sEmailOne);
                student.setStudent_email_two(sEmailTwo);
                student.setStudent_email_three(sEmailThree);
                student.setStudent_email_four(sEmailFour);
                student.setStudent_email_five(sEmailFive);
                student.setStudent_email_six(sEmailSix);
                student.setStudent_email_seven(sEmailSeven);
                student.setStudent_email_eight(sEmailEight);
                student.setStudent_email_nine(sEmailNine);
                student.setStudent_email_ten(sEmailTen);

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
