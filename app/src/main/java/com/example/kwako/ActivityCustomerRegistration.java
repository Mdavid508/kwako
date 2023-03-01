package com.example.kwako;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;

public class ActivityCustomerRegistration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);


        EditText emailEditText = findViewById(R.id.editTextEmail);
        EditText usernameEditText = findViewById(R.id.editTextUsername);
        EditText phoneNumberEditText = findViewById(R.id.editTextPhoneNo);
        EditText passwordEditText = findViewById(R.id.editTextPassword);
        EditText repeatPasswordEditText = findViewById(R.id.editTextRepeatPassword);
        Button btnLogin = findViewById(R.id.btnRegister);
    }
}