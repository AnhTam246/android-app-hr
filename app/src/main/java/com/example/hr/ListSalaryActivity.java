package com.example.hr;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hr.adapter.ListContractAdapter;
import com.example.hr.adapter.ListSalaryDetailAdapter;
import com.example.hr.api.ApiService;
import com.example.hr.model.Contract;
import com.example.hr.model.Data;
import com.example.hr.model.Salary;
import com.example.hr.model.SalaryDetail;
import com.example.hr.model.StaffLogin;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSalaryActivity extends AppCompatActivity {

    private StaffLogin staffLogin;
    private Data data;
    private ArrayList<SalaryDetail> arrSalary;

    ListView lvSalary;
    private ListSalaryDetailAdapter listSalaryDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_salary);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.logo_main);    //Icon muốn hiện thị
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        checkLogin();

        data = new Data();
        lvSalary = (ListView) findViewById(R.id.lvSalary);
        arrSalary = new ArrayList<>();

        SharedPreferences preferences = getSharedPreferences("com.example.hr", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("StaffLogin", "");
        staffLogin = gson.fromJson(json, StaffLogin.class);

        listSalaryDetailAdapter = new ListSalaryDetailAdapter(this, R.layout.item_salary_detail, arrSalary);
        lvSalary.setAdapter(listSalaryDetailAdapter);
        getListSalary();
    }


    private void getListSalary() {
        ApiService.apiService.getListSalaryByStaffId(staffLogin.getId()).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                //Toast.makeText(LoginActivity.this, "Call API Success", Toast.LENGTH_SHORT).show();
                data = response.body();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                for (HashMap<String, Object> salaryDetail : data.getData()) {
                    try {
                        Gson gson = new Gson();
                        HashMap<String, Object> detail = gson.fromJson(salaryDetail.get("salaryDetail").toString(), HashMap.class);
                        double _id = Double.parseDouble(salaryDetail.get("id").toString());
                        double _staffId = Double.parseDouble(salaryDetail.get("staffId").toString());
                        int id = (int) _id;
                        int staffId = (int) _staffId;
                        double salaryMoney = Double.parseDouble(salaryDetail.get("salary").toString());
                        double baseSalaryContract = Double.parseDouble(salaryDetail.get("baseSalaryContract").toString());
                        double totalDayWork = Double.parseDouble(salaryDetail.get("totalDayWork").toString());
                        double totalSpecialDay = Double.parseDouble(salaryDetail.get("totalSpecialDay").toString());
                        double salaryOt = Double.parseDouble(salaryDetail.get("salaryOt").toString());
                        double totalAllowance = Double.parseDouble(salaryDetail.get("totalAllowance").toString());
                        double totalInsurance = Double.parseDouble(salaryDetail.get("totalInsurance").toString());
                        double totalTax = Double.parseDouble(salaryDetail.get("totalTax").toString());
                        double incomeTax = Double.parseDouble(salaryDetail.get("incomeTax").toString());
                        double taxableIncome = Double.parseDouble(salaryDetail.get("taxableIncome").toString());
                        double personalTax = Double.parseDouble(salaryDetail.get("personalTax").toString());
                        double salaryActuallyReceived = Double.parseDouble(salaryDetail.get("salaryActuallyReceived").toString());
                        Date startDate = simpleDateFormat.parse(detail.get("fromDate").toString());
                        Date endDate = simpleDateFormat.parse(detail.get("toDate").toString());
                        double _standardDays = Double.parseDouble(detail.get("standardDays").toString());
                        int standardDays = (int) _standardDays;
                        Salary salary = new Salary(startDate, endDate, standardDays);

                        SalaryDetail salaryDetail1 = new SalaryDetail(salaryMoney, baseSalaryContract, totalDayWork, totalSpecialDay, salaryOt, totalAllowance, totalInsurance, totalTax, incomeTax, taxableIncome, personalTax, salaryActuallyReceived, salary);

                        arrSalary.add(salaryDetail1);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                listSalaryDetailAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Toast.makeText(ListSalaryActivity.this, "Call API Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void checkLogin() {
        SharedPreferences preferences = getSharedPreferences("com.example.hr", Context.MODE_PRIVATE);
        String json = preferences.getString("StaffLogin", "");

        if (json == "") {
            Intent intent = new Intent(ListSalaryActivity.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Vui lòng đăng nhập", Toast.LENGTH_SHORT).show();
        }
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
                Intent intent = new Intent(ListSalaryActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.info:
                Intent intent2 = new Intent(ListSalaryActivity.this, ProfileActivity.class);
                startActivity(intent2);
                return true;

            case R.id.miSalary:
                startActivity(new Intent(ListSalaryActivity.this, ListSalaryActivity.class));
                return true;

            case R.id.logout:
                SharedPreferences preferences = getSharedPreferences("com.example.hr", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                editor.apply();
                finish();
                Intent intent3 = new Intent(ListSalaryActivity.this, LoginActivity.class);
                startActivity(intent3);
                Toast.makeText(ListSalaryActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}