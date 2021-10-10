package com.sgstech.studentmathtest;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.sgstech.studentmathtest.Database.model.Student;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class UpdateStudentMathTestActivity extends AppCompatActivity implements View.OnClickListener {


    private Manager_Cache managerCacheUpdateServiceGallery;

    private String serviceRegion = "";
    private String service_site_id = "";
    private String service_site_name = "";
    private String service_team_global = "";

    private String service_unit_no_global = "";

    private TextView txtServiceUnitNo ;
    private CheckBox checkBoxFinished;

    /**
     * cmara freatures init
     */

    private ImageView ivSChecListImg, ivSBefore1Img1, ivSBefore1Img2, ivSBefore1Img3, ivSBefore1Img4 , ivSBefore1Img5,ivSBefore2Img1, ivSBefore2Img2, ivSBefore2Img3, ivSBefore2Img4, ivSBefore2Img5, ivSDuring1Img1, ivSDuring1Img2, ivSDuring1Img3, ivSDuring1Img4, ivSDuring1Img5, ivSDuring2Img1, ivSDuring2Img2, ivSDuring2Img3, ivSDuring2Img4, ivSDuring2Img5, ivSDuring3Img1, ivSDuring3Img2, ivSDuring3Img3, ivSDuring3Img4, ivSDuring3Img5, ivSAfter1Img1, ivSAfter1Img2, ivSAfter1Img3, ivSAfter1Img4, ivSAfter1Img5, ivSAfter2Img1, ivSAfter2Img2, ivSAfter2Img3, ivSAfter2Img4, ivSAfter2Img5;


    String region_dir ,service_breakdown_dir,date_dir,site_id_dir, site_name_dir,unit_dir, stage_dir,dateDir, datePrint, team, company;


    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student_math_test);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        managerCacheUpdateServiceGallery = Manager_Cache.getPreferences(this);

        final Student student = (Student) getIntent().getSerializableExtra("studentMathTest");


        txtServiceUnitNo = findViewById(R.id.service_gallery_unit_no);
        checkBoxFinished = findViewById(R.id.checkBoxFinished);


        loadServiceGallery(student);




        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//        ivSChecListImg          = findViewById(R.id.ivImgSChecklistU);




//        ivSChecListImg.setOnClickListener( this);






        findViewById(R.id.button_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(GLOBAL_IMAGE_NO.isEmpty()) {
//                    Toast.makeText(getApplicationContext(), "Please capture images", Toast.LENGTH_LONG).show();
//                }
//                else {
//                    updateServiceGallery(student);
//                }
            }
        });

//
    }

//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        final Service service = (Service) getIntent().getSerializableExtra("serviceGallery");
//        loadServiceGallery(service);
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            /**
             * Checklist image
             */
//            case R.id.ivImgSChecklistU:
//
//                GLOBAL_IMAGE_NO = "C" ;
//                if (CameraUtils.checkPermissions(getApplicationContext())) {
//                    captureImage();
//                } else {
//                    requestCameraPermission(MEDIA_TYPE_IMAGE);
//                }
//                break;

        }
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(UpdateStudentMathTestActivity.this, MainActivity_Student_Math_Test.class);
        startActivity(intent);
    }

    private void loadServiceGallery(Student student) {

        txtServiceUnitNo.setText(student.getStudent_no());

        service_unit_no_global = student.getStudent_no();


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


//    private void deleteServiceGallery(final Student service) {
//        class DeleteServiceGallery extends AsyncTask<Void, Void, Void> {
//
//            @Override
//            protected Void doInBackground(Void... voids) {
//                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
//                        .studentDao()
//                        .delete(student);
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void aVoid) {
//                super.onPostExecute(aVoid);
//                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
//                finish();
//                startActivity(new Intent(UpdateServiceGalleryActivity.this, MainActivity_Service_Gallery.class));
//            }
//        }
//
//        DeleteServiceGallery dt = new DeleteServiceGallery();
//        dt.execute();
//
//    }





    public static Bitmap RotateBitmap(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }





    public String getCurrentTimeStamp()
    {
        Long tsLong     = System.currentTimeMillis()/1000;
        String ts       = tsLong.toString();
        return ts;
    }




}
