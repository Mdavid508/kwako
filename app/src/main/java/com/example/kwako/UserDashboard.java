package com.example.kwako;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class UserDashboard extends AppCompatActivity {
    Button bookedUnits;
    Button viewUnits, logout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

       bookedUnits = findViewById(R.id.btnBookedUnits);
       bookedUnits.setOnClickListener(View->{
           Intent intent = new Intent(this,CustomerPayment.class);
           startActivity(intent);
       });
        viewUnits = findViewById(R.id.btnView);
        viewUnits.setOnClickListener(View->{
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        });
        
        //button onclick to logout
        logout = findViewById(R.id.btnLogout);
        logout.setOnClickListener(v -> {
            Intent intent = new Intent(this, ActivityCustomerLogin.class);
            startActivity(intent);
        });
        


    }
}