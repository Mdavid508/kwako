package com.example.kwako.fragments;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kwako.R;
import com.example.kwako.adapters.HouseAdapter;
import com.example.kwako.models.House;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VerifiedPropertyDetails} factory method to
 * create an instance of this fragment.
 */
public class VerifiedPropertyDetails extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<House> houses;
    HouseAdapter adapter;
    RecyclerView recyclerView;
    ImageView imageView;
    TextView tvWhatsapp;
    View.OnClickListener onWhatsappListener;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private PackageManager packageManager;

    public VerifiedPropertyDetails() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_verified_property_details, container, false);
//        imageView = view.findViewById(R.id.ivPhone);
//
//        //open phone call
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:0712345678"));
//                startActivity(intent);
//
//            }
//        });

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //  TODO add logic for this fragment here, including instantiating views
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);





//            //open WhatsApp chat
//            tvWhatsapp = view.findViewById(R.id.tvWhatsApp);
//            tvWhatsapp.setOnClickListener(v -> {
//                Uri uri = Uri.parse("smsto:" + "0713662010");
//                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
//                intent.setPackage("com.whatsapp");
//                if (intent.resolveActivity(this.packageManager) != null) {
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(getContext(), "WhatsApp is not installed", Toast.LENGTH_SHORT).show();
//                }
//
//            });


            houses = new ArrayList<>();
            House house1 = new House();
            house1.setName("Kens Hostels");
            house1.setAvailable(true);
            house1.setLocation("Kimathi Way");
            house1.setPrice(3500);
            house1.setSellerName("Mr. Almond");
            House house2 = new House();
            house2.setName("Kens Hostels");
            house2.setAvailable(false);
            house2.setLocation("Kimathi Way");
            house2.setPrice(3500);
            house2.setSellerName("Mr. Almond");
            House house3 = new House();
            house3.setName("Kens Hostels");
            house3.setAvailable(true);
            house3.setLocation("Kimathi Way");
            house3.setPrice(3500);
            house3.setSellerName("Mr. Almond");

            // add to arrayList
            houses.add(house1);
            houses.add(house2);
            houses.add(house3);

            // instantiate adapter
            adapter = new HouseAdapter(getContext(), houses);
            recyclerView.setAdapter(adapter);
        }
    }
