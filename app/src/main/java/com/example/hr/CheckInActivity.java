package com.example.hr;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hr.adapter.CheckInAdapter;
import com.example.hr.api.ApiService;
import com.example.hr.model.CheckInOut;
import com.example.hr.model.Data;
import com.example.hr.model.StaffLogin;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.hr.MainActivity.checkDateFormat;

public class CheckInActivity extends AppCompatActivity {
    private Data data;
    ListView lvCheckin;
    ArrayList<CheckInOut> arrCheckIn;
    CheckInAdapter checkInAdapter;
    EditText y_m;
    Button btnSearch;
    TextView tvTitle;

    //Summary staff time
    TextView tvTime;
    TextView tvInLate;
    TextView tvOutSoon;
    TextView tvOt;
    TextView tvNumberTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.logo_main);    //Icon muốn hiện thị
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        checkLogin();

        SharedPreferences preferences = getSharedPreferences("com.example.hr", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("StaffLogin", "");
        StaffLogin staffLogin = gson.fromJson(json, StaffLogin.class);
        data = new Data();
        lvCheckin = (ListView) findViewById(R.id.lvTimeLeave);
        arrCheckIn = new ArrayList<>();
        String date = "2021-04-01";
        sendRequest(staffLogin.getId(), date);
        getSummaryStaffTime(staffLogin.getId(), date);
        checkInAdapter = new CheckInAdapter(this, R.layout.item_check_in_out, arrCheckIn);
        lvCheckin.setAdapter(checkInAdapter);

        btnSearch = findViewById(R.id.btnSearchTimeLeave);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                y_m = findViewById(R.id.y_m_time_leave);
                if(checkDateFormat(y_m.getText().toString()) == false) {
                    Toast.makeText(CheckInActivity.this, "Vui lòng nhập đúng định dạng yyyy-mm", Toast.LENGTH_SHORT).show();
                    return;
                }
                tvTitle = findViewById(R.id.tvTitleTimeLeave);
                if(y_m.getText().toString().isEmpty()) {
                    Toast.makeText(CheckInActivity.this, "Vui lòng nhập để tìm kiếm", Toast.LENGTH_SHORT).show();
                } else {
                    lvCheckin = (ListView) findViewById(R.id.lvTimeLeave);
                    arrCheckIn = new ArrayList<>();
                    String date = y_m.getText().toString() + "-01";
                    sendRequest(staffLogin.getId(), date);
                    checkInAdapter = new CheckInAdapter(CheckInActivity.this, R.layout.item_check_in_out, arrCheckIn);
                    lvCheckin.setAdapter(checkInAdapter);
                    tvTitle.setText("Lịch Sử Chấm Công " + y_m.getText().toString());
                    getSummaryStaffTime(staffLogin.getId(), date);
                }
            }
        });
    }

    private void getSummaryStaffTime(Integer id, String date) {
        ApiService.apiService.getListSummaryStaffTime(date).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                data = response.body();
                for (HashMap<String, Object> summary : data.getData()) {
                    //System.out.println((int) Double.parseDouble(summary.get("staff_id").toString()));
                    if((int) Double.parseDouble(summary.get("staff_id").toString()) == id) {
                        tvTime = findViewById(R.id.tvTime);
                        tvInLate = findViewById(R.id.tvInLate);
                        tvOutSoon = findViewById(R.id.tvOutSoon);
                        tvOt = findViewById(R.id.tvOt);
                        tvNumberTime = findViewById(R.id.tvNumberTime);

                        if(summary.get("sum_time") != null) {
                            tvTime.setText(summary.get("sum_time").toString());
                        }

                        if(summary.get("sum_in_late") != null) {
                            tvInLate.setText(summary.get("sum_in_late").toString());
                        }

                        if(summary.get("sum_out_soon") != null) {
                            tvOutSoon.setText(summary.get("sum_out_soon").toString());
                        }

                        if(summary.get("sum_ot") != null) {
                            tvOt.setText(summary.get("sum_ot").toString());
                        }

                        if(summary.get("total_number_time_all") != null) {
                            tvNumberTime.setText(summary.get("total_number_time_all").toString());
                        }


                    }
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Toast.makeText(CheckInActivity.this, "Call API Summary Staff Time Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendRequest(Integer idLogin, String date) {
        ApiService.apiService.getListCheckInOut(idLogin, date).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                //Toast.makeText(CheckInActivity.this, "Call API Success", Toast.LENGTH_SHORT).show();
                data = response.body();
                for (HashMap<String, Object> check_in : data.getData()) {
                    try {
                        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(check_in.get("check_in_day").toString());
                        Date check_in_time = new SimpleDateFormat("HH:mm:ss").parse("00:00:00");
                        Date check_out_time = new SimpleDateFormat("HH:mm:ss").parse("00:00:00");

                        if(check_in.get("check_out") != null) {
                            check_out_time = new SimpleDateFormat("HH:mm:ss").parse(check_in.get("check_out").toString());
                        }

                        if(check_in.get("check_in") != null) {
                            check_in_time = new SimpleDateFormat("HH:mm:ss").parse(check_in.get("check_in").toString());
                        }

                        arrCheckIn.add(new CheckInOut(date, check_in_time, check_out_time));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                checkInAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Toast.makeText(CheckInActivity.this, "Call API Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(CheckInActivity.this, MainActivity.class);
                startActivity(intent);
                return true;

            case R.id.info:
                Intent intent2 = new Intent(CheckInActivity.this, ProfileActivity.class);
                startActivity(intent2);
                return true;

            case R.id.logout:
                SharedPreferences preferences = getSharedPreferences("com.example.hr",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                editor.apply();
                finish();
                Intent intent3 = new Intent(CheckInActivity.this, LoginActivity.class);
                startActivity(intent3);
                Toast.makeText(CheckInActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void checkLogin() {
        SharedPreferences preferences = getSharedPreferences("com.example.hr", Context.MODE_PRIVATE);
        String json = preferences.getString("StaffLogin", "");

        if(json == "") {
            Intent intent = new Intent(CheckInActivity.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Vui lòng đăng nhập", Toast.LENGTH_SHORT).show();
        }
    }
}