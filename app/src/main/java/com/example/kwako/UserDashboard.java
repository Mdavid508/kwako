package com.example.kwako;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserDashboard extends AppCompatActivity {
    Button bookedUnits;
    Button viewUnits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        bookedUnits = findViewById(R.id.buttonBookedUnits);
       bookedUnits.setOnClickListener(View->{
           Intent intent = new Intent(this,CustomerPayment.class);
           startActivity(intent);
       });
        viewUnits = findViewById(R.id.buttonViewUnits);
        viewUnits.setOnClickListener(View->{
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        });

    }
}