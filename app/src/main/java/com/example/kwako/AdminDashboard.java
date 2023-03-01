package com.example.kwako;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class AdminDashboard extends AppCompatActivity {
    Button btnLogout;
    Button btnVerifyProperty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        //
        //logout button
        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(this, AdminLogin.class);
            startActivity(intent);
        });

        //btnVerifyProperty takes the admin to the verify property page.
        btnVerifyProperty.setOnClickListener(v -> {

        });
    }
}