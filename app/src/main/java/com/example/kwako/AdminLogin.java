package com.example.kwako;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kwako.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdminLogin extends AppCompatActivity {
    EditText edtEmail;
    EditText edtPassword;
    Button btnLogin;
    String email;
    String password;
    ProgressDialog loader;
    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        edtEmail = findViewById(R.id.adminEmail);
        edtPassword = findViewById(R.id.AdminPassword);
        btnLogin = findViewById(R.id.button_login);
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
                db.collection("Users").document(firebaseUser.getUid()).get().addOnCompleteListener(task2 -> {
                    if (loader.isShowing()) loader.dismiss();
                    if (!task2.isSuccessful()) {
                        Toast.makeText(this, "Unable to get your saved data: " + task2.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        return;

                    }
                    User user = task2.getResult().toObject(User.class);
                    // verify if user is admin
                    if (!user.getUserType().equals(Constants.USER_TYPE_ADMIN)) {
                        Toast.makeText(this, "Failed! Only admins can access this page. ", Toast.LENGTH_LONG).show();
                        return;
                    }
                    // login successful
                    Session.currentUser = user;
                    // Login was successful. Move to Admin Dashboard
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AdminLogin.this, AdminDashboard.class);
                    startActivity(intent);
                });
            });
        });


    }


}