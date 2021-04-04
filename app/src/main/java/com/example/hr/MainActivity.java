package com.example.hr;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView check_in_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.logo_main);    //Icon muốn hiện thị
        actionBar.setDisplayUseLogoEnabled(true);

        SharedPreferences preferences =
                getSharedPreferences("com.example.hr", Context.MODE_PRIVATE);

        Integer idLogin = preferences.getInt("idLogin", 0);
        clickCheckInOut();
//        String firstname = preferences.getString("firstname", "");
//        Log.e("firstname", firstname.toString());
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
}