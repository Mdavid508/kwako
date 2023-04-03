package com.example.kwako;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kwako.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

// declare views
public class ActivityHouseOwnerLogin extends AppCompatActivity {
    EditText edtEmail;
    EditText edtPassword;
    Button btnLogin;
    String email;
    String password;
    ProgressDialog loader;
    TextView tvRegister;
    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_owner_login);
        // TODO: Add logic for button click event

        // initialize views
        edtEmail = findViewById(R.id.email);
        edtPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);
        loader = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

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

                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                db.collection("Users").document(firebaseUser.getUid()).get().addOnCompleteListener(task2 -> {
                    if (!task2.isSuccessful()){
                        Toast.makeText(this, "Unable to get your saved data: "+task2.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    // save user session and proceed to HouseOwner dashboard
                    User user = task2.getResult().toObject(User.class);
                    Session.currentUser = user;
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ActivityHouseOwnerLogin.this, HouseOwnerDashboard.class);
                    startActivity(intent);

                });
            });
        });

        // move to Register activity if user has no account
        tvRegister.setOnClickListener(view->{
            Intent intent = new Intent(ActivityHouseOwnerLogin.this, ActivityHouseOwnerRegister.class);
            startActivity(intent);
        });

    }

    }