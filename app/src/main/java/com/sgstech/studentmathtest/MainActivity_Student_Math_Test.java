package com.sgstech.studentmathtest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.amitshekhar.DebugDB;
import com.google.android.material.snackbar.Snackbar;
import com.sgstech.studentmathtest.Database.model.Student;
import com.sgstech.studentmathtest.adapter.StudentMathTestAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity_Student_Math_Test extends AppCompatActivity {



    private String serviceRegion = "";
    private String service_site_id = "";
    private String service_site_name = "";
    private String service_team_global = "";


    private ArrayList<Uri> arrayList;

    String service_breakdown_name = "Service";
    String site_id_site_name_name = service_site_id + " " + service_site_name;
    String unit_no = "";
    String stage_name = "After";

    SwipeRefreshLayout pullToRefresh;
    private RecyclerView recyclerView;

    int k=1;
    int count =0;
    ProgressDialog progressDialog;
    File image_file_global = null;
    Button btnUploadImage, btnServiceBackToHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_student_math_test);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        /**
         * for check db-----------------------------------------------------------------------------
         */

        DebugDB.getAddressLog();

        /**
         * db check over----------------------------------------------------------------------------
         */

        /**
         * retrieve cache data----------------------------------------------------------------------
         */


        /**
         * retrieve cache data over-----------------------------------------------------------------
         */

        pullToRefresh = (SwipeRefreshLayout) findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent(); // your code
                //pullToRefresh.setRefreshing(false);
            }
        });

//        File image_file_global_          = new File(getIntent().getStringExtra("image_path"));

        //////////////////////////////////

        //delete database

//        DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
//                .serviceGalleryDao()
//                .delete();

        //////////////////////////////////

        btnServiceBackToHome    = (Button)findViewById(R.id.button_back_service_main);


        recyclerView = findViewById(R.id.recyclerview_tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        btnServiceBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), StudentMain.class);
                startActivity(intent);
            }
        });


        getServiceGallerys();


    }


    @Override
    protected void onStart() {
        super.onStart();
        final Student student = (Student) getIntent().getSerializableExtra("serviceGallery");
        loadServiceGalleryImagesFromDB(student);
    }

    private void loadServiceGalleryImagesFromDB(Student student) {
        //   editTextTask.setText(serviceGallery.getServiceGallery());
//        editTextServiceUnitNo.setText(serviceGallery.getUnitNo());


       // String image_path =  serviceGallery.getServiceChecklistImg();

      //  Log.d("image_file_from_db",String.valueOf(image_path) );
        // String image_path = "/storage/emulated/0/Pictures/BROWNS-ACMS/SERVICE/IMG_1624596816.jpg";
      //  image_file_global = new File(image_path);



       // String checklistImg = "/storage/emulated/0/Pictures/BROWNS-ACMS/SERVICE/IMG_1624596816.jpg"; // serviceGallery.getServiceChecklistImg();

      //  Log.d("img_from_db",checklistImg);

//        ivSChecListImg.setImageURI(Uri.fromFile(new File(String.valueOf(checklistImg))));
        // ivSChecListImg.setImageURI(Uri.parse(String.valueOf(checklistImg)));

      //  Log.d("img_from_db_c",checklistImg);

//        ivSChecListImg.setImageURI(Uri.parse("/storage/emulated/0/Pictures/BROWNS-ACMS/SERVICE/IMG_1624596816.jpg"));
//        editTextFinishBy.setText(serviceGallery.getFinishBy());
//        checkBoxFinished.setChecked(serviceGallery.isFinished());
    }

    private void refreshContent(){

        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                pullToRefresh.setRefreshing(false);
                finish();
                startActivity(getIntent());
            }
        }, 3000);

    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(getIntent());
    }

    private void getServiceGallerys() {
        class GetServiceGallerys extends AsyncTask<Void, Void, List<Student>> {

            @Override
            protected List<Student> doInBackground(Void... voids) {
                List<Student> serviceList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .studentDao()
                        .getAll();
                return serviceList;
            }

            @Override
            protected void onPostExecute(List<Student> serviceGalleries) {
                super.onPostExecute(serviceGalleries);
                StudentMathTestAdapter adapter = new StudentMathTestAdapter(MainActivity_Student_Math_Test.this, serviceGalleries);
                recyclerView.setAdapter(adapter);
            }
        }

        GetServiceGallerys gt = new GetServiceGallerys();
        gt.execute();
    }

    public String getCurrentTimeStamp()
    {
        Long tsLong     = System.currentTimeMillis()/1000;
        String ts       = tsLong.toString();
        return ts;
    }




    /**
     * retrofit uploader init-----------------------------------------------------------------------
     */

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {
        // use the FileUtils to get the actual file by uri
        File file = FileUtils.getFile(this, fileUri);

        // create RequestBody instance from file
        RequestBody requestFile = RequestBody.create (MediaType.parse(FileUtils.MIME_TYPE_IMAGE), file);

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(MediaType.parse(FileUtils.MIME_TYPE_TEXT), descriptionString);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void uploadImagesToServer() {
        if (CheckNetworkStatus.isNetworkStatusAvialable(getBaseContext())) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            progressDialog = new ProgressDialog(MainActivity_Student_Math_Test.this,ProgressDialog.THEME_HOLO_DARK);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setTitle("Please wait moment...");
            progressDialog.setMessage("Images uploading to BROWNS ACMS server.Please wait a moment!");
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

//            showProgress();

            // create list of file parts (photo, video, ...)
            List<MultipartBody.Part> parts = new ArrayList<>();

            // create upload service client
            ApiService service = retrofit.create(ApiService.class);

            if (arrayList != null) {
                // create part for file (photo, video, ...)
                for (int i = 0; i < arrayList.size(); i++) {
                    parts.add(prepareFilePart("image"+i, arrayList.get(i)));
                }
            }

            // create a map of data to pass along
            RequestBody region = createPartFromString(serviceRegion);
            RequestBody service_breakdown = createPartFromString(service_breakdown_name);
            RequestBody site_id_name = createPartFromString(site_id_site_name_name);
            RequestBody unit_name = createPartFromString(unit_no );
            RequestBody stage_before_after = createPartFromString(stage_name);

            RequestBody size = createPartFromString(""+parts.size());

            // finally, execute the request
            Call<ResponseBody> call = service.uploadMultiple(region,service_breakdown,site_id_name,unit_name, stage_before_after, size, parts);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(MainActivity_Student_Math_Test.this,
                                "Images successfully uploaded!", Toast.LENGTH_SHORT).show();
                        finish();
                        Intent intent = new Intent(getApplicationContext(), StudentMain.class);
                        startActivity(intent);
                    } else {
                        Snackbar.make(findViewById(android.R.id.content),"Something wrong", Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
//                    Log.e(TAG, "Image upload failed!", t);
                    Snackbar.make(findViewById(android.R.id.content),
                            "Image upload failed!", Snackbar.LENGTH_LONG).show();
                }
            });

        } else {
            Intent intent = new Intent(MainActivity_Student_Math_Test.this, AlertNoInternet.class);
            startActivity(intent);
        }
    }

}
