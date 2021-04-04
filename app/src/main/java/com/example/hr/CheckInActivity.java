package com.example.hr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.hr.api.ApiService;
import com.example.hr.model.CheckInOut;
import com.example.hr.model.Data;
import com.example.hr.model.PostCheckIn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckInActivity extends AppCompatActivity {
    private Data data;
    ListView lvCheckin;
    ArrayList<CheckInOut> arrCheckIn;
    CheckInAdapter checkInAdapter;
    EditText y_m;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        SharedPreferences preferences = getSharedPreferences("com.example.hr", Context.MODE_PRIVATE);
        Integer idLogin = preferences.getInt("idLogin", 0);
        data = new Data();
        lvCheckin = (ListView) findViewById(R.id.lvCheckIn);
        arrCheckIn = new ArrayList<>();
        String date = "2021-04-01";
        sendRequest(idLogin, date);
        checkInAdapter = new CheckInAdapter(this, R.layout.item_check_in_out, arrCheckIn);
        lvCheckin.setAdapter(checkInAdapter);

        btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                y_m = findViewById(R.id.y_m);
                lvCheckin = (ListView) findViewById(R.id.lvCheckIn);
                arrCheckIn = new ArrayList<>();
                String date = y_m.getText().toString();
                sendRequest(idLogin, date);
                checkInAdapter = new CheckInAdapter(CheckInActivity.this, R.layout.item_check_in_out, arrCheckIn);
                lvCheckin.setAdapter(checkInAdapter);
            }
        });
    }

    private void sendRequest(Integer idLogin, String date) {
        System.out.println(idLogin);
        ApiService.apiService.getListCheckInOut(5, date).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Toast.makeText(CheckInActivity.this, "Call API Success", Toast.LENGTH_SHORT).show();
                data = response.body();
                for (HashMap<String, Object> check_in : data.getData()) {
                    try {
                        arrCheckIn.add(new CheckInOut(
                                new SimpleDateFormat("dd-MM-yyyy").parse(check_in.get("check_in_day").toString()),
                                new SimpleDateFormat("HH:mm:ss").parse(check_in.get("check_in").toString()),
                                new SimpleDateFormat("HH:mm:ss").parse(check_in.get("check_out").toString())
                        ));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(data.getData());
                checkInAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Toast.makeText(CheckInActivity.this, "Call API Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}