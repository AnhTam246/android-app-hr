package com.example.hr;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hr.api.ApiService;
import com.example.hr.model.Data;
import com.example.hr.model.Staff;
import com.example.hr.model.StaffLogin;
import com.google.gson.Gson;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtPass;
    private Button btnLogin;

    private Data data;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.logo_main);    //Icon muốn hiện thị
        actionBar.setDisplayUseLogoEnabled(true);

        txtEmail = findViewById(R.id.txtEmail);
        txtPass = findViewById(R.id.txtPass);
        btnLogin = findViewById(R.id.btnLogin);

        data = new Data();
        getListStaff();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLogin();
            }
        });
    }

    private void clickLogin() {
        String email = txtEmail.getText().toString().trim();
        String password = txtPass.getText().toString().trim();
        if(email.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Nhập email và mật khẩu!", Toast.LENGTH_SHORT).show();
            return;
        }
//        Integer idLogin = 0;
        StaffLogin staffLogin = null;

        if(data.getData() == null || data.getData().isEmpty()) {
            return;
        }

        boolean check = false;
        for (HashMap<String, Object> staff : data.getData()) {
            if(staff.get("email").toString().equals(email) && staff.get("password").toString().equals(md5(password)) && Boolean.parseBoolean(staff.get("status").toString()) == false) {
                check = true;
                staffLogin = new StaffLogin(
                        (int) Double.parseDouble(staff.get("id").toString()),
                        staff.get("code").toString(),
                        staff.get("firstname").toString(),
                        staff.get("lastname").toString(),
                        (int) Double.parseDouble(staff.get("department").toString()),
                        Boolean.parseBoolean(staff.get("isManager").toString()),
                        Boolean.parseBoolean(staff.get("status").toString())
                );

                break;
            }
        }

        if(check == true) {
            SharedPreferences preferences =
                    getSharedPreferences("com.example.hr", Context.MODE_PRIVATE);

            SharedPreferences.Editor prefsEditor = preferences.edit();
            Gson gson = new Gson();
            String json = gson.toJson(staffLogin);
            prefsEditor.putString("StaffLogin", json);
            prefsEditor.commit();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(LoginActivity.this, "Email hoặc mật khẩu đăng nhập không chính xác!", Toast.LENGTH_SHORT).show();
        }
    }

    private void getListStaff() {
        ApiService.apiService.getListStaff().enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                //Toast.makeText(LoginActivity.this, "Call API Success", Toast.LENGTH_SHORT).show();
                data = response.body();
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Call API Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}