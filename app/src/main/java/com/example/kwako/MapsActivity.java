package com.example.kwako;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;

import com.example.kwako.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDragHandleView;



public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener{

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private PlacesClient placesClient;
    private SearchView searchView;
    private BottomSheetDragHandleView bottomSheetDragHandleView;
    private BottomSheetBehavior bottomSheetBehavior;
    private TextView tvLatLng;
    Button btnSetLocation;
    BottomSheetDialog bottomSheetDialog;
    ConstraintLayout bottomSheet;
    double latitude, longitude;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Set the OnMapClickListener to listen for clicks on the map
        mMap.setOnMapClickListener(this);
        View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_view, null);
        //initialize set location button and on click listener
        btnSetLocation = bottomSheetView.findViewById(R.id.btnSetLocation);
        tvLatLng = bottomSheetView.findViewById(R.id.tvLatLng);

        // Add a marker in dekut and move the camera
        LatLng dkut = new LatLng(-0.3974012, 36.9581068);
        mMap.addMarker(new MarkerOptions().position(dkut).title("Dedan Kimathi University of Technology!"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(dkut));
      //  adding another marker
        LatLng kahawa = new LatLng(-0.4040506, 36.9534623);
        mMap.addMarker(new MarkerOptions().position(kahawa).title("Kings & Queen Hostel"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(kahawa));
        //achivers marker
        LatLng achievers = new LatLng(-0.4040506, 36.9534623);
        mMap.addMarker(new MarkerOptions().position(achievers).title("Achievers Hostel"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(achievers));



        // Define the camera position
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(dkut)
                .zoom(14)
                .build();

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        //set custom map style




        bottomSheetDialog = new BottomSheetDialog(MapsActivity.this);
        bottomSheetDialog.setContentView(bottomSheetView);


        // initialize bottomSheet
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetDialog.findViewById(R.id.bottom_sheet));
        bottomSheetBehavior.setPeekHeight(400);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        btnSetLocation.setOnClickListener(v -> {
            //  update firebase

        });
    }
    @Override
    public void onMapClick(LatLng latLng) {
        // Get the latitude and longitude coordinates of the clicked location
        latitude = latLng.latitude;
        longitude = latLng.longitude;

        tvLatLng.setText("Coords: "+latitude +","+longitude);
        // Use the coordinates as needed
        bottomSheetDialog.show();
    }


  }

