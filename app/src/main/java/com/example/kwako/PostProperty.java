package com.example.kwako;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class PostProperty extends AppCompatActivity {
    ImageView previousImageView;
    Button nextBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_property);

        nextBtn = findViewById(R.id.nextbtn);
        previousImageView = findViewById(R.id.previousImageView);

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