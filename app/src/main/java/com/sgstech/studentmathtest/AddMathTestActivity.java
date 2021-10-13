package com.sgstech.studentmathtest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.sgstech.studentmathtest.Database.model.MathTest;


public class AddMathTestActivity extends AppCompatActivity {


    private EditText
            editTextStudentNo,
            editTextFirstName,
            editTextLastName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_math_test);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        editTextStudentNo = findViewById(R.id.student_s_no);
        editTextFirstName = findViewById(R.id.enter_student_first_name);
        editTextLastName = findViewById(R.id.enter_student_last_name);



        findViewById(R.id.button_save_student_profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
//                    if(global_Img.isEmpty())
//                    {
//                        Toast.makeText(getApplicationContext(),"Student Image required", Toast.LENGTH_LONG).show();
//                    }
//                    else
//                    {
                        saveStudentProfile();
//                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Student Image required", Toast.LENGTH_LONG).show();
                    Intent intent       = new Intent(getBaseContext(), AddStudentProfileActivity.class);
                    startActivity(intent);

                }


            }
        });


    }




    private void saveStudentProfile() {



         final String sStudentNo  =  editTextStudentNo.getText().toString().trim();
         final String sFirstName  =  editTextFirstName.getText().toString().trim();
         final String sLastName  =  editTextLastName .getText().toString().trim();





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



        class SaveStudentProfile extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                MathTest mathTest = new MathTest();

                mathTest.setScores(sStudentNo);
                mathTest.setTime_of_beginning(sFirstName);
                mathTest.setTotal_time_of_the_test(sLastName);

                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .mathTestDao()
                        .insert(mathTest);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity_Math_Test_Sub.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveStudentProfile ssg = new SaveStudentProfile();
        ssg.execute();
    }




    public String getCurrentTimeStamp()
    {
        Long tsLong     = System.currentTimeMillis()/1000;
        String ts       = tsLong.toString();
        return ts;
    }


}
