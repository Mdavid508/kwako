package com.example.kwako;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class ActivityCustomerLogin extends AppCompatActivity {
    EditText edtEmail;
    EditText edtPassword;
    Button btnLogin;
    String email;
    String password;
    ProgressDialog loader;
    TextView tvRegister;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        // TODO: Add logic for button click event

        // initialize views
        edtEmail = findViewById(R.id.editTextEmail);
        edtPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.textViewRegister);
        loader = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();


        // listen for btnRegister click
        btnLogin.setOnClickListener(v -> {
            // get user inputs
            email = edtEmail.getText().toString();
            password = edtPassword.getText().toString();

            // check for empty fields
            if (email.isEmpty()) {
                edtEmail.setError("Email is required");
                edtEmail.requestFocus();
                return;
            }
            if (password.isEmpty()) {
                edtPassword.setError("Password is required");
                edtPassword.requestFocus();
                return;
            }


            // Login user
            loader.setMessage("Login in progress. Please wait...");
            loader.setCanceledOnTouchOutside(false);
            loader.show();

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                // hide loader if is showing
                if (loader.isShowing()) loader.dismiss();
                // check if login is successful
                if (!task.isSuccessful()) {
                    Toast.makeText(this, "Unable to Login " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }

                // Login was successful. Move to main activity
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
            });
        });

        // move to Register activity if user has no account
        tvRegister.setOnClickListener(view -> {
            Intent intent = new Intent(ActivityCustomerLogin.this, ActivityCustomerRegistration.class);
            startActivity(intent);
        });

    }
    }