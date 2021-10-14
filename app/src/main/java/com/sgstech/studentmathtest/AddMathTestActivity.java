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

    private String strAnswer, strTxtAnswer;
    private int intResult;

    private int intTimeSpend = 0;
    private int intTimerProgress;
    private float floatTimeToSolve;

    private String student_no_global = "";

    int rStudentScore = 0;

    ProgressDialog progressDialog;

    LinearLayout layout;

    TextView
            txtStudentScore,
            txtQuestions ,
            txtResult ,
            txtOptions ,
            txtTimeToSolve;

    String currentTime, strQuestion, strResult, strTimeToSolve;

    EditText etAnswer;

    Button btnButtonList, btnEndTest , btnSaveMathTest;

    private EditText
            editTextFirstName,
            editTextLastName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_math_test);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        txtStudentScore = findViewById(R.id.enterAnswer);
        editTextFirstName = findViewById(R.id.enter_student_first_name);
        editTextLastName = findViewById(R.id.enter_student_last_name);

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        currentTime = df.format(Calendar.getInstance().getTime());


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
                    Toast.makeText(getApplicationContext(),"Something wrong", Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
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

        //making the progressbar visible
//        progressBar.setVisibility(View.VISIBLE);

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("res", response);

//                        progressBar.setVisibility(View.INVISIBLE);


                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            String strQuestion = obj.getString("question");
                            strResult = obj.getString("result");
                            String strTimeToSolve = obj.getString("timetosolve");

                            Log.d("res_s", strQuestion);
                            Log.d("res_s", strResult);
                            Log.d("res_s", strTimeToSolve);

                            txtQuestions.setText(strQuestion);
//                            txtResult.setText(strResult);

                            intTimerProgress = Integer.parseInt(strTimeToSolve);
                            Log.d("res_p", String.valueOf(intTimerProgress));
                            floatTimeToSolve = Float.parseFloat(strTimeToSolve);
                            Log.d("res_p", String.valueOf(floatTimeToSolve));

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

                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            JSONArray heroArray = obj.getJSONArray("options");

                            ArrayList<Object> listdata = new ArrayList<Object>();

                            //Checking whether the JSON array has some value or not
                            if (heroArray != null) {

                                //Iterating JSON array
                                for (int i=0;i<heroArray.length();i++){

                                    //Adding each element of JSON array into ArrayList
                                    listdata.add(heroArray.get(i));
                                }
                            }
                            //Iterating ArrayList to print each element

                            System.out.println("Each element of ArrayList");
                            for(int i=0; i<listdata.size(); i++) {
                                //Printing each element of ArrayList
                                System.out.println(listdata.get(i));
                                Log.d("res_arr", String.valueOf(listdata.get(i)));
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
//                                            saveStudentProfile();
                                        }
                                    });
                                    layout.addView(btn);

                                }

                            }

                         /**
                          * Button Generate-------------------------------------------------
                          */


                            //now looping through all the elements of the json array
                            for (int i = 0; i < heroArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject heroObject = heroArray.getJSONObject(i);

                                Log.d("res_sro", String.valueOf(heroObject));

                                //creating a hero object and giving them the values from json object
//                                Hero hero = new Hero(heroObject.getString("name"), heroObject.getString("imageurl"));

                                //adding the hero to herolist
//                                heroList.add(hero);
                            }



                            //creating custom adapter object
//                            ListViewAdapter adapter = new ListViewAdapter(heroList, getApplicationContext());

                            //adding the adapter to listview
//                            listView.setAdapter(adapter);

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
                        Log.d("res", error.getMessage());
                    }
                });

        progressDialog.cancel();

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }

    private void saveStudentProfile() {




         //String stuScore =  txtStudentScore.getText().toString().trim();
//        sStudentScore = Integer.parseInt(stuScore);

        String sStudentScore = String.valueOf(rStudentScore);
        String sTimerProgress = String.valueOf(intTimeSpend);


//        try {
//            if (sStudentScore < 0) {
//                txtStudentScore.setError("test No required");
//                txtStudentScore.requestFocus();
//                return;
//
//            }
//
//
//
//        }catch (Exception e)
//        {
//            Toast.makeText(getApplicationContext(),"Student No required", Toast.LENGTH_LONG).show();
//        }



        class SaveStudentProfile extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                MathTest mathTest = new MathTest();

                mathTest.setScores(String.valueOf(sStudentScore));
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
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveStudentProfile ssg = new SaveStudentProfile();
        ssg.execute();
    }

    private void loadAnotherTaskStudentProfile() {



        //String stuScore =  txtStudentScore.getText().toString().trim();
//        sStudentScore = Integer.parseInt(stuScore);

        String sStudentScore = String.valueOf(rStudentScore);
        String sTimerProgress = String.valueOf(intTimeSpend);


//        try {
//            if (sStudentScore < 0) {
//                txtStudentScore.setError("test No required");
//                txtStudentScore.requestFocus();
//                return;
//
//            }
//
//
//
//        }catch (Exception e)
//        {
//            Toast.makeText(getApplicationContext(),"Student No required", Toast.LENGTH_LONG).show();
//        }



        class loadAnotherTaskStudentProfile extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                MathTest mathTest = new MathTest();

                mathTest.setScores(String.valueOf(sStudentScore));
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
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        loadAnotherTaskStudentProfile ssg = new loadAnotherTaskStudentProfile();
        ssg.execute();
    }

}
