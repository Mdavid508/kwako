package com.example.kwako;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kwako.models.House;

public class HouseBooking extends AppCompatActivity {
    Button makePayment;
    TextView confirm;
    TextView cancel;
    Dialog dialog;
    House house;
    ImageView prevBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_booking);

        // get house data passed via intent
        house = getIntent().getParcelableExtra("house");


//        button to trigger the dialog
        makePayment = findViewById(R.id.btnmakepayment);
        makePayment.setOnClickListener(v -> {
            if (house == null) {
                Toast.makeText(this, "House data is null", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this, "House data passed successfully", Toast.LENGTH_SHORT).show();
            }

            dialog.show();
        });


//        initialization of dialog
        dialog = new Dialog(HouseBooking.this);
        dialog.setContentView(R.layout.activity_custom_dialog);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

//        call to action textviews
        cancel = dialog.findViewById(R.id.tvcancel);
        confirm = dialog.findViewById(R.id.tvconfirm);

        cancel.setOnClickListener(v -> {
            //cancel returns one to the
            dialog.dismiss();

        });
        confirm.setOnClickListener(v -> {
            //this directs the user to the new page where the loading will take place first
            Toast.makeText(this, "yes make sure you have paid for the amount said", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        prevBtn = findViewById(R.id.HouseOwner);
        prevBtn.setOnClickListener(v -> {
           finish();
        });


    }
}