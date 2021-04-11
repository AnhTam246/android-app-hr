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
import com.example.hr.api.ApiService;
import com.example.hr.model.Contract;
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


public class ListContractActivity extends AppCompatActivity {

    private HashMap<String, Object> staff;
    private StaffLogin staffLogin;
    private Data data;
    private ArrayList<Contract> arrContract;

    ListView lvContract;
    private ListContractAdapter listContractAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contract);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.logo_main);    //Icon muốn hiện thị
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        checkLogin();

        data = new Data();
        lvContract = (ListView) findViewById(R.id.lvContract);
        arrContract = new ArrayList<>();

        SharedPreferences preferences = getSharedPreferences("com.example.hr", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("StaffLogin", "");
        staffLogin = gson.fromJson(json, StaffLogin.class);

        listContractAdapter = new ListContractAdapter(this, R.layout.item_contract, arrContract);
        lvContract.setAdapter(listContractAdapter);
        getListContract();
    }


    public void checkLogin() {
        SharedPreferences preferences = getSharedPreferences("com.example.hr", Context.MODE_PRIVATE);
        String json = preferences.getString("StaffLogin", "");

        if (json == "") {
            Intent intent = new Intent(ListContractActivity.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Vui lòng đăng nhập", Toast.LENGTH_SHORT).show();
        }
    }

    private void getListContract() {
        ApiService.apiService.getListContractByStaff(staffLogin.getId()).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                //Toast.makeText(LoginActivity.this, "Call API Success", Toast.LENGTH_SHORT).show();
                data = response.body();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Gson gson = new Gson();

                for (HashMap<String, Object> contract : data.getData()) {
                    try {
                        System.out.println(contract.get("id").toString());
                        double _id = Double.parseDouble(contract.get("id").toString());
                        double _staffId = Double.parseDouble(contract.get("staffId").toString());
                        int id = (int) _id;
                        int staffId = (int) _staffId;
                        Date startDate = simpleDateFormat.parse(contract.get("startDate").toString());
                        Date endDate = simpleDateFormat.parse(contract.get("endDate").toString());
                        Date stopDate = simpleDateFormat.parse(contract.get("stopDate").toString());
                        Date createAt = simpleDateFormat.parse(contract.get("createAt").toString());

                        double baseSalary = Double.parseDouble(contract.get("baseSalary").toString());

                        arrContract.add(new Contract(id, staffId, startDate, endDate, stopDate, baseSalary, createAt));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                listContractAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Toast.makeText(ListContractActivity.this, "Call API Error", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(ListContractActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.info:
                Intent intent2 = new Intent(ListContractActivity.this, ProfileActivity.class);
                startActivity(intent2);
                return true;
            case R.id.miContract:
                startActivity(new Intent(ListContractActivity.this, ListContractActivity.class));
                return true;
            case R.id.miSalary:
                startActivity(new Intent(ListContractActivity.this, ListSalaryActivity.class));
                return true;

            case R.id.logout:
                SharedPreferences preferences = getSharedPreferences("com.example.hr", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                editor.apply();
                finish();
                Intent intent3 = new Intent(ListContractActivity.this, LoginActivity.class);
                startActivity(intent3);
                Toast.makeText(ListContractActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}