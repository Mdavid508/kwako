package com.example.kwako;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class PostProperty extends AppCompatActivity {
    ImageView previousImageView;
    EditText edtLocation, edtPhone , edtWhatsAppNo,edtPrice;
    Button nextBtn;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_property);
        //initialize views
        edtLocation=findViewById(R.id.edtLocation);
        edtPhone=findViewById(R.id.edtPhone);
        edtPrice=findViewById(R.id.edtPrice);
        edtWhatsAppNo=findViewById(R.id.edtWhatsAppNo);
        nextBtn = findViewById(R.id.nextbtn);
        previousImageView = findViewById(R.id.previousImageView);
        mAuth=FirebaseAuth.getInstance();




        //this is the button that will take the house owner to the next page.
        nextBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, UploadImages.class);
            startActivity(intent);
        });
        //the previous button that will take the house owner to the previous page.
        previousImageView.setOnClickListener(view -> {
            finish();
        });
    }
}