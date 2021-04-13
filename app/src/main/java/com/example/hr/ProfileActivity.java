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
import android.widget.TextView;
import android.widget.Toast;

import com.example.hr.api.ApiService;
import com.example.hr.model.DataOneObject;
import com.example.hr.model.StaffLogin;
import com.google.gson.Gson;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    private DataOneObject dataOneObject;
    private HashMap<String, Object> staff;

    CircleImageView profile_image_female;
    CircleImageView profile_image_male;
    TextView tvNameProfile;
    TextView tvDepartmentProfile;
    TextView tvCodeProfile;
    TextView tvDobProfile;
    TextView tvNumberPhoneProfile;
    TextView tvEmailProfile;
    TextView tvJoinedAtProfile;
    TextView tvDayOfLeaveProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.logo_main);    //Icon muốn hiện thị
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

//        profile_image_female = findViewById(R.id.profile_image_female);
//        profile_image_female.setVisibility(View.GONE);

        SharedPreferences preferences = getSharedPreferences("com.example.hr", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("StaffLogin", "");
        StaffLogin staffLogin = gson.fromJson(json, StaffLogin.class);

        checkLogin();

        sendRequest(staffLogin.getId());

    }

    private void sendRequest(Integer id) {
        ApiService.apiService.getProfileStaff(id).enqueue(new Callback<DataOneObject>() {
            @Override
            public void onResponse(Call<DataOneObject> call, Response<DataOneObject> response) {
                System.out.println(response.body());
                dataOneObject = response.body();

                staff = dataOneObject.getData();

                profile_image_female = findViewById(R.id.profile_image_female);
                profile_image_male = findViewById(R.id.profile_image_male);
                tvNameProfile = findViewById(R.id.tvNameProfile);
                tvDepartmentProfile = findViewById(R.id.tvDepartmentProfile);
                tvCodeProfile = findViewById(R.id.tvCodeProfile);
                tvNumberPhoneProfile = findViewById(R.id.tvNumberPhoneProfile);
                tvEmailProfile = findViewById(R.id.tvEmailProfile);
                tvJoinedAtProfile = findViewById(R.id.tvJoinedAtProfile);
                tvDayOfLeaveProfile = findViewById(R.id.tvDayOfLeaveProfile);
                tvDobProfile = findViewById(R.id.tvDobProfile);

                if ((int) Double.parseDouble(staff.get("gender").toString()) == 1) {
                    profile_image_male.setVisibility(View.VISIBLE);
                } else {
                    profile_image_female.setVisibility(View.VISIBLE);
                }

                tvNameProfile.setText(staff.get("firstname").toString() + " " + staff.get("lastname").toString());
                tvDepartmentProfile.setText("-- " + staff.get("department_name").toString() + " --");

                if (Boolean.parseBoolean(staff.get("is_manager").toString()) == true) {
                    tvDepartmentProfile.setText("-- " + staff.get("department_name").toString() + " -- Quản lý --");
                } else {
                    tvDepartmentProfile.setText("-- " + staff.get("department_name").toString() + " -- Nhân viên --");
                }

                tvCodeProfile.setText(staff.get("code").toString());
                tvDobProfile.setText(staff.get("dob").toString());
                tvNumberPhoneProfile.setText(staff.get("phone_number").toString());
                tvEmailProfile.setText(staff.get("email").toString());
                tvJoinedAtProfile.setText(staff.get("joined_at").toString());
                tvDayOfLeaveProfile.setText(staff.get("day_of_leave").toString());

            }

            @Override
            public void onFailure(Call<DataOneObject> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Call API Profile Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void checkLogin() {
        SharedPreferences preferences = getSharedPreferences("com.example.hr", Context.MODE_PRIVATE);
        String json = preferences.getString("StaffLogin", "");

        if (json == "") {
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
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
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
                return true;

            case R.id.info:
                Intent intent2 = new Intent(ProfileActivity.this, ProfileActivity.class);
                startActivity(intent2);
                return true;

            case R.id.miSalary:
                startActivity(new Intent(ProfileActivity.this, ListSalaryActivity.class));
                return true;

            case R.id.logout:
                SharedPreferences preferences = getSharedPreferences("com.example.hr", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                editor.apply();
                finish();
                Intent intent3 = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent3);
                Toast.makeText(ProfileActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}