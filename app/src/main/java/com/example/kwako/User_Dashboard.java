package com.example.kwako;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class User_Dashboard extends AppCompatActivity {
    //declare views
    Button btnViewUnits, btnBookedUnits, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        //initialize units
        btnViewUnits=findViewById(R.id.buttonViewUnits);
        btnBookedUnits=findViewById(R.id.buttonBookedUnits);
        btnLogout=findViewById(R.id.buttonLogout);

        //logout button
        btnLogout.setOnClickListener(view -> {
            Intent intent = new Intent(User_Dashboard.this, ActivityCustomerLogin.class);
            startActivity(intent);
        });



    }
}