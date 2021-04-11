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

import com.example.hr.adapter.TransferAdapter;
import com.example.hr.api.ApiService;
import com.example.hr.model.StaffLogin;
import com.example.hr.model.Transfer;
import com.example.hr.model.Data;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.hr.MainActivity.checkDateFormat;

public class TransferActivity extends AppCompatActivity {

    private Data data;
    ListView lvTransfers;
    ArrayList<Transfer> arrTransfer;
    TransferAdapter transferAdapter;
    EditText y_m;
    Button btnSearch;
    TextView tvTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

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
        lvTransfers = (ListView) findViewById(R.id.lvTransfer);
        arrTransfer = new ArrayList<>();
        String date = "2021-04-01";
        sendRequest(staffLogin.getDepartment(), date);

        transferAdapter = new TransferAdapter(this, R.layout.detailtransfer, arrTransfer);
        lvTransfers.setAdapter(transferAdapter);

        btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                y_m = findViewById(R.id.y_m_time_leave);
                if(checkDateFormat(y_m.getText().toString()) == false) {
                    Toast.makeText(TransferActivity.this, "Vui lòng nhập đúng định dạng yyyy-mm", Toast.LENGTH_SHORT).show();
                    return;
                }
                tvTitle = findViewById(R.id.tvLitleTrans);
                if(y_m.getText().toString().isEmpty()) {
                    Toast.makeText(TransferActivity.this, "Vui lòng nhập để tìm kiếm", Toast.LENGTH_SHORT).show();
                } else {
                    lvTransfers = (ListView) findViewById(R.id.lvTransfer);
                    arrTransfer = new ArrayList<>();
                    String date = y_m.getText().toString() + "-01";
                    sendRequest(staffLogin.getDepartment(), date);
                    transferAdapter = new TransferAdapter(TransferActivity.this, R.layout.detailtransfer, arrTransfer);
                    lvTransfers.setAdapter(transferAdapter);
                    tvTitle.setText("Danh Sách Chuyển Đổi " + y_m.getText().toString());
                }
            }
        });
    }

    private void sendRequest(Integer depart, String date) {
        ApiService.apiService.getListTransfer(date, depart).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Toast.makeText(TransferActivity.this, "Call API Success", Toast.LENGTH_SHORT).show();
                data = response.body();
                for (HashMap<String, Object> trans_fer : data.getData()) {
//                    try {
                    String staff_transfer = trans_fer.get("staff_transfer").toString();
                    String old_department_name = trans_fer.get("old_department_name").toString();
                    String new_department_name = trans_fer.get("new_department_name").toString();
                    Boolean old_manager_approved =  Boolean.parseBoolean(trans_fer.get("old_manager_approved").toString());
                    Boolean new_manager_approved =  Boolean.parseBoolean(trans_fer.get("new_manager_approved").toString());
                    Boolean manager_approved =  Boolean.parseBoolean(trans_fer.get("manager_approved").toString());

                    arrTransfer.add(new Transfer( staff_transfer,old_department_name,
                            new_department_name, old_manager_approved,
                            new_manager_approved, manager_approved));
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
                }
                transferAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Toast.makeText(TransferActivity.this, "Call API Error", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(TransferActivity.this, MainActivity.class);
                startActivity(intent);
                return true;

            case R.id.info:
                Intent intent2 = new Intent(TransferActivity.this, ProfileActivity.class);
                startActivity(intent2);
                return true;

            case R.id.logout:
                SharedPreferences preferences = getSharedPreferences("com.example.hr",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                editor.apply();
                finish();
                Intent intent3 = new Intent(TransferActivity.this, LoginActivity.class);
                startActivity(intent3);
                Toast.makeText(TransferActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void checkLogin() {
        SharedPreferences preferences = getSharedPreferences("com.example.hr", Context.MODE_PRIVATE);
        String json = preferences.getString("StaffLogin", "");

        if(json == "") {
            Intent intent = new Intent(TransferActivity.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Vui lòng đăng nhập", Toast.LENGTH_SHORT).show();
        }
    }
}