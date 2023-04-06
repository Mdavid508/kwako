package com.example.kwako;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kwako.models.House;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class PostProperty extends AppCompatActivity {
    ImageView previousImageView;
    EditText edtLocation, edtHouseType, edtPhone, edtWhatsAppNo, edtPrice;
    Button nextBtn, setLocation;
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    ProgressDialog loader;
    double latitude, longitude;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_property);
        //initialize views
//        edtLocation = findViewById(R.id.edtLocation);
        edtHouseType = findViewById(R.id.edtHouseType);
        edtPhone = findViewById(R.id.edtPhone);
        edtPrice = findViewById(R.id.edtPrice);
        edtWhatsAppNo = findViewById(R.id.edtWhatsAppNo);
        nextBtn = findViewById(R.id.nextbtn);
        setLocation = findViewById(R.id.btnSetLocation);
        previousImageView = findViewById(R.id.previousImageView);
        loader = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        // get latitude and longitude data passed via intent
        latitude = getIntent().getDoubleExtra("latitude", 0);
        longitude = getIntent().getDoubleExtra("longitude", 0);

        //setlocation btn that opens the map

        setLocation.setOnClickListener(v -> {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        });

        //this is the button that will take the house owner to the next page.
        nextBtn.setOnClickListener(view -> {
            String location, price, type, phoneNo, whatsAppNo;
            String ownerRef = "Users/"+firebaseUser.getUid();
            price = edtPrice.getText().toString().trim();
            type = edtHouseType.getText().toString().trim();

            // verify whether user has selected location
            if(latitude == 0 || longitude == 0){
                // user has not selected location
            }

            // get house information
            House house = new House();
            house.setName("");
            house.setPrice(Double.parseDouble(price));
            house.setHouseType(type);
            house.setLat(latitude);
            house.setLon(longitude);
            house.setOwnerRef(ownerRef);


            loader.setMessage("Uploading house details...");
            loader.setCanceledOnTouchOutside(false);
            loader.show();
            saveHouseToFirebase(house);
        });

        //the previous button that will take the house owner to the previous page.
        previousImageView.setOnClickListener(view -> {
            finish();
        });
    }

    private void saveHouseToFirebase(House house) {
        db.collection("Houses").add(house.toMap()).addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                if (loader.isShowing()) loader.dismiss();
                Toast.makeText(this, "Unable to save house: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }

            String houseId  = task.getResult().getId();
            // house uploaded successfully
            Toast.makeText(this, "House data uploaded successfully ", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, UploadImages.class);
            intent.putExtra("houseId", houseId);
            startActivity(intent);
        });
    }
}