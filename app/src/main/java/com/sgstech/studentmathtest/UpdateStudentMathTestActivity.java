package com.sgstech.studentmathtest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.sgstech.studentmathtest.Database.model.Student;
import com.sgstech.studentmathtest.ProgressBar.FloatTextProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;


public class UpdateStudentMathTestActivity extends AppCompatActivity {


    private String math_question_api_url = new Manager_API().math_question_api_url;

    private int intAnswer ;
    private int intResult;

    private int intTimerProgress;
    private float floatTimeToSolve;

    private String student_no_global = "";

    private TextView txtStudentNo;
    private CheckBox checkBoxFinished;

    ProgressDialog  progressDialog;

    TextView    txtQuestionsQty,
                txtQuestions ,
                txtResult ,
                txtOptions ,
                txtTimeToSolve;


    EditText    etAnswer;

    Button btnSFinalSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student_math_test);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        final Student student = (Student) getIntent().getSerializableExtra("studentMathTest");


        progressDialog            = new ProgressDialog(UpdateStudentMathTestActivity.this,ProgressDialog.THEME_HOLO_DARK);
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


        txtQuestionsQty     = (TextView)findViewById(R.id.txtQuestionQty);
        txtQuestions        = (TextView)findViewById(R.id.txtQuestion);
        txtResult           = (TextView)findViewById(R.id.txtResult);
        txtOptions          = (TextView)findViewById(R.id.txtOptions);
        txtTimeToSolve      = (TextView)findViewById(R.id.txtTimeToSolve);

        etAnswer = (EditText) findViewById(R.id.enterAnswer);

        btnSFinalSubmit          = (Button)findViewById(R.id.btn_submit_ans);

        getData();


        txtStudentNo = findViewById(R.id.service_gallery_unit_no);
        checkBoxFinished = findViewById(R.id.checkBoxFinished);


        loadServiceGallery(student);


//        findViewById(R.id.button_update).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                if(GLOBAL_IMAGE_NO.isEmpty()) {
////                    Toast.makeText(getApplicationContext(), "Please capture images", Toast.LENGTH_LONG).show();
////                }
////                else {
////                    updateServiceGallery(student);
////                }
//            }
//        });

//
    }



    private void loadServiceGallery(Student student) {

        txtStudentNo.setText(student.getStudent_no());

        student_no_global = student.getStudent_no();


    }


    private void getData() {

        String path         = math_question_api_url;
        String url = path;
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(UpdateStudentMathTestActivity.this);

        // of array so we are making a json array request.
        // request and then extracting data from each json object.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                Log.d("res_s", String.valueOf(response));
                Log.d("res_s", String.valueOf(response.length()));

                int questionQty =response.length();

                txtQuestionsQty.setText(String.valueOf(questionQty));

                int questionArray[]=new int[questionQty];


                    try {


//                        for (int i = 0; i < questionQty; i++) {

                        // we are getting each json object.
                        JSONObject responseObj = response.getJSONObject(questionArray[0]);

                        String strQuestion = responseObj.getString("question");
                        String strResult = responseObj.getString("result");
                        String strTimeToSolve = responseObj.getString("timetosolve");


//                        String strOptions = responseObj.getString("options");

//                        JSONArray array = (JSONArray)responseObj.get(strOptions);

                        JSONArray jsonarray = responseObj.getJSONArray("options");

                        Log.d("res_s", String.valueOf(jsonarray));

                        for (int j = 0; j < jsonarray.length(); j++) {

                            Log.d("res_s", String.valueOf(jsonarray.length()));
//                            JSONObject jsonobject = jsonarray.getJSONObject(i);
//                            Toast.makeText(UpdateStudentMathTestActivity.this, (CharSequence) jsonobject, Toast.LENGTH_SHORT).show();


                        }

                        progressDialog.cancel();

                        /**
                         * Progress Bar-------------------------------------------------------------
                         */

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

                                }

                                public void onFinish() {
                                    txtTimeToSolve.setText("FINISH!!");
                                }
                            }.start();
                        }



//
//                        for (int i = 0; i < array.length(); i++) {
//
//                            JSONObject childObject = array.getJSONObject(i);
//
//                            String id = childObject.getString("id");
//
//
//                        }

//                        JSONObject responseSubObj = response.getJSONObject(Integer.parseInt(strOptions));
//
//                        String strInnerOptions = responseSubObj.getString("options");
//
//                        Toast.makeText(UpdateStudentMathTestActivity.this, strInnerOptions, Toast.LENGTH_SHORT).show();


                        txtQuestions.setText(strQuestion);
                        txtResult.setText(strResult);
//                        txtOptions.setText(strOptions);
//                        txtTimeToSolve.setText(strTimeToSolve + " seconds");

//                        String value = etAnswer.getText().toString();
//                        intAnswer=Integer.parseInt(value);
//
//                        intResult = Integer.parseInt(strResult);

//                        if( intAnswer == intResult)
//                        {
//                            Toast.makeText(UpdateStudentMathTestActivity.this, "Correct Answer..", Toast.LENGTH_SHORT).show();
//                        }
//                        else
//                        {
//                            Toast.makeText(UpdateStudentMathTestActivity.this, "Wrong Answer..", Toast.LENGTH_SHORT).show();
//                        }

                        progressDialog.cancel();

//                    }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UpdateStudentMathTestActivity.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonArrayRequest);
    }





    private void updateServiceGallery(final Student student) {



        class UpdateServiceGallery extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                /**
                 * Profile image
                 */
//                if(GLOBAL_IMAGE_NO == "C" ) {
//                    student.setServiceChecklistImg(global_Img);
//                }



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
                Intent intent = new Intent(UpdateStudentMathTestActivity.this, MainActivity_Student_Math_Test.class);
//                intent.putExtra("image_path"       ,image_file_global);
                startActivity(intent);
            }
        }

        UpdateServiceGallery ut = new UpdateServiceGallery();
        ut.execute();
    }








}

