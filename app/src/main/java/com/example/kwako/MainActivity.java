package com.example.kwako;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kwako.adapters.AllHousesAdapter;
import com.example.kwako.models.House;
import com.example.kwako.models.User;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db;
    RecyclerView recyclerView;
    List<House> houses;
    AllHousesAdapter adapter;
    ProgressDialog loader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        db =FirebaseFirestore.getInstance();
        loader = new ProgressDialog(this);
        loader.setCanceledOnTouchOutside(false);

        houses = new ArrayList<>();
        adapter = new AllHousesAdapter(this, houses);
        // bind recyclerview to fetch data from the adapter
        recyclerView.setAdapter(adapter);

        db.collection("Houses").get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()){
                if (!documentSnapshot.exists()){
                    Toast.makeText(this, "Unable to get houses", Toast.LENGTH_SHORT).show();
                    return;
                }

                House house = documentSnapshot.toObject(House.class);
                // get house owner
                DocumentReference userRef = db.document(house.getOwnerRef());
                userRef.get().addOnSuccessListener(documentSnapshot1 -> {
                    if (!documentSnapshot1.exists()){
                        Toast.makeText(this, "House owner is empty", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    User owner = documentSnapshot1.toObject(User.class);
                    house.setOwner(owner);
                    houses.add(house);

                    adapter.notifyDataSetChanged();
                }).addOnFailureListener(e -> {
                    Toast.makeText(this, "Error getting house owner: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                });

            }
        }).addOnFailureListener(e -> {
            Toast.makeText(this, "Error getting house data: "+e.getMessage(), Toast.LENGTH_LONG).show();
        });

        // make the layout refresh with new data
        adapter.notifyDataSetChanged();
    }
}
