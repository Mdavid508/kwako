package com.example.kwako;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import com.denzcoskun.imageslider.models.SlideModel;
import com.example.kwako.adapters.AllHousesAdapter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db;
    RecyclerView recyclerView;
    List<SlideModel> imageList;
    AllHousesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        db =FirebaseFirestore.getInstance();
        imageList = new ArrayList<>();
        adapter = new AllHousesAdapter(this, imageList);
        // add images to list
        imageList.add(new SlideModel("https://bit.ly/2YoJ77H", "The animal population decreased by 58 percent in 42 years.", null));
        imageList.add(new SlideModel("https://bit.ly/2BteuF2", "Elephants and tigers may become extinct.", null));
        imageList.add(new SlideModel("https://bit.ly/3fLJf72", "And people do that.", null));
        // bind recyclerview to fetch data from the adapter
        recyclerView.setAdapter(adapter);
        // make the layout refresh with new data
        adapter.notifyDataSetChanged();
    }
}