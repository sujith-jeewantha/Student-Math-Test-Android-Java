package com.sgstech.studentmathtest;

import static android.Manifest.permission.READ_CONTACTS;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.sgstech.studentmathtest.Cache.Manager_Cache;
import com.sgstech.studentmathtest.Database.model.Student;
import com.sgstech.studentmathtest.Manager.Manager_Permissions;
import com.sgstech.studentmathtest.Utills.DatabaseClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class AddStudentProfileActivity extends AppCompatActivity{

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

    public static final int RESULT_CONTACT = 3;

    public static final int PERMISSIONS_REQUEST_READ_CONTACTS = 4;

    // Gallery directory name to store the images or videos
    public static final String STUDENT_GALLERY_DIRECTORY_NAME = "STUDENT-MATH/PROFILE";

    // Image and Video file extensions
    public static final String IMAGE_EXTENSION = "jpg";

    private static String imageStoragePath;

    private String noImageText = "NOIMG";

    int etPhoneNo = -1;
    int etEmailNo = -1;

    private String TAG = "Contacts";
    private static final int RESULT_PICK_CONTACT = 5;

    String phoneNo;
    String id;
    String name;
    String email;
    Cursor cursor;
    Cursor c;


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
    Button  btnProfilePic,
            btnAddMorePhone,
            btnAddMoreEmail,

            btnPhoneFromContact_1,
            btnPhoneFromContact_2,
            btnPhoneFromContact_3,
            btnPhoneFromContact_4,
            btnPhoneFromContact_5,
            btnPhoneFromContact_6,
            btnPhoneFromContact_7,
            btnPhoneFromContact_8,
            btnPhoneFromContact_9,
            btnPhoneFromContact_10;


    ProgressDialog progressDialog;

    Manager_Permissions managerPermissions = new Manager_Permissions();


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

        btnPhoneFromContact_1 = findViewById(R.id.btnPhoneFromContact_1);
        btnPhoneFromContact_2 = findViewById(R.id.btnPhoneFromContact_2);
        btnPhoneFromContact_3 = findViewById(R.id.btnPhoneFromContact_3);
        btnPhoneFromContact_4 = findViewById(R.id.btnPhoneFromContact_4);
        btnPhoneFromContact_5 = findViewById(R.id.btnPhoneFromContact_5);
        btnPhoneFromContact_6 = findViewById(R.id.btnPhoneFromContact_6);
        btnPhoneFromContact_7 = findViewById(R.id.btnPhoneFromContact_7);
        btnPhoneFromContact_8 = findViewById(R.id.btnPhoneFromContact_8);
        btnPhoneFromContact_9 = findViewById(R.id.btnPhoneFromContact_9);
        btnPhoneFromContact_10 = findViewById(R.id.btnPhoneFromContact_10);


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

                                Intent intent = new Intent(AddStudentProfileActivity.this, ImagesFromServerActivity.class);
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

        /**
         *  get contacts
         */

        btnPhoneFromContact_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CameraUtils.checkPermissions(getApplicationContext())) {

                   Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                   startActivityForResult(contactPickerIntent, RESULT_CONTACT);

                } else {
                    check();
                }

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
    @SuppressLint("Range")
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

        if(requestCode ==RESULT_LOAD_IMG)
        {

            if (resultCode == RESULT_OK) {
                    try {

                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                        global_Img = String.valueOf(selectedImage);

                        Log.d("f_img",global_Img);

                        /**
                         * file path here
                         */

                        Glide.with(this)
                                .load(imageUri)
                                .circleCrop()
                                .into(ivProfilePic);


        //                ivProfilePic.setImageBitmap(selectedImage);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(AddStudentProfileActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                    }

            }else {
            Toast.makeText(AddStudentProfileActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
            }

        }




        if(requestCode == RESULT_CONTACT)
        {
            if (resultCode == RESULT_OK) {
                // Check for the request code, we might be usign multiple startActivityForResult
                switch (requestCode) {
                    case RESULT_PICK_CONTACT:
                        contactPicked(data);
                        break;
                }
            } else {
                Log.e("ContactFragment", "Failed to pick contact");
            }if (resultCode == Activity.RESULT_OK) {
            Uri contactData = data.getData();
            c = getContentResolver().query(contactData, null, null, null, null);
            if (c.moveToFirst()) {
                @SuppressLint("Range") String contactId = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
                @SuppressLint("Range") String hasNumber = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                String num = "";
                if (Integer.valueOf(hasNumber) == 1) {
                    Cursor numbers = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                    while (numbers.moveToNext()) {
                        num = numbers.getString(numbers.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Toast.makeText(AddStudentProfileActivity.this, "Number=" + num, Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
        }






    }

    /**
     * Get Contact
     */
    @SuppressLint("Range")
    private void contactPicked(Intent data) {

        Uri uri = data.getData();
        Log.i(TAG, "contactPicked() uri " + uri.toString());

        ContentResolver cr = AddStudentProfileActivity.this.getContentResolver();

        try {
            Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
            if (null != cur && cur.getCount() > 0) {
                cur.moveToFirst();
                for (String column : cur.getColumnNames()) {
                    Log.i(TAG, "contactPicked() Contacts column " + column + " : " + cur.getString(cur.getColumnIndex(column)));
                }
            }

            if (cur.getCount() > 0) {
                //Query the content uri
                cursor = AddStudentProfileActivity.this.getContentResolver().query(uri, null, null, null, null);

                if (null != cursor && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    for (String column : cursor.getColumnNames()) {
                        Log.i(TAG, "contactPicked() uri column " + column + " : " + cursor.getString(cursor.getColumnIndex(column)));
                    }
                }

                cursor.moveToFirst();
                id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                Log.i(TAG, "contactPicked() uri id " + id);
                String contact_id = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                Log.i(TAG, "contactPicked() uri contact id " + contact_id);
                // column index of the contact name
                name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                // column index of the phone number
                phoneNo = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                //get Email id of selected contact....
                Log.e("ContactsFragment", "::>> " + id + name + phoneNo);

                Cursor cur1 = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", new String[]{contact_id}, null);

                if (null != cur1 && cur1.getCount() > 0) {
                    cur1.moveToFirst();
                    for (String column : cur1.getColumnNames()) {
                        Log.i(TAG, "contactPicked() Email column " + column + " : " + cur1.getString(cur1.getColumnIndex(column)));
                        email = cur1.getString(cur1.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                    }

                    //HERE YOU GET name, phoneno & email of selected contact from contactlist.....
                    Log.e("setcontactDetails","::>>" + name+"\nPhoneno:" + phoneNo+"\nEmail: " + email);
                } else {
                    Log.e("setcontactDetails","::>>" + name+"\nPhoneno:" + phoneNo+"\nEmail: " + email);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
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
     * Requesting Contact Permission
     */
    public void requestContactPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        READ_CONTACTS)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Read Contacts permission");
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.setMessage("Please enable access to contacts.");
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @TargetApi(Build.VERSION_CODES.M)
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            requestPermissions(
                                    new String[]
                                            {READ_CONTACTS}
                                    , PERMISSIONS_REQUEST_READ_CONTACTS);
                        }
                    });
                    builder.show();
                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{READ_CONTACTS},
                            PERMISSIONS_REQUEST_READ_CONTACTS);
                }
            } else {
//                getPhoneNumber();
            }
        } else {
//            getPhoneNumber();
        }
    }


    /**
     * --------------------------------------------------------------------
     */

    private void check(){


        if (ContextCompat.checkSelfPermission(AddStudentProfileActivity.this, Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED) {
            Runnable r = new Runnable() {
                @Override
                public void run() {
//                    getContacts();
                }
            };
            Thread thread = new Thread(r);
            thread.start();
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {
                    Toast.makeText(AddStudentProfileActivity.this, "Read contacts permission is required to function app correctly", Toast.LENGTH_LONG).show();
                }else {
                    ActivityCompat.requestPermissions(AddStudentProfileActivity.this,
                            new String[]{Manifest.permission.READ_CONTACTS},
                            1);                }

            }
        }


    }

    private boolean checkPermission(){
        if (ActivityCompat.checkSelfPermission(AddStudentProfileActivity.this, READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(AddStudentProfileActivity.this, READ_CONTACTS)) {
                Toast.makeText(AddStudentProfileActivity.this, "Contact read permission needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", AddStudentProfileActivity.this.getPackageName(), null));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, 789);
                return false;
            }else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{READ_CONTACTS}, 123);
                }
                return false;
            }
        }
        return true;
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
