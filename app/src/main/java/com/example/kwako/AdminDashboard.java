package com.example.kwako;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminDashboard extends AppCompatActivity {
    Button btnLogout;
    Button btnVerifyProperty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        //intialize views
        btnLogout=findViewById(R.id.adminLogout);
        btnVerifyProperty= findViewById(R.id.verifyProperty);




        //btnVerifyProperty takes the admin to the verify property page.
        btnVerifyProperty.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDashboard.this, VerifyPropertyDetails.class);
            startActivity(intent);
        });

        //logout button
        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDashboard.this, AdminLogin.class);
            startActivity(intent);
        });
    }
}