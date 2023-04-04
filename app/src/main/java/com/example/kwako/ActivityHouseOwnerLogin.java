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
                // check if login is successful
                if (!task.isSuccessful()) {
                    if (loader.isShowing()) loader.dismiss();
                    Toast.makeText(this, "Unable to Login " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }

                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                Toast.makeText(this, "User Id; "+firebaseUser.getUid(), Toast.LENGTH_LONG).show();
                db.collection("Users").document(firebaseUser.getUid()).get().addOnCompleteListener(task2 -> {
                    if (loader.isShowing()) loader.dismiss();
                    if (!task2.isSuccessful()){
                        Toast.makeText(this, "Unable to get your saved data: "+task2.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // save user session and proceed to HouseOwner dashboard
                    User user = task2.getResult().toObject(User.class);
                    if (user == null) return;
                    Toast.makeText(ActivityHouseOwnerLogin.this, user.getUsername(), Toast.LENGTH_LONG).show();
                    // verify if a house owner
                    if (!user.getUserType().equals(Constants.USER_TYPE_LANDLORD)){
                        Toast.makeText(this, "Only house owners can access this page. ", Toast.LENGTH_LONG).show();
                        return;
                    }

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