package com.example.kwako;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityHomePage extends AppCompatActivity {

    //declare views
    ImageButton imageButtonHouse, imageButtonTenant, imageButtonAdmin;
    TextView tvHouseRegister, tvCustomerRegister,tvHomeLogin, textView9;

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
        tvHomeLogin=findViewById(R.id.tvHomeLogin);
        textView9=findViewById(R.id.textView9);
 
        findViewById(R.id.imageView2).setOnClickListener(view -> {startActivity(new Intent(this, MainActivity.class));});

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
        //open maps
        tvHomeLogin.setOnClickListener(v -> {
            Intent intent = new Intent(ActivityHomePage.this, UploadImages.class);
            startActivity(intent);
        });

        //bottom sheet trial
//        textView9.setOnClickListener(v -> {
//            Intent intent = new Intent(ActivityHomePage.this, VerifyPropertyDetails.class);
//            startActivity(intent);
//        });

    }


}

