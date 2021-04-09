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

import com.example.hr.adapter.SpecialDateAdapter;
import com.example.hr.api.ApiService;
import com.example.hr.model.Data;
import com.example.hr.model.SpecialDate;
import com.example.hr.model.StaffLogin;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestOverTimeActivity extends AppCompatActivity {
    private Data data;
    ListView lvSpecialDate;
    ArrayList<SpecialDate> arrRequestOt;
    SpecialDateAdapter specialDateAdapter;
    EditText y_request_ot;
    Button btnSearchRequestOt;
    TextView tvTitleRequestOt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_over_time);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.logo_main);    //Icon muốn hiện thị
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        SharedPreferences preferences = getSharedPreferences("com.example.hr", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("StaffLogin", "");
        StaffLogin staffLogin = gson.fromJson(json, StaffLogin.class);

        checkLogin();

        data = new Data();
        lvSpecialDate = (ListView) findViewById(R.id.lvSpecialDate);
        arrRequestOt = new ArrayList<>();
        String date = "2021-04-01";
        sendRequest(date, staffLogin.getId(), staffLogin.getDepartment());
        specialDateAdapter = new SpecialDateAdapter(this, R.layout.item_special_date, arrRequestOt);
        lvSpecialDate.setAdapter(specialDateAdapter);

        btnSearchRequestOt = findViewById(R.id.btnSearchRequestOt);

        btnSearchRequestOt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                y_request_ot = findViewById(R.id.y_request_ot);
                if(checkYearFormat(y_request_ot.getText().toString()) == false) {
                    Toast.makeText(RequestOverTimeActivity.this, "Vui lòng nhập đúng định dạng yyyy", Toast.LENGTH_SHORT).show();
                    return;
                }
                tvTitleRequestOt = findViewById(R.id.tvTitleRequestOt);
                if(y_request_ot.getText().toString().isEmpty()) {
                    Toast.makeText(RequestOverTimeActivity.this, "Vui lòng nhập để tìm kiếm", Toast.LENGTH_SHORT).show();
                } else {
                    lvSpecialDate = (ListView) findViewById(R.id.lvSpecialDate);
                    arrRequestOt = new ArrayList<>();
                    SharedPreferences preferences = getSharedPreferences("com.example.hr", Context.MODE_PRIVATE);
                    Gson gson = new Gson();
                    String json = preferences.getString("StaffLogin", "");
                    StaffLogin staffLogin = gson.fromJson(json, StaffLogin.class);
                    String date = y_request_ot.getText().toString() + "-01-01";
                    sendRequest(date, staffLogin.getId(), staffLogin.getDepartment());
                    specialDateAdapter = new SpecialDateAdapter(RequestOverTimeActivity.this, R.layout.item_special_date, arrRequestOt);
                    lvSpecialDate.setAdapter(specialDateAdapter);
                    tvTitleRequestOt.setText("Lịch Tăng Ca " + y_request_ot.getText().toString());
                }
            }
        });
    }

    private void sendRequest(String date, Integer staff_id, Integer department_id) {
        ApiService.apiService.getListRequestOt(date, staff_id, department_id).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                //Toast.makeText(SpecialDateActivity.this, "Call API Request Overtime Success", Toast.LENGTH_SHORT).show();
                data = response.body();

                for (HashMap<String, Object> special_date : data.getData()) {

                    if((int) Double.parseDouble(special_date.get("type_day").toString()) == 2) {
                        String list_staff_ot = "";
                        if(special_date.get("staff_ot") != null) {
                            list_staff_ot = special_date.get("staff_ot").toString();
                        }
                        String id_staff_str = staff_id.toString();
                        if(list_staff_ot.indexOf(id_staff_str) > -1 && (int) Double.parseDouble(special_date.get("is_approved").toString()) == 1) {
                            String day_special_from = special_date.get("day_special_from").toString();
                            String day_special_to = special_date.get("day_special_to").toString();
                            String note = special_date.get("note").toString();

                            arrRequestOt.add(new SpecialDate(day_special_from, day_special_to, note));
                        }
                    }
                }
                specialDateAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Toast.makeText(RequestOverTimeActivity.this, "Call API Request Over Time Error", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(RequestOverTimeActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.info:
                Intent intent2 = new Intent(RequestOverTimeActivity.this, ProfileActivity.class);
                startActivity(intent2);
                return true;

            case R.id.logout:
                SharedPreferences preferences = getSharedPreferences("com.example.hr", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                editor.apply();
                finish();
                Intent intent3 = new Intent(RequestOverTimeActivity.this, LoginActivity.class);
                startActivity(intent3);
                Toast.makeText(RequestOverTimeActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void checkLogin() {
        SharedPreferences preferences = getSharedPreferences("com.example.hr", Context.MODE_PRIVATE);
        String json = preferences.getString("StaffLogin", "");

        if(json == "") {
            Intent intent = new Intent(RequestOverTimeActivity.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Vui lòng đăng nhập", Toast.LENGTH_SHORT).show();
        }
    }

    static Boolean checkYearFormat(String date){
        if (date == null || !date.matches("\\d{4}"))
            return false;
        SimpleDateFormat format=new SimpleDateFormat("yyyy");
        try {
            format.parse(date);
            return true;
        }catch (ParseException e){
            return false;
        }
    }
}