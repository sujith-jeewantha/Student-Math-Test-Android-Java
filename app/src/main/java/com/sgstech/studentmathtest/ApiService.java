package com.sgstech.studentmathtest;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface ApiService {
    String BASE_URL = "https://web.com/image_api_bydate/.ret_date_upload.php/";

    @Multipart
    @POST(".ret_date_upload.php")
    Call<ResponseBody> uploadMultiple(
            @Part("region") RequestBody region,
            @Part("sdgsd") RequestBody service_breakdown,
            @Part("site_id_name") RequestBody site_id_name,
            @Part("unit_name") RequestBody unit_name,
            @Part("stage_before_after") RequestBody stage_before_after,
            @Part("size") RequestBody size,
            @Part List<MultipartBody.Part> files);
}
