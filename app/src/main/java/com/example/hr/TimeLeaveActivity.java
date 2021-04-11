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

import com.example.hr.adapter.TimeLeaveAdapter;
import com.example.hr.api.ApiService;
import com.example.hr.model.Data;
import com.example.hr.model.StaffLogin;
import com.example.hr.model.TimeLeave;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.hr.MainActivity.checkDateFormat;

public class TimeLeaveActivity extends AppCompatActivity {
    private Data data;
    ListView lvTimeLeave;
    ArrayList<TimeLeave> arrTimeLeave;
    TimeLeaveAdapter timeLeaveAdapter;
    EditText y_m_time_leave;
    Button btnSearchTimeLeave;
    TextView tvTitleTimeLeave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_leave);

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
        lvTimeLeave = (ListView) findViewById(R.id.lvTimeLeave);
        arrTimeLeave = new ArrayList<>();
        String date = "2021-04-01";
        sendRequest(staffLogin.getId(), date);
        timeLeaveAdapter = new TimeLeaveAdapter(this, R.layout.item_time_leave, arrTimeLeave);
        lvTimeLeave.setAdapter(timeLeaveAdapter);

        btnSearchTimeLeave = findViewById(R.id.btnSearchTimeLeave);

        btnSearchTimeLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                y_m_time_leave = findViewById(R.id.y_m_time_leave);
                if(checkDateFormat(y_m_time_leave.getText().toString()) == false) {
                    Toast.makeText(TimeLeaveActivity.this, "Vui lòng nhập đúng định dạng yyyy-mm", Toast.LENGTH_SHORT).show();
                    return;
                }
                tvTitleTimeLeave = findViewById(R.id.tvTitleTimeLeave);
                if(y_m_time_leave.getText().toString().isEmpty()) {
                    Toast.makeText(TimeLeaveActivity.this, "Vui lòng nhập để tìm kiếm", Toast.LENGTH_SHORT).show();
                } else {
                    lvTimeLeave = (ListView) findViewById(R.id.lvTimeLeave);
                    arrTimeLeave = new ArrayList<>();
                    String date = y_m_time_leave.getText().toString() + "-01";
                    sendRequest(staffLogin.getId(), date);
                    timeLeaveAdapter = new TimeLeaveAdapter(TimeLeaveActivity.this, R.layout.item_time_leave, arrTimeLeave);
                    lvTimeLeave.setAdapter(timeLeaveAdapter);
                    tvTitleTimeLeave.setText("Bổ Sung Công Phép " + y_m_time_leave.getText().toString());
                }
            }
        });
    }

    private void sendRequest(Integer id, String date) {
        ApiService.apiService.getDetailTimeLeave(date, id).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                //Toast.makeText(TimeLeaveActivity.this, "Call API Time leave Success", Toast.LENGTH_SHORT).show();
                data = response.body();
                for (HashMap<String, Object> time_leave : data.getData()) {
                    String day_time_leave = time_leave.get("day_time_leave").toString();
                    String number_time = "";
                    if(time_leave.get("time") != null) {
                        number_time = time_leave.get("time").toString();
                    }

                    Integer type = (int) Double.parseDouble(time_leave.get("type").toString());
                    Integer is_approved = (int) Double.parseDouble(time_leave.get("is_approved").toString());

                    arrTimeLeave.add(new TimeLeave(day_time_leave, type, number_time, is_approved));
                }
                timeLeaveAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Toast.makeText(TimeLeaveActivity.this, "Call API Time leave Error", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(TimeLeaveActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.info:
                Intent intent2 = new Intent(TimeLeaveActivity.this, ProfileActivity.class);
                startActivity(intent2);
                return true;
            case R.id.miContract:
                startActivity(new Intent(TimeLeaveActivity.this, ListContractActivity.class));
                return true;
            case R.id.miSalary:
                startActivity(new Intent(TimeLeaveActivity.this, ListSalaryActivity.class));
                return true;

            case R.id.logout:
                SharedPreferences preferences = getSharedPreferences("com.example.hr", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                editor.apply();
                finish();
                Intent intent3 = new Intent(TimeLeaveActivity.this, LoginActivity.class);
                startActivity(intent3);
                Toast.makeText(TimeLeaveActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void checkLogin() {
        SharedPreferences preferences = getSharedPreferences("com.example.hr", Context.MODE_PRIVATE);
        String json = preferences.getString("StaffLogin", "");

        if(json == "") {
            Intent intent = new Intent(TimeLeaveActivity.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Vui lòng đăng nhập", Toast.LENGTH_SHORT).show();
        }
    }
}