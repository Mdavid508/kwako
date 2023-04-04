package com.example.kwako;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class HouseOwnerDashboard extends AppCompatActivity {
    Button addProperty, houseOwnerLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_owner_dashboard);

        addProperty = findViewById(R.id.addProperty);
        //logout from the activity
        houseOwnerLogout = findViewById(R.id.logout);

        addProperty.setOnClickListener(view -> {
            Intent intent = new Intent(HouseOwnerDashboard.this, PostProperty.class);
            startActivity(intent);
        });

        houseOwnerLogout.setOnClickListener(view -> {
            Intent intent = new Intent(HouseOwnerDashboard.this, ActivityHouseOwnerLogin.class);
            startActivity(intent);
            finish();
        });

    }
}