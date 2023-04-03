package com.example.kwako.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kwako.R;
import com.example.kwako.adapters.HouseAdapter;
import com.example.kwako.models.House;
import com.example.kwako.models.Image;

import java.util.ArrayList;
import java.util.List;

public class UnverifiedPropertyDetails extends Fragment {
    List<House> houses;
    HouseAdapter adapter;
    RecyclerView recyclerView;

        public UnverifiedPropertyDetails() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_unverified_property_details, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        houses = new ArrayList<>();


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
        house2.setPrice(4500);
        house2.setSellerName("Ashleen");
        Image image3 = new Image();
        image3.setImageUrl("https://bit.ly/2YoJ77H");
        Image image4 = new Image();
        image4.setImageUrl("https://bit.ly/2BteuF2");
        imageList2.add(image1);
        imageList2.add(image2);
        house2.setImages(imageList2);

        // add to arrayList
        houses.add(house1);
        houses.add(house2);

        adapter = new HouseAdapter(getActivity(), houses);
        recyclerView.setAdapter(adapter);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
    }
}