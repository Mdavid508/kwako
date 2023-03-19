package com.example.kwako;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class HouseOwnerDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_owner_dashboard);

        Button addProperty= findViewById(R.id.addProperty);

        addProperty.setOnClickListener(view ->{
            Intent intent = new Intent(HouseOwnerDashboard.this, PostProperty.class);
            startActivity(intent);
        });

        //logout from the activity
        Button houseOwnerLogout = findViewById(R.id.logout);

        houseOwnerLogout.setOnClickListener(view -> {
            Intent intent = new Intent(HouseOwnerDashboard.this, ActivityHouseOwnerLogin.class);
            startActivity(intent);
        });

    }
}