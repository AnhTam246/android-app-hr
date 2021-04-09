package com.example.hr.api;

import com.example.hr.model.Data;
import com.example.hr.model.DataOneObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    ApiService apiService = new Retrofit
            .Builder()
            .baseUrl("http://10.0.2.2:8888")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("/staff/list")
    Call<Data> getListStaff();

    @GET("/check-in-out/get-staff-time-get")
    Call<Data> getListCheckInOut(@Query("staff_id") Integer staff_id,
                                 @Query("y_m") String y_m);

    @GET("/time-leave/summary-staff-time")
    Call<Data> getListSummaryStaffTime(@Query("y_m") String y_m);

    @GET("/time-leave/detail-time-leave-all")
    Call<Data> getDetailTimeLeave(@Query("month_get") String month_get,
                                  @Query("staff_id") Integer staff_id);

    @GET("/staff/get-profile")
    Call<DataOneObject> getProfileStaff(@Query("staff_id") Integer staff_id);

    @GET("/special-date/list-special-date")
    Call<Data> getListSpecialDate(@Query("special_date_from") String special_date_from);

    @GET("/special-date/get-request-ot")
    Call<Data> getListRequestOt(@Query("special_date_from") String special_date_from,
                                @Query("staff_request") Integer staff_request,
                                @Query("department_request") Integer department_request);
//    @POST("/check-in-out/get-staff-time")
//    Call<Data> getListCheckIn(@Field("staff_id") int staff_id, @Field("y_m") Date y_m);
}
