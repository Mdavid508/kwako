package com.example.kwako;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HouseBooking extends AppCompatActivity {
    Button makePayment;
    TextView confirm;
    TextView cancel;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_booking);

//        button to trigger the dialog
        makePayment = findViewById(R.id.btnmakepayment);
        makePayment.setOnClickListener(v -> {
          dialog.show();
        });



//        initialization of dialog
        dialog = new Dialog(HouseBooking.this);
        dialog.setContentView(R.layout.custom_dialog);
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



    }
}