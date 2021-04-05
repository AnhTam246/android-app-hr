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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hr.model.StaffLogin;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {
    ImageView check_in_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.logo_main);
        actionBar.setDisplayUseLogoEnabled(true);

        SharedPreferences preferences =
                getSharedPreferences("com.example.hr", Context.MODE_PRIVATE);

        Integer idLogin = preferences.getInt("idLogin", 0);
        clickCheckInOut();
        checkLogin();
//        String firstname = preferences.getString("firstname", "");
//        Log.e("firstname", firstname.toString());
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
            case R.id.info:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.logout:
                SharedPreferences preferences = getSharedPreferences("com.example.hr",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                editor.apply();
                finish();
                Intent intent2 = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent2);
                Toast.makeText(MainActivity.this, "Logout Success", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void clickCheckInOut() {
        check_in_out = findViewById(R.id.check_in_out);

        check_in_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CheckInActivity.class);
                startActivity(intent);
            }
        });
    }


    static Boolean checkDateFormat(String date){
        if (date == null || !date.matches("\\d{4}-\\d{2}"))
            return false;
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM");
        try {
            format.parse(date);
            return true;
        }catch (ParseException e){
            return false;
        }
    }

    public void checkLogin() {
        SharedPreferences preferences = getSharedPreferences("com.example.hr", Context.MODE_PRIVATE);
        String json = preferences.getString("StaffLogin", "");

        if(json == "") {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Vui lòng đăng nhập", Toast.LENGTH_SHORT).show();
        }
    }
}