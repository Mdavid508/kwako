package com.example.kwako;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomDialog extends AppCompatActivity {

    TextView textViewResponse, tvConfirm;
    String reference;
    private static final String BASE_URL = "https://tinypesa.com/api/v1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);

        // grab data from the previous intent
        Intent data = getIntent();
        reference = data.getStringExtra("ref");


        textViewResponse = findViewById(R.id.textView25);


        // wait for button implementation
        tvConfirm = findViewById(R.id.tvConfirm);

        tvConfirm.setOnClickListener(view -> {
            if(reference.isEmpty()){
                Toast.makeText(CustomDialog.this, "Missing Parameters", Toast.LENGTH_SHORT).show();
                return;
            }
            checkResponse(reference);
        });


    }

    private void checkResponse(String reference) {

        // Function to call the tinypesa APi
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitApi getStatus = retrofit.create(RetrofitApi.class);

        String acceptHeader = "application/json";
        String apikey = "UILUeVO6GBD";

        Call<ExpressGetStatusResponse> call = getStatus.getExpressGetStatus(reference, apikey, acceptHeader);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                ExpressGetStatusResponse data = (ExpressGetStatusResponse) response.body();

                if(response.code() == 404){
                    String transactionState = "Transaction Details : \n" + response.message();
                    Toast.makeText(CustomDialog.this, transactionState, Toast.LENGTH_SHORT).show();
                    return;
                }
                if(data.getMpesaReceipt() == null){
                    String transactionState = "Transaction Details : \nPayment was not recieved" ;
                    Toast.makeText(CustomDialog.this, transactionState, Toast.LENGTH_SHORT).show();
                    return;
                }
                String transactionState = "Transaction Details : \nYour Mpesa code is " +data.getMpesaReceipt()+", amount " +data.getAmount()+ "paid by "+data.getMsisdn();
                textViewResponse.setText(transactionState);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(CustomDialog.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}