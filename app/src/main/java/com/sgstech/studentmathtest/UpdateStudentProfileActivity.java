package com.sgstech.studentmathtest;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.sgstech.studentmathtest.Database.model.Student;
import com.sgstech.studentmathtest.Utills.DatabaseClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class UpdateStudentProfileActivity extends AppCompatActivity {

    public String GLOBAL_IMAGE_NO = "";
    File image_file_global = null;

    // Activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;

    // key to store image path in savedInstance state
    public static final String KEY_IMAGE_STORAGE_PATH = "image_path";

    public static final int MEDIA_TYPE_IMAGE = 1;

    public static final int RESULT_LOAD_IMG = 2;


    // Gallery directory name to store the images or videos
    public static final String STUDENT_GALLERY_DIRECTORY_NAME = "STUDENT-MATH/PROFILE";

    // Image and Video file extensions
    public static final String IMAGE_EXTENSION = "jpg";

    private static String imageStoragePath;

    private int targetHeight = 100;
    private int targetWidth = 130;

    /**
     * Global image
     */
    public String global_Img;

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

    LinearLayout
            phoneTwoLayout,
            phoneThreeLayout,
            phoneFourLayout,
            phoneFiveLayout,
            phoneSixLayout,
            phoneSevenLayout,
            phoneEightLayout,
            phoneNineLayout,
            phoneTenLayout,

            emailTwoLayout,
            emailThreeLayout,
            emailFourLayout,
            emailFiveLayout,
            emailSixLayout,
            emailSevenLayout,
            emailEightLayout,
            emailNineLayout,
            emailTenLayout;

    ImageView ivProfilePic;
    Button  btnProfilePic;


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
        editTextEmailTen = findViewById(R.id.enterEmail_ten);
        editTextEmailNine = findViewById(R.id.enterEmail_nine);


        phoneTwoLayout = (LinearLayout) findViewById(R.id.layoutPhone_2);
        phoneThreeLayout = (LinearLayout) findViewById(R.id.layoutPhone_3);
        phoneFourLayout = (LinearLayout) findViewById(R.id.layoutPhone_4);
        phoneFiveLayout = (LinearLayout) findViewById(R.id.layoutPhone_5);
        phoneSixLayout = (LinearLayout) findViewById(R.id.layoutPhone_6);
        phoneSevenLayout = (LinearLayout) findViewById(R.id.layoutPhone_7);
        phoneEightLayout = (LinearLayout) findViewById(R.id.layoutPhone_8);
        phoneNineLayout = (LinearLayout) findViewById(R.id.layoutPhone_9);
        phoneTenLayout = (LinearLayout) findViewById(R.id.layoutPhone_10);

        emailTwoLayout = (LinearLayout) findViewById(R.id.layoutEmail_2);
        emailThreeLayout = (LinearLayout) findViewById(R.id.layoutEmail_3);
        emailFourLayout = (LinearLayout) findViewById(R.id.layoutEmail_4);
        emailFiveLayout = (LinearLayout) findViewById(R.id.layoutEmail_5);
        emailSixLayout = (LinearLayout) findViewById(R.id.layoutEmail_6);
        emailSevenLayout = (LinearLayout) findViewById(R.id.layoutEmail_7);
        emailEightLayout = (LinearLayout) findViewById(R.id.layoutEmail_8);
        emailNineLayout = (LinearLayout) findViewById(R.id.layoutEmail_9);
        emailTenLayout = (LinearLayout) findViewById(R.id.layoutEmail_10);

        ivProfilePic = (ImageView) findViewById(R.id.ivProfile);

        btnProfilePic = (Button) findViewById(R.id.btnProfilePic);

        btnProfilePic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(UpdateStudentProfileActivity.this, btnProfilePic);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(UpdateStudentProfileActivity.this,"You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();

                        switch (item.getItemId()) {
                            case R.id.id_camera:
                                // do your code

                                if (CameraUtils.checkPermissions(getApplicationContext())) {
                                    captureImage();
                                } else {
                                    requestCameraPermission(MEDIA_TYPE_IMAGE);
                                }

                                return true;
                            case R.id.id_files:
                                // do your code


                                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                                photoPickerIntent.setType("image/*");
                                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);


                                return true;
                            case R.id.id_internet:
                                // do your code

                                Intent intent = new Intent(UpdateStudentProfileActivity.this, ImagesFromServerActivity.class);
                                startActivity(intent);

                                return true;

                            default:
                                return false;
                        }


                    }
                });

                popup.show();//showing popup menu
            }
        });



        final Student student = (Student) getIntent().getSerializableExtra("studentProfile");

        loadStudentProfile(student);



        findViewById(R.id.button_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateStudentProfile(student);

            }
        });

        findViewById(R.id.button_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateStudentProfileActivity.this);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteStudentProfile(student);
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
        Intent intent = new Intent(getApplicationContext(), MainActivity_Student_Profile.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }



    private void loadStudentProfile(Student student) {


        String studentImg = student.getStudent_profile_img();


        try {

            File file = new File(studentImg);
            Uri imageUri = Uri.fromFile(file);

            Glide.with(this)
                    .load(imageUri)
                    .circleCrop()
                    .into(ivProfilePic);

        }

        catch (Exception e)
        {

        }


        editTextStudentNo.setText(student.getStudent_no());
        editTextFirstName.setText(student.getStudent_first_name());
        editTextLastName.setText(student.getStudent_last_name());

        editTextPhoneNoOne.setText(student.getStudent_phone_one());
        editTextEmailOne.setText(student.getStudent_email_one());

        String phoneNo_2 = student.getStudent_phone_two();

        /**
         * Phone Layout
         */
        if (!phoneNo_2.equals(""))
        {
            editTextPhoneNoTwo.setText(phoneNo_2);
            editTextPhoneNoTwo.setVisibility(View.VISIBLE);
        }
            else
        {
            phoneTwoLayout.setVisibility(View.GONE);
        }

        String phoneNo_3 = student.getStudent_phone_three();

        if (!phoneNo_3.equals(""))
        {
            editTextPhoneNoThree.setText(phoneNo_3);
            phoneThreeLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            phoneThreeLayout.setVisibility(View.GONE);
        }

        String phoneNo_4 = student.getStudent_phone_four();

        if (!phoneNo_4.equals(""))
        {
            editTextPhoneNoFour.setText(phoneNo_4);
            phoneFourLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            phoneFourLayout.setVisibility(View.GONE);
        }

        String phoneNo_5 = student.getStudent_phone_five();

        if (!phoneNo_5.equals(""))
        {
            editTextPhoneNoFive.setText(phoneNo_5);
            phoneFiveLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            phoneFiveLayout.setVisibility(View.GONE);
        }

        String phoneNo_6 = student.getStudent_phone_six();

        if (!phoneNo_6.equals(""))
        {
            editTextPhoneNoSix.setText(phoneNo_6);
            phoneSixLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            phoneSixLayout.setVisibility(View.GONE);
        }

        String phoneNo_7 = student.getStudent_phone_seven();

        if (!phoneNo_7.equals(""))
        {
            editTextPhoneNoSeven.setText(phoneNo_7);
            phoneSevenLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            phoneSevenLayout.setVisibility(View.GONE);
        }

        String phoneNo_8 = student.getStudent_phone_eight();

        if (!phoneNo_8.equals(""))
        {
            editTextPhoneNoEight.setText(phoneNo_8);
            phoneEightLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            phoneEightLayout.setVisibility(View.GONE);
        }

        String phoneNo_9 = student.getStudent_phone_nine();

        if (!phoneNo_9.equals(""))
        {
            editTextPhoneNoNine.setText(phoneNo_9);
            phoneNineLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            phoneNineLayout.setVisibility(View.GONE);
        }

        String phoneNo_10 = student.getStudent_phone_ten();

        if (!phoneNo_10.equals(""))
        {
            editTextPhoneNoTen.setText(phoneNo_10);
            phoneTenLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            phoneTenLayout.setVisibility(View.GONE);
        }
        /**
         *  Email layout
         */

        String email_2 = student.getStudent_email_two();

        if (!email_2.equals(""))
        {
            editTextEmailTwo.setText(email_2);
            emailTwoLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            emailTwoLayout.setVisibility(View.GONE);
        }

        String email_3 = student.getStudent_email_three();

        if (!email_3.equals(""))
        {
            editTextEmailThree.setText(email_3);
            emailThreeLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            emailThreeLayout.setVisibility(View.GONE);
        }

        String email_4 = student.getStudent_email_four();

        if (!email_4.equals(""))
        {
            editTextEmailFour.setText(email_4);
            emailFourLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            emailFourLayout.setVisibility(View.GONE);
        }

        String email_5 = student.getStudent_email_five();

        if (!email_5.equals(""))
        {
            editTextEmailFive.setText(email_5);
            emailFiveLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            emailFiveLayout.setVisibility(View.GONE);
        }

        String email_6 = student.getStudent_email_six();

        if (!email_6.equals(""))
        {
            editTextEmailSix.setText(email_6);
            emailSixLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            emailSixLayout.setVisibility(View.GONE);
        }

        String email_7 = student.getStudent_email_seven();

        if (!email_7.equals(""))
        {
            editTextEmailSeven.setText(email_7);
            emailSevenLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            emailSevenLayout.setVisibility(View.GONE);
        }

        String email_8 = student.getStudent_email_eight();

        if (!email_8.equals(""))
        {
            editTextEmailEight.setText(email_8);
            emailEightLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            emailEightLayout.setVisibility(View.GONE);
        }

        String email_9 = student.getStudent_email_nine();

        if (!email_9.equals(""))
        {
            editTextEmailNine.setText(email_9);
            emailNineLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            emailNineLayout.setVisibility(View.GONE);
        }

        String email_10 = student.getStudent_email_ten();

        if (!email_10.equals(""))
        {
            editTextEmailTen.setText(email_10);
            emailTenLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            emailTenLayout.setVisibility(View.GONE);
        }

    }

    private void updateStudentProfile(final Student student) {

        final String sStudentPic  =  global_Img;

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
            Toast.makeText(getApplicationContext(),"Student No required", Toast.LENGTH_SHORT).show();
        }


        class UpdateStudentProfile extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                student.setStudent_profile_img(sStudentPic);

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

                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .studentDao()
                        .update(student);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(UpdateStudentProfileActivity.this, MainActivity_Student_Profile.class));
            }
        }

        UpdateStudentProfile ut = new UpdateStudentProfile();
        ut.execute();
    }


    private void deleteStudentProfile(final Student student) {
        class DeleteStudentProfile extends AsyncTask<Void, Void, Void> {

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
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(UpdateStudentProfileActivity.this, MainActivity_Student_Profile.class));
            }
        }

        DeleteStudentProfile dt = new DeleteStudentProfile();
        dt.execute();

    }


    /**
     * Capturing Camera Image will launch camera app requested image capture
     */
    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File file = CameraUtils.getStudentUpdateOutputMediaFile(MEDIA_TYPE_IMAGE);
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

            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),STUDENT_GALLERY_DIRECTORY_NAME);
            File mediaFileUpdated = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + "." + IMAGE_EXTENSION);

            global_Img  = String.valueOf(mediaFileUpdated);

            Log.d("img_path_file", global_Img);

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


            ivProfilePic.setVisibility(View.VISIBLE);
            File newFile        = new File(mediaFileUpdated.getAbsolutePath());
            global_Img = String.valueOf(newFile);

            Log.d("img_path_file", global_Img);


            /**
             * file path here
             */


            Uri imageUri = Uri.fromFile(newFile);

            Glide.with(this)
                    .load(imageUri)
                    .circleCrop()
                    .into(ivProfilePic);

            Log.d("img_store_path_file", String.valueOf(newFile));


        } catch (NullPointerException e) {
            e.printStackTrace();
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
     * Alert dialog to navigate to app settings
     * to enable necessary permissions
     */
    private void showPermissionsAlert() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setTitle("Permissions required!")
                .setMessage("Camera needs few permissions to work properly. Grant them in settings.")
                .setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        CameraUtils.openSettings(UpdateStudentProfileActivity.this);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }


    public String getCurrentTimeStamp()
    {
        Long tsLong     = System.currentTimeMillis()/1000;
        String ts       = tsLong.toString();
        return ts;
    }

}
