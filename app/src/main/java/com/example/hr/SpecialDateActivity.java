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
import com.example.hr.adapter.SpecialDateAdapter;
import com.example.hr.adapter.TimeLeaveAdapter;
import com.example.hr.api.ApiService;
import com.example.hr.model.Data;
import com.example.hr.model.SpecialDate;
import com.example.hr.model.StaffLogin;
import com.example.hr.model.TimeLeave;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.hr.MainActivity.checkDateFormat;

public class SpecialDateActivity extends AppCompatActivity {

    private Data data;
    ListView lvSpecialDate;
    ArrayList<SpecialDate> arrSpecialDate;
    SpecialDateAdapter specialDateAdapter;
    EditText y_special_date;
    Button btnSearchSpecialDate;
    TextView tvTitleSpecialDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_date);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.logo_main);    //Icon muốn hiện thị
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        checkLogin();

        data = new Data();
        lvSpecialDate = (ListView) findViewById(R.id.lvSpecialDate);
        arrSpecialDate = new ArrayList<>();
        String date = "2021-04-01";
        sendRequest(date);
        specialDateAdapter = new SpecialDateAdapter(this, R.layout.item_special_date, arrSpecialDate);
        lvSpecialDate.setAdapter(specialDateAdapter);

        btnSearchSpecialDate = findViewById(R.id.btnSearchSpecialDate);

        btnSearchSpecialDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                y_special_date = findViewById(R.id.y_special_date);
                if (checkYearFormat(y_special_date.getText().toString()) == false) {
                    Toast.makeText(SpecialDateActivity.this, "Vui lòng nhập đúng định dạng yyyy", Toast.LENGTH_SHORT).show();
                    return;
                }
                tvTitleSpecialDate = findViewById(R.id.tvTitleSpecialDate);
                if (y_special_date.getText().toString().isEmpty()) {
                    Toast.makeText(SpecialDateActivity.this, "Vui lòng nhập để tìm kiếm", Toast.LENGTH_SHORT).show();
                } else {
                    lvSpecialDate = (ListView) findViewById(R.id.lvSpecialDate);
                    arrSpecialDate = new ArrayList<>();
                    String date = y_special_date.getText().toString() + "-01-01";
                    sendRequest(date);
                    specialDateAdapter = new SpecialDateAdapter(SpecialDateActivity.this, R.layout.item_special_date, arrSpecialDate);
                    lvSpecialDate.setAdapter(specialDateAdapter);
                    tvTitleSpecialDate.setText("Danh Sách Ngày Lễ " + y_special_date.getText().toString());
                }
            }
        });
    }

    private void sendRequest(String date) {
        ApiService.apiService.getListSpecialDate(date).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                //Toast.makeText(SpecialDateActivity.this, "Call API Special Date Success", Toast.LENGTH_SHORT).show();
                data = response.body();

                for (HashMap<String, Object> special_date : data.getData()) {
                    if ((int) Double.parseDouble(special_date.get("type_day").toString()) == 1) {
                        String day_special_from = special_date.get("day_special_from").toString();
                        String day_special_to = special_date.get("day_special_to").toString();
                        String note = special_date.get("note").toString();

                        arrSpecialDate.add(new SpecialDate(day_special_from, day_special_to, note));
                    }
                }
                specialDateAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Toast.makeText(SpecialDateActivity.this, "Call API Special Date Error", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(SpecialDateActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.info:
                Intent intent2 = new Intent(SpecialDateActivity.this, ProfileActivity.class);
                startActivity(intent2);
                return true;
            case R.id.miContract:
                startActivity(new Intent(SpecialDateActivity.this, ListContractActivity.class));
                return true;
            case R.id.miSalary:
                startActivity(new Intent(SpecialDateActivity.this, ListSalaryActivity.class));
                return true;

            case R.id.logout:
                SharedPreferences preferences = getSharedPreferences("com.example.hr", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                editor.apply();
                finish();
                Intent intent3 = new Intent(SpecialDateActivity.this, LoginActivity.class);
                startActivity(intent3);
                Toast.makeText(SpecialDateActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void checkLogin() {
        SharedPreferences preferences = getSharedPreferences("com.example.hr", Context.MODE_PRIVATE);
        String json = preferences.getString("StaffLogin", "");

        if (json == "") {
            Intent intent = new Intent(SpecialDateActivity.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Vui lòng đăng nhập", Toast.LENGTH_SHORT).show();
        }
    }

    static Boolean checkYearFormat(String date) {
        if (date == null || !date.matches("\\d{4}"))
            return false;
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        try {
            format.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}