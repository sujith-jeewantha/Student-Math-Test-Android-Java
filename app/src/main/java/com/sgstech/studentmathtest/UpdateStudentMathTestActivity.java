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
    File image_file_global = null;

    public String GLOBAL_IMAGE_NO = "";

    // Activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;

    // key to store image path in savedInstance state
    public static final String KEY_IMAGE_STORAGE_PATH = "image_path";

    public static final int MEDIA_TYPE_IMAGE = 1;

    // Bitmap sampling size
    public static final int BITMAP_SAMPLE_SIZE = 2;

    // Gallery directory name to store the images or videos
    public static final String SERVICE_GALLERY_DIRECTORY_NAME = "STUDENT-MATH/PROFILE";

    // Image and Video file extensions
    public static final String IMAGE_EXTENSION = "jpg";

    private static String imageStoragePath;


    /**
     * Global image
     */
    public String global_Img;




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


//        serviceRegion       = managerCacheUpdateServiceGallery.getServiceRegionName();
//        service_site_id     = managerCacheUpdateServiceGallery.getServiceSiteID();
//        service_site_name   = managerCacheUpdateServiceGallery.getServiceSiteName();
//        service_team_global = managerCacheUpdateServiceGallery.getServiceTeamName();


        /**
         * Camera init
         */

        region_dir  = serviceRegion;
        service_breakdown_dir= "Breakdown";
        team = service_team_global;
        company = "Browns Engineering & Construction";
        date_dir = dateDir = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm", Locale.getDefault()).format(new Date());;
        site_id_dir = service_site_id;
        site_name_dir = service_site_name;
        unit_dir  = service_unit_no_global;
        stage_dir = "stage";
        datePrint = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss", Locale.getDefault()).format(new Date());;

        // Checking availability of the camera
        if (!CameraUtils.isDeviceSupportCamera(getApplicationContext())) {
            Toast.makeText(getApplicationContext(),
                    "Sorry! Your device doesn't support camera",
                    Toast.LENGTH_LONG).show();
            // will close the app if the device doesn't have camera
            finish();
        }

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ivSChecListImg          = findViewById(R.id.ivImgSChecklistU);




        ivSChecListImg.setOnClickListener( this);







        /**
         *  Camera init over
         */

        findViewById(R.id.button_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(GLOBAL_IMAGE_NO.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please capture images", Toast.LENGTH_LONG).show();
                }
                else {
                    updateServiceGallery(student);
                }
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
            case R.id.ivImgSChecklistU:

                GLOBAL_IMAGE_NO = "C" ;
                if (CameraUtils.checkPermissions(getApplicationContext())) {
                    captureImage();
                } else {
                    requestCameraPermission(MEDIA_TYPE_IMAGE);
                }
                break;

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
                if(GLOBAL_IMAGE_NO == "C" ) {
                    student.setServiceChecklistImg(global_Img);
                }



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
                intent.putExtra("image_path"       ,image_file_global);
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

    /**
     * Camera init method start -------------------------------------------------------------------------------------------------------
     */

    /**
     * Restoring store image path from saved instance state
     */
    private void restoreFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(KEY_IMAGE_STORAGE_PATH)) {
                imageStoragePath = savedInstanceState.getString(KEY_IMAGE_STORAGE_PATH);
                if (!TextUtils.isEmpty(imageStoragePath)) {
                    if (imageStoragePath.substring(imageStoragePath.lastIndexOf(".")).equals("." + IMAGE_EXTENSION)) {
                        previewCapturedImage();
                    }
                }
            }
        }
    }

    /**
     * Requesting permissions using Dexter library
     */
    private void requestCameraPermission(final int type) {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {

                            if (type == MEDIA_TYPE_IMAGE) {
                                // capture picture
                                captureImage();
                            }

                        } else if (report.isAnyPermissionPermanentlyDenied()) {
                            showPermissionsAlert();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }


    /**
     * Capturing Camera Image will launch camera app requested image capture
     */
    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File file = CameraUtils.getServiceOutputMediaFile(MEDIA_TYPE_IMAGE);
        if (file != null) {
            imageStoragePath = file.getAbsolutePath();
        }

        Uri fileUri = CameraUtils.getOutputMediaFileUri(getApplicationContext(), file);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    /**
     * Saving stored image path to saved instance state
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on screen orientation
        // changes
        outState.putString(KEY_IMAGE_STORAGE_PATH, imageStoragePath);
    }

    /**
     * Restoring image path from saved instance state
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        imageStoragePath = savedInstanceState.getString(KEY_IMAGE_STORAGE_PATH);

        Log.d("img_store_path",imageStoragePath);
    }


    /**
     * Activity result method will be called after closing the camera
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                CameraUtils.refreshGallery(getApplicationContext(), imageStoragePath);

                previewCapturedImage();



            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    /**
     * Display image from gallery
     */
    private void previewCapturedImage() {
        try {

            //changed

            //new step


            String timeStamp      = getCurrentTimeStamp();

            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),SERVICE_GALLERY_DIRECTORY_NAME);
            File mediaFileUpdated = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + "." + IMAGE_EXTENSION);

            global_Img  = String.valueOf(mediaFileUpdated);

            FileOutputStream out = null;
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bmp = BitmapFactory.decodeFile(imageStoragePath, options);

            ////////////////////////////////--------------------------------------------------------------------------------------/////////////////////////////////

            Bitmap.Config config = bmp.getConfig();

            int width = bmp.getWidth();
            int height = bmp.getHeight();

            int contentTagX                     = (width/100);
            int contentTagBeginY                = (height/2)+(height/4);

            Bitmap imgNew = Bitmap.createBitmap(width, height, config);

            Canvas c = new Canvas(imgNew);
            c.drawBitmap(bmp, 0, 0, null);

            if(GLOBAL_IMAGE_NO.equals("C"))
            {
                Paint paint = new Paint();
                paint.setColor(Color.WHITE);
                paint.setStyle(Paint.Style.FILL);
                paint.setTextSize(bmp.getHeight()/30);
                c.drawText(team                                         , contentTagX, contentTagBeginY+(width/35*4), paint);
                c.drawText(datePrint                                    , contentTagX, contentTagBeginY+(width/35*5), paint);
            }

            else{


            }
            ///////////////////////--------------------------------------------------------------------------------------///////////////////////////


            try {


                out = new FileOutputStream(mediaFileUpdated);

                imgNew.compress(Bitmap.CompressFormat.JPEG, 50, out);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            image_file_global   = mediaFileUpdated;

            // if(buttonText.equals("checklist")){

            ivSChecListImg.setVisibility(View.VISIBLE);
            File newFile        = new File(mediaFileUpdated.getAbsolutePath());
            global_Img = String.valueOf(newFile);


            /**
             * file path here
             */

            ivSChecListImg.setImageURI(Uri.fromFile(newFile));

            Log.d("img_store_path_file", String.valueOf(newFile));


        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap RotateBitmap(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    /**
     * Alert dialog to navigate to app settings
     * to enable necessary permissions
     */
    private void showPermissionsAlert() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setTitle("Permissions required!")
                .setMessage("Camera needs few permissions to work properly. Grant them in settings.")
                .setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        CameraUtils.openSettings(UpdateStudentMathTestActivity.this);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }


    /**
     * Camera init over -----------------------------------------------------------------------------------------------------------------------
     */

    public String getCurrentTimeStamp()
    {
        Long tsLong     = System.currentTimeMillis()/1000;
        String ts       = tsLong.toString();
        return ts;
    }




}
