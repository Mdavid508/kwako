package com.example.kwako;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ActivityHomePage extends AppCompatActivity {

    //declare views
    Button btnRegisterHouse, btnRegisterCustomer, btnLoginAdmin;
    TextView tvHouseRegister, tvCustomerRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //initialize views
        btnRegisterHouse = findViewById(R.id.btnRegisterHouse);
        btnRegisterCustomer = findViewById(R.id.btnRegisterCustomer);
        btnLoginAdmin = findViewById(R.id.btnLoginAdmin);
        tvHouseRegister = findViewById(R.id.tvHouseRegister);
        tvCustomerRegister = findViewById(R.id.tvCustomerRegister);

        // listen for btnRegister click
        btnRegisterCustomer.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityHomePage.this, ActivityCustomerRegistration.class);
            startActivity(intent);
        });
        // listen for btnRegister click
        btnRegisterHouse.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityHomePage.this, ActivityHouseOwnerRegister.class);
            startActivity(intent);
        });
        // listen for btnRegister click
        btnLoginAdmin.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityHomePage.this, AdminLogin.class);
            startActivity(intent);
        });


        // move to login activity if the user already has an account

        tvHouseRegister.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityHomePage.this, ActivityHouseOwnerLogin.class);
            startActivity(intent);
        });


        // move to login activity if the user already has an account

        tvCustomerRegister.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityHomePage.this, ActivityCustomerLogin.class);
            startActivity(intent);
        });



    }


}

