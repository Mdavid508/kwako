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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class ActivityHouseOwnerRegister extends AppCompatActivity {

    // declare views
    EditText edtUsername, edtEmail, edtPassword, edtCPassword, edtPhone;
    Button btnRegister;
    TextView tvLogin;
    String name, email, password, cPassword, phoneno;
    ProgressDialog loader;
    FirebaseAuth mAuth;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_owner_register);

        // initialize views
        edtUsername = findViewById(R.id.username);
        edtEmail = findViewById(R.id.email);
        edtPassword = findViewById(R.id.password);
        edtCPassword = findViewById(R.id.cpassword);
        edtPhone = findViewById(R.id.phoneno);
        btnRegister = findViewById(R.id.btnRegister);
        tvLogin = findViewById(R.id.tvRegister);
        loader = new ProgressDialog(this);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();


        // listen for btnRegister click
        btnRegister.setOnClickListener(v -> {
            // get user inputs
            name = edtUsername.getText().toString();
            email = edtEmail.getText().toString();
            phoneno=edtPhone.getText().toString();
            password = edtPassword.getText().toString();
            cPassword = edtCPassword.getText().toString();



            // check for empty fields
            if (email.isEmpty()) {
                edtEmail.setError("Email is required");
                edtEmail.requestFocus();
                return;
            }
            if (name.isEmpty()) {
                edtUsername.setError("Username is required");
                edtUsername.requestFocus();
                return;
            }
            if (phoneno.isEmpty()) {
                edtPhone.setError("Phone No is required");
                edtPhone.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                edtPassword.setError("Password is required");
                edtPassword.requestFocus();
                return;
            }

            if (cPassword.isEmpty()) {
                edtCPassword.setError("Confirm Password is required");
                edtCPassword.requestFocus();
                return;
            }
            // check if passwords match
            if (!password.equals(cPassword)) {
                edtCPassword.setError("Passwords do not match");
                edtCPassword.requestFocus();
                return;
            }
            // register user
            // show progress indicator
            loader.setMessage("Creating account. Please wait...");
            loader.setCanceledOnTouchOutside(false);
            loader.show();
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                // check if account created successfully
                if (!task.isSuccessful()) {
                    Toast.makeText(this, "Unable to create account. " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    // hide loader if showing
                    if (loader.isShowing()) loader.dismiss();
                    return;
                }
                ;

                // to save additional details of a user, you need to save this data to a Firestore database
                // hashmap object that will be saved to firebase
                HashMap<String, String> details = new HashMap<>();
                details.put("username", name);
                details.put("email", email);
                details.put("password", password);

                db.collection("Users").add(details).addOnCompleteListener(task2 -> {
                    // hide loader if showing
                    if (loader.isShowing()) loader.dismiss();
                    if (!task2.isSuccessful()) {
                        Toast.makeText(this, "Unable to save user to firebase: " + task2.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // save successful, move to mainActivity
                    Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ActivityHouseOwnerRegister.this, HouseOwnerDashboard.class);
                    startActivity(intent);
                });
            });
        });

        // move to login activity if the user already has an account
        tvLogin.setOnClickListener(view -> {
            Intent intent = new Intent(ActivityHouseOwnerRegister.this, ActivityHouseOwnerLogin.class);
            startActivity(intent);
        });

    }
}