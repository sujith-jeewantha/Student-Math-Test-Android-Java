package com.sgstech.studentmathtest;

import android.Manifest;
import android.app.ProgressDialog;
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

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.sgstech.studentmathtest.Database.model.Student;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class AddStudentProfileActivity extends AppCompatActivity {

    private Manager_Cache managerCacheAddStudentProfile;

    public String GLOBAL_IMAGE_NO = "";
    File image_file_global = null;

    // Activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;

    // key to store image path in savedInstance state
    public static final String KEY_IMAGE_STORAGE_PATH = "image_path";

    public static final int MEDIA_TYPE_IMAGE = 1;

    // Bitmap sampling size
    public static final int RESULT_LOAD_IMG = 2;

    // Gallery directory name to store the images or videos
    public static final String STUDENT_GALLERY_DIRECTORY_NAME = "STUDENT-MATH/PROFILE";

    // Image and Video file extensions
    public static final String IMAGE_EXTENSION = "jpg";

    private static String imageStoragePath;

    private String noImageText = "NOIMG";

    int etPhoneNo = -1;
    int etEmailNo = -1;


    /**
     * Global image
     */
    public String global_Img;


    private EditText
            editTextStudentNo,
            editTextFirstName,
            editTextLastName,

            editTextPhone_1,
            editTextPhone_2,
            editTextPhone_3,
            editTextPhone_4,
            editTextPhone_5,
            editTextPhone_6,
            editTextPhone_7,
            editTextPhone_8,
            editTextPhone_9,
            editTextPhone_10,

            editTextEmail_1,
            editTextEmail_2,
            editTextEmail_3,
            editTextEmail_4,
            editTextEmail_5,
            editTextEmail_6,
            editTextEmail_7,
            editTextEmail_8,
            editTextEmail_9,
            editTextEmail_10;

    ImageView ivProfilePic;
    Button  btnProfilePic, btnAddMorePhone, btnAddMoreEmail;

    ProgressDialog progressDialog;



    /**
     * Camera features init over
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        editTextStudentNo = findViewById(R.id.student_s_no);
        editTextFirstName = findViewById(R.id.enter_student_first_name);
        editTextLastName = findViewById(R.id.enter_student_last_name);

        editTextPhone_1 = findViewById(R.id.entertpNo_one);
        editTextPhone_2 = findViewById(R.id.entertpNo_two);
        editTextPhone_3 = findViewById(R.id.entertpNo_three);
        editTextPhone_4 = findViewById(R.id.entertpNo_four);
        editTextPhone_5 = findViewById(R.id.entertpNo_five);
        editTextPhone_6 = findViewById(R.id.entertpNo_six);
        editTextPhone_7 = findViewById(R.id.entertpNo_seven);
        editTextPhone_8 = findViewById(R.id.entertpNo_eight);
        editTextPhone_9 = findViewById(R.id.entertpNo_nine);
        editTextPhone_10 = findViewById(R.id.entertpNo_ten);

        editTextEmail_1 = findViewById(R.id.enterEmail_one);
        editTextEmail_2 = findViewById(R.id.enterEmail_two);
        editTextEmail_3 = findViewById(R.id.enterEmail_three);
        editTextEmail_4 = findViewById(R.id.enterEmail_four);
        editTextEmail_5 = findViewById(R.id.enterEmail_five);
        editTextEmail_6 = findViewById(R.id.enterEmail_six);
        editTextEmail_7 = findViewById(R.id.enterEmail_seven);
        editTextEmail_8 = findViewById(R.id.enterEmail_eight);
        editTextEmail_9 = findViewById(R.id.enterEmail_nine);
        editTextEmail_10 = findViewById(R.id.enterEmail_ten);


        LinearLayout phoneTwoLayout = (LinearLayout) findViewById(R.id.layoutPhone_2);
        LinearLayout phoneThreeLayout = (LinearLayout) findViewById(R.id.layoutPhone_3);
        LinearLayout phoneFourLayout = (LinearLayout) findViewById(R.id.layoutPhone_4);
        LinearLayout phoneFiveLayout = (LinearLayout) findViewById(R.id.layoutPhone_5);
        LinearLayout phoneSixLayout = (LinearLayout) findViewById(R.id.layoutPhone_6);
        LinearLayout phoneSevenLayout = (LinearLayout) findViewById(R.id.layoutPhone_7);
        LinearLayout phoneEightLayout = (LinearLayout) findViewById(R.id.layoutPhone_8);
        LinearLayout phoneNineNLayout = (LinearLayout) findViewById(R.id.layoutPhone_9);
        LinearLayout phoneTenLayout = (LinearLayout) findViewById(R.id.layoutPhone_10);

        LinearLayout emailTwoLayout = (LinearLayout) findViewById(R.id.layoutEmail_2);
        LinearLayout emailThreeLayout = (LinearLayout) findViewById(R.id.layoutEmail_3);
        LinearLayout emailFourLayout = (LinearLayout) findViewById(R.id.layoutEmail_4);
        LinearLayout emailFiveLayout = (LinearLayout) findViewById(R.id.layoutEmail_5);
        LinearLayout emailSixLayout = (LinearLayout) findViewById(R.id.layoutEmail_6);
        LinearLayout emailSevenLayout = (LinearLayout) findViewById(R.id.layoutEmail_7);
        LinearLayout emailEightLayout = (LinearLayout) findViewById(R.id.layoutEmail_8);
        LinearLayout emailNineLayout = (LinearLayout) findViewById(R.id.layoutEmail_9);
        LinearLayout emailTenLayout = (LinearLayout) findViewById(R.id.layoutEmail_10);

        LinearLayout[] phoneLayouts = new LinearLayout[]{

                phoneTwoLayout,
                phoneThreeLayout,
                phoneFourLayout,
                phoneFiveLayout,
                phoneSixLayout,
                phoneSevenLayout,
                phoneEightLayout,
                phoneNineNLayout,
                phoneTenLayout

        };

        LinearLayout[] emailLayouts = new LinearLayout[]{

                emailTwoLayout,
                emailThreeLayout,
                emailFourLayout,
                emailFiveLayout,
                emailSixLayout,
                emailSevenLayout,
                emailEightLayout,
                emailNineLayout,
                emailTenLayout

        };


        phoneTwoLayout.setVisibility(View.GONE);
        phoneThreeLayout.setVisibility(View.GONE);
        phoneFourLayout.setVisibility(View.GONE);
        phoneFiveLayout.setVisibility(View.GONE);
        phoneSixLayout.setVisibility(View.GONE);
        phoneSevenLayout.setVisibility(View.GONE);
        phoneEightLayout.setVisibility(View.GONE);
        phoneNineNLayout.setVisibility(View.GONE);
        phoneTenLayout.setVisibility(View.GONE);



        emailTwoLayout.setVisibility(View.GONE);
        emailThreeLayout.setVisibility(View.GONE);
        emailFourLayout.setVisibility(View.GONE);
        emailFiveLayout.setVisibility(View.GONE);
        emailSixLayout.setVisibility(View.GONE);
        emailSevenLayout.setVisibility(View.GONE);
        emailEightLayout.setVisibility(View.GONE);
        emailNineLayout.setVisibility(View.GONE);
        emailTenLayout.setVisibility(View.GONE);

        btnAddMorePhone = (Button) findViewById(R.id.btnAddMorePhone);
        btnAddMoreEmail = (Button) findViewById(R.id.btnAddMoreEmail);

        ivProfilePic = (ImageView) findViewById(R.id.ivProfile);

        btnProfilePic = (Button) findViewById(R.id.btnProfilePic);

        btnProfilePic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(AddStudentProfileActivity.this, btnProfilePic);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(AddStudentProfileActivity.this,"You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();

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

                                Toast.makeText(getApplicationContext(),"I", Toast.LENGTH_LONG).show();


                                return true;

                            default:
                                return false;
                        }


                    }
                });

                popup.show();//showing popup menu
            }
        });

        findViewById(R.id.button_save_student_profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if(global_Img.isEmpty())
                    {
                        Toast.makeText(getApplicationContext(),"Student Image required", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        saveStudentProfile();
                    }
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

        btnAddMorePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if(etPhoneNo < 10 ) {
                        etPhoneNo++;
                        Log.d("btn_idp", String.valueOf(etPhoneNo));
                        phoneLayouts[etPhoneNo].setVisibility(View.VISIBLE);
                    }
                }catch (Exception e)
                {
                    System.out.println(e);
                }




            }
        });

        btnAddMoreEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if(etEmailNo < 10 ) {
                        etEmailNo++;
                        Log.d("btn_ide", String.valueOf(etEmailNo));
                        emailLayouts[etEmailNo].setVisibility(View.VISIBLE);
                    }
                }catch (Exception e)
                {
                    System.out.println(e);
                }


            }
        });
    }




    private void saveStudentProfile() {

        final String sStudentPic  =  global_Img;

         final String sStudentNo  =  editTextStudentNo.getText().toString().trim();
         final String sFirstName  =  editTextFirstName.getText().toString().trim();
         final String sLastName  =  editTextLastName .getText().toString().trim();

         final String sPhoneNoOne  =  editTextPhone_1.getText().toString().trim();
         final String sPhoneNoTwo  =  editTextPhone_2.getText().toString().trim();
         final String sPhoneNoThree  =  editTextPhone_3.getText().toString().trim();
         final String sPhoneNoFour  =  editTextPhone_4.getText().toString().trim();
         final String sPhoneNoFive  =  editTextPhone_5.getText().toString().trim();
         final String sPhoneNoSix  =  editTextPhone_6.getText().toString().trim();
         final String sPhoneNoSeven  =  editTextPhone_7.getText().toString().trim();
         final String sPhoneNoEight  =  editTextPhone_8.getText().toString().trim();
         final String sPhoneNoNine  =  editTextPhone_9.getText().toString().trim();
         final String sPhoneNoTen  =  editTextPhone_10.getText().toString().trim();

         final String sEmailOne  =  editTextEmail_1.getText().toString().trim();
         final String sEmailTwo  =  editTextEmail_2.getText().toString().trim();
         final String sEmailThree  = editTextEmail_3.getText().toString().trim();
         final String sEmailFour  = editTextEmail_4.getText().toString().trim();
         final String sEmailFive  = editTextEmail_5.getText().toString().trim();
         final String sEmailSix  =  editTextEmail_6.getText().toString().trim();
         final String sEmailSeven  =editTextEmail_7.getText().toString().trim();
         final String sEmailEight  =editTextEmail_8.getText().toString().trim();
         final String sEmailNine  = editTextEmail_9.getText().toString().trim();
         final String sEmailTen  =  editTextEmail_10.getText().toString().trim();



        try {
            if (sStudentNo.isEmpty()) {
                editTextStudentNo.setError("Student No required");
                editTextStudentNo.requestFocus();
                return;

            }

            else if (sStudentPic.isEmpty()) {
//                editTextStudentNo.setError("Student No required");
//                editTextStudentNo.requestFocus();
                Toast.makeText(getApplicationContext(),"Student Image required", Toast.LENGTH_LONG).show();
                return;

            }


        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Student No required", Toast.LENGTH_LONG).show();
        }



        class SaveStudentProfile extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                Student student = new Student();




//                /**
//                 * Profile image
//                 */
//                if(GLOBAL_IMAGE_NO == "C" ) {
//
//                    student.setStudent_profile_img(global_Img);
//                }

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
                startActivity(new Intent(getApplicationContext(), MainActivity_Student_Profile.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveStudentProfile ssg = new SaveStudentProfile();
        ssg.execute();
    }


    /**
     * Capturing Camera Image will launch camera app requested image capture
     */
    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File file = CameraUtils.getStudentOutputMediaFile(MEDIA_TYPE_IMAGE);
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


//        if (resultCode == RESULT_OK) {
//            try {
//
//
//                final Uri imageUri = data.getData();
//                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
//                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
//
//
//                global_Img = String.valueOf(selectedImage);
//
//                Log.d("f_img",global_Img);
//
//                /**
//                 * file path here
//                 */
//
//
//                Glide.with(this)
//                        .load(imageUri)
//                        .circleCrop()
//                        .into(ivProfilePic);
//
//
////                ivProfilePic.setImageBitmap(selectedImage);
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//                Toast.makeText(AddStudentProfileActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
//            }
//
//        }else {
//            Toast.makeText(AddStudentProfileActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
//        }


    }

    /**
     * Display image from gallery
     */
    private void previewCapturedImage() {
        try {

            //changed

            //new step


            String timeStamp      = getCurrentTimeStamp();

            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), STUDENT_GALLERY_DIRECTORY_NAME);
            File mediaFileUpdated = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + "." + IMAGE_EXTENSION);

            global_Img  = String.valueOf(mediaFileUpdated);

            FileOutputStream out = null;
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bmp = BitmapFactory.decodeFile(imageStoragePath, options);

            ////////////////////////////////-------------------------------------------------------------------------------///////////////////////////

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

            // if(buttonText.equals("checklist")){

            ivProfilePic.setVisibility(View.VISIBLE);
            File newFile        = new File(mediaFileUpdated.getAbsolutePath());
            global_Img = String.valueOf(newFile);


            /**
             * file path here
             */

//            ivProfilePic.setImageURI(Uri.fromFile(newFile));

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
                        CameraUtils.openSettings(AddStudentProfileActivity.this);
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
