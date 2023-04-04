package com.example.kwako;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kwako.adapters.AllHousesAdapter;
import com.example.kwako.models.House;
import com.example.kwako.models.Image;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db;
    RecyclerView recyclerView;
    List<House> houses;
    AllHousesAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        db =FirebaseFirestore.getInstance();
        houses = new ArrayList<>();
        adapter = new AllHousesAdapter(this, houses);
        // sample house dummy data
        List<Image> imageList1 = new ArrayList<>();
        List<Image> imageList2 = new ArrayList<>();
        House house1 = new House();
        house1.setName("Las Vegas");
        house1.setAvailable(true);
        house1.setLocation("Skuta");
        house1.setHouseType("BedSitter");
        house1.setPrice(3000);
        house1.setSellerName("Maurice");
        Image image1 = new Image();
        image1.setImageUrl("https://bit.ly/2YoJ77H");
        Image image2 = new Image();
        image2.setImageUrl("https://bit.ly/2BteuF2");
        imageList1.add(image1);
        imageList1.add(image2);
        house1.setImages(imageList1);

        // sample house dummy data
        House house2 = new House();
        house2.setName("Brights Hostels");
        house2.setAvailable(true);
        house2.setLocation("Embassy");
        house2.setHouseType("Single");
        house2.setPrice(3000);
        house2.setSellerName("Ashleen");
        Image image3 = new Image();
        image3.setImageUrl("https://bit.ly/2YoJ77H");
        Image image4 = new Image();
        image4.setImageUrl("https://bit.ly/2BteuF2");
        imageList2.add(image1);
        imageList2.add(image2);
        house2.setImages(imageList2);

        // add the houses to recyclerview adapter
        houses.add(house1);
        houses.add(house2);

        // bind recyclerview to fetch data from the adapter
        recyclerView.setAdapter(adapter);
        // make the layout refresh with new data
        adapter.notifyDataSetChanged();



    }
}