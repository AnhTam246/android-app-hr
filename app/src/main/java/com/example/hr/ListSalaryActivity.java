package com.example.hr;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ListSalaryActivity extends AppCompatActivity {

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