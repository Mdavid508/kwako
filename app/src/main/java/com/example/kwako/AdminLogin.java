package com.example.kwako;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class AdminLogin extends AppCompatActivity {
    EditText edtEmail;
    EditText edtPassword;
    Button btnLogin;
    String email;
    String password;
    ProgressDialog loader;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        edtEmail =findViewById(R.id.adminEmail);
        edtPassword=findViewById(R.id.AdminPassword);
        btnLogin=findViewById(R.id.button_login);
        loader=new ProgressDialog(this);
        mAuth= FirebaseAuth.getInstance();

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
                Intent intent = new Intent(AdminLogin.this, AdminDashboard.class);
                startActivity(intent);
            });
        });



    }




       
}