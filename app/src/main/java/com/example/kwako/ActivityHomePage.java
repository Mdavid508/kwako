package com.example.kwako;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ActivityHomePage extends AppCompatActivity {

    //declare views
    ImageButton imageButtonHouse, imageButtonTenant, imageButtonAdmin;
    TextView tvHouseRegister, tvCustomerRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //initialize views
        imageButtonHouse = findViewById(R.id.imageButtonHouseOwner);
        imageButtonTenant = findViewById(R.id.imageButtonTenant);
        imageButtonAdmin = findViewById(R.id.imageButtonAdmin);
        tvHouseRegister = findViewById(R.id.tvHouseRegister);
        tvCustomerRegister = findViewById(R.id.tvCustomerRegister);

        // listen for btnRegister click
        imageButtonTenant.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityHomePage.this, ActivityCustomerLogin.class);
            startActivity(intent);
        });
        // listen for btnRegister click
        imageButtonHouse.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityHomePage.this, ActivityHouseOwnerLogin.class);
            startActivity(intent);
        });
        // listen for btnRegister click
        imageButtonAdmin.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityHomePage.this, AdminLogin.class);
            startActivity(intent);
        });


        // move to login activity if the user already has an account

        tvHouseRegister.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityHomePage.this, ActivityHouseOwnerRegister.class);
            startActivity(intent);
        });


        // move to login activity if the user already has an account

        tvCustomerRegister.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityHomePage.this, ActivityCustomerRegistration.class);
            startActivity(intent);
        });



    }


}

