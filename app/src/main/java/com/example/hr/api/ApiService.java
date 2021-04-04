package com.example.hr.api;

import com.example.hr.model.Data;
import com.example.hr.model.PostCheckIn;
import com.example.hr.model.Staff;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    //http://localhost:8888/staff/list
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    ApiService apiService = new Retrofit
            .Builder()
            .baseUrl("http://10.0.2.2:8888")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("/staff/list")
    Call<Data> getListStaff();

    @POST("/check-in-out/get-staff-time")
    Call<Data> getListCheckIn(@Body PostCheckIn postCheckIn);

    @GET("/check-in-out/get-staff-time-get")
    Call<Data> getListCheckInOut(@Query("staff_id") Integer staff_id,
                                 @Query("y_m") String y_m);

//    @POST("/check-in-out/get-staff-time")
//    Call<Data> getListCheckIn(@Field("staff_id") int staff_id, @Field("y_m") Date y_m);
}
