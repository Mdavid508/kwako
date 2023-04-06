package com.example.kwako;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kwako.models.House;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityHouseBooking extends AppCompatActivity{

    EditText editTextUnitsNumber;

    TextView textViewTotalAmount;

    EditText editTextMpesaNo;

    Button btnMakePayment;

    String IdentifierID;

    private static final String BASE_URL = "https://tinypesa.com/api/v1/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_booking);

        editTextMpesaNo = findViewById(R.id.editTextMpesaNo);
        textViewTotalAmount = null;
        editTextUnitsNumber = findViewById(R.id.editTextUnitsNumber);

        String MpesaNoValue = editTextMpesaNo.getText().toString();

        String UnitsNumberValue = editTextUnitsNumber.getText().toString();
        int UnitsNumber = Integer.parseInt(UnitsNumberValue);

        // Generate a UUID
        IdentifierID = String.valueOf(UUID.randomUUID());

        // receive data from the other side
        House house = (House) getIntent().getSerializableExtra("House");
        int RentAmount = (int) house.getPrice();
        int DepositAmount = (int) house.getPrice();

        btnMakePayment = findViewById(R.id.btnmakepayment);

        editTextUnitsNumber.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        // Not needed yet !
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        // Change values on the Total Amount using this method
                        try {
                            int displayData = (RentAmount + DepositAmount) * UnitsNumber;

                            textViewTotalAmount.setText(String.valueOf(displayData));

                        }catch (NumberFormatException e){
                            // Handle error
                        }

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        // Not needed yet !
                    }
                }
        );

        btnMakePayment.setOnClickListener( view -> {
            if(editTextMpesaNo.getText().toString().isEmpty() && editTextUnitsNumber.getText().toString().isEmpty()){
                Toast.makeText(ActivityHouseBooking.this, "Please enter both the values", Toast.LENGTH_SHORT).show();
                return;
            }

            // Get data from previous screen
            int TotalAmountPayble = (RentAmount + DepositAmount) * UnitsNumber;

            postData(MpesaNoValue, TotalAmountPayble, IdentifierID);

        });

    }
    private void postData(String phone, int amount, String identifier) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitApi service = retrofit.create(RetrofitApi.class);

        DataModal dataModal = new DataModal(phone, amount, identifier);
        String accceptHeader = "application/json";
        String apikey = "UILUeVO6GBD";

        Call<DataModal> call = service.createPost(apikey,accceptHeader,dataModal);

        call.enqueue(new Callback<DataModal>() {
            @Override
            public void onResponse(@NonNull Call<DataModal> call, @NonNull Response<DataModal> response) {
                // this methods are called when we get response from our api.
                Toast.makeText(ActivityHouseBooking.this, response.message(), Toast.LENGTH_SHORT).show();
                // Move this to a preloader activity and then the dialog box

                // on below line we are setting empty text
                // to our both edit text.
                editTextUnitsNumber.setText("");
                editTextMpesaNo.setText("");

                // we are getting response from our body
                // and passing it to our dataModal class.
                String responseFromAPI = response.body().toString();

                // Pass data to the next activity
                Intent intent = new Intent(ActivityHouseBooking.this, CustomDialog.class);
                intent.putExtra("ref" , IdentifierID);
                startActivity(intent);
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFailure(@NonNull Call<DataModal> call, @NonNull Throwable t) {
                // setting text to our text view when
                // we get error response from API.
                Toast.makeText(ActivityHouseBooking.this, "Failed to send STK Push !", Toast.LENGTH_SHORT).show();
            }
        });
    }

}