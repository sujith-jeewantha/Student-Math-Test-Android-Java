package com.sgstech.studentmathtest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sgstech.studentmathtest.Database.model.MathTest;
import com.sgstech.studentmathtest.SharedPreferance.Manager_Cache;
import com.sgstech.studentmathtest.Utills.DatabaseClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class    AddMathTestActivity extends AppCompatActivity {

    private String math_question_api_url = new Manager_API().math_question_api_url;

    private Manager_Cache managerCacheStudentNo;

    private String strAnswer, strTxtAnswer, studentNO;

    private int intTimeSpend = 0;
    private int intTimerProgress;
    private float floatTimeToSolve;

    int rStudentScore = 0;

    ProgressDialog progressDialog;

    LinearLayout layout;

    TextView
            txtStudentScore,
            txtQuestions ,
            txtResult ,
            txtOptions ,
            txtTimeToSolve;

    String currentTime, strResult;

    EditText etAnswer;

    Button  btnEndTest , btnSaveMathTest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_math_test);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        txtStudentScore = findViewById(R.id.enterAnswer);

        managerCacheStudentNo = Manager_Cache.getPreferences(this);

        studentNO = getIntent().getStringExtra("add_student_no");
        saveCachedStudentNo();

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        currentTime = df.format(Calendar.getInstance().getTime());


        layout = (LinearLayout)findViewById(R.id.button_list);
        txtQuestions        = (TextView)findViewById(R.id.txtQuestion);
        txtResult           = (TextView)findViewById(R.id.txtResult);
        txtOptions          = (TextView)findViewById(R.id.txtOptions);
        txtTimeToSolve      = (TextView)findViewById(R.id.txtTimeToSolve);

        etAnswer = (EditText) findViewById(R.id.enterAnswer);

        btnSaveMathTest =   (Button) findViewById(R.id.button_save_math_test);
        btnEndTest          = (Button)findViewById(R.id.button_end_test);

        loadData();


        btnSaveMathTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    strAnswer =   etAnswer.getText().toString().trim();
                    strTxtAnswer = txtResult.getText().toString().trim();
                    getCachedStudentNo();
                    Toast.makeText(getApplicationContext(),"add_student_no" + studentNO, Toast.LENGTH_SHORT).show();

                    if((strAnswer.equals(strResult)) || (strTxtAnswer.equals(strResult)))
                    {
                        rStudentScore = 10;
                    }
                    else
                    {
                        rStudentScore = 0;
                    }

                    saveStudentProfile();

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Log.d("err_s", e.getMessage());
                    Toast.makeText(getApplicationContext(),"Something wrong", Toast.LENGTH_SHORT).show();
                    Intent intent       = new Intent(getBaseContext(), MainActivity_Math_Test_Sub.class);
                    startActivity(intent);

                }


            }
        });

        btnEndTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity_Math_Test_Sub.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onBackPressed() {
// super.onBackPressed();
// Not calling **super**, disables back button in current screen.
    }

    private void loadData() {

        String path = math_question_api_url;
        String url = path;

        progressDialog            = new ProgressDialog(AddMathTestActivity.this,ProgressDialog.THEME_HOLO_DARK);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Please wait moment...!");
        progressDialog.setMessage("Retrieve Data From Question server...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                onBackPressed();
                finish();

            }
        });
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("res", response);


                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            String strQuestion = obj.getString("question");
                            strResult = obj.getString("result");
                            String strTimeToSolve = obj.getString("timetosolve");

                            txtQuestions.setText(strQuestion);

                            intTimerProgress = Integer.parseInt(strTimeToSolve);
                            floatTimeToSolve = Float.parseFloat(strTimeToSolve);

                            int questionTime = intTimerProgress * 1000;

                            if (intTimerProgress > 0) {
                                new CountDownTimer(questionTime, 1000) {
                                    public void onTick(long millisUntilFinished) {

                                        txtTimeToSolve.setText(String.valueOf(intTimerProgress + " Seconds"));
                                        intTimerProgress--;

                                        intTimeSpend++;

                                    }

                                    public void onFinish() {
                                        txtTimeToSolve.setText("FINISH!!");
                                        loadAnotherTaskStudentProfile();
                                    }
                                }.start();
                            }

                            JSONArray heroArray = obj.getJSONArray("options");

                            ArrayList<Object> listdata = new ArrayList<Object>();

                            if (heroArray != null) {

                                for (int i=0;i<heroArray.length();i++){

                                    listdata.add(heroArray.get(i));
                                }
                            }




                        /**
                         * Button generate-------------------------------------------------
                         */

                            if(heroArray.length() <= 0)
                            {
                                etAnswer.setVisibility(View.VISIBLE);
                                btnSaveMathTest.setVisibility(View.GONE);
                                txtResult.setVisibility(View.GONE);
                            }
                            else
                            {
                                etAnswer.setVisibility(View.GONE);

                                txtResult.setVisibility(View.VISIBLE);


                                for(int i=0;i<heroArray.length();i++)
                                {

                                    listdata.add(heroArray.get(i));


                                    Button btn = new Button(AddMathTestActivity.this);
                                    LinearLayout.LayoutParams tr = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                    layout.setWeightSum(12.0f);
                                    layout.setOrientation(LinearLayout.VERTICAL);
                                    tr.weight = 0;
                                    btn.setLayoutParams(tr);
                                    btn.setHeight(150);

                                    btn.setWidth(150);
                                    btn.setText("Ans. : " + listdata.get(i));
                                    btn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            strAnswer = btn.getText().toString().toString();
                                            String fTXT =  strAnswer.replace("Ans. : ", "").trim();
                                            txtResult.setText(fTXT);
                                            Toast.makeText(AddMathTestActivity.this,fTXT, Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    layout.addView(btn);

                                }

                            }

                         /**
                          * Button Generate-------------------------------------------------
                          */



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        progressDialog.cancel();

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }

    private void saveCachedStudentNo() {
        managerCacheStudentNo.setCachedStudentNO(studentNO);
    }

    private void getCachedStudentNo() {
        studentNO = managerCacheStudentNo.getCachedStudentNO();
    }

    private void saveStudentProfile() {

        String sStudentScore = String.valueOf(rStudentScore);
        String sTimerProgress = String.valueOf(intTimeSpend);

        class SaveStudentProfile extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                MathTest mathTest = new MathTest();

                mathTest.setTest_student_no(studentNO);
                mathTest.setScores(sStudentScore);
                mathTest.setTime_of_beginning(currentTime);
                mathTest.setTotal_time_of_the_test(String.valueOf(sTimerProgress) + " Seconds");

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
                startActivity(new Intent(getApplicationContext(), AddMathTestActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
            }
        }

        SaveStudentProfile ssg = new SaveStudentProfile();
        ssg.execute();
    }

    private void loadAnotherTaskStudentProfile() {

        String sStudentScore = String.valueOf(rStudentScore);
        String sTimerProgress = String.valueOf(intTimeSpend);

        class loadAnotherTaskStudentProfile extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                MathTest mathTest = new MathTest();

                mathTest.setScores(sStudentScore);
                mathTest.setTime_of_beginning(currentTime);
                mathTest.setTotal_time_of_the_test(sTimerProgress + " Seconds");

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
                startActivity(new Intent(getApplicationContext(), AddMathTestActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
            }
        }

        loadAnotherTaskStudentProfile ssg = new loadAnotherTaskStudentProfile();
        ssg.execute();
    }

}
