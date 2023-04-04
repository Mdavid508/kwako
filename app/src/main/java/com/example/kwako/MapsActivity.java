package com.example.kwako;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
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
import com.google.android.material.bottomsheet.BottomSheetDragHandleView;



public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private PlacesClient placesClient;
    private SearchView searchView;
    private BottomSheetDragHandleView bottomSheetDragHandleView;
    private BottomSheetBehavior bottomSheetBehavior;
    private TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


//        //bottom sheet
//        bottomSheetDragHandleView = findViewById(R.id.bottomSheetDragHandle);
//        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetDragHandleView);
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//        textView= findViewById(R.id.text_view);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//            }
//        });




//        //initialize the places api
//        if (!Places.isInitialized()) {
//            Places.initialize(getApplicationContext(), "YOUR_API_KEY");
//        }
//
//
//
//
//        //creates a place api
//        placesClient = Places.createClient(this);
//
//        SearchView searchView = findViewById(R.id.search_view);
//        BottomSheetDragHandleView bottomSheetDragHandleView1=findViewById(R.id.bottomSheetDragHandle);
        //   searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                // Perform a Places search with the user's query
//                AutocompleteSessionToken token = AutocompleteSessionToken.newInstance();
//                FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
//                        .setTypeFilter(TypeFilter.ADDRESS)
//                        .setSessionToken(token)
//                        .setQuery(query)
//                        .build();
//                placesClient.findAutocompletePredictions(request)
//                        .addOnSuccessListener((response) -> {
//                            // Get the first prediction and move the camera to that location
//                            if (response.getAutocompletePredictions().size() > 0) {
//                                AutocompletePrediction prediction = response.getAutocompletePredictions().get(0);
//                                String placeId = prediction.getPlaceId();
////                                List<Place.Field> placeFields = Arrays.asList(Place.Field.LAT_LNG);
//                                List<Place.Field> placeFields = Arrays.asList(Place.Field.LAT_LNG);
//                                FetchPlaceRequest placeRequest = FetchPlaceRequest.builder(placeId, placeFields).build();
//
//                                placesClient.fetchPlace(placeRequest)
//                                        .addOnSuccessListener((place) -> {
//                                            LatLng latLng = place.getPlace().getLatLng();
//                                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
//                                        });
//                            }
//                        });
//
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                // Handle text changes
//                return false;
//            }
//        });
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
        //add bottom sheet
//        // Add bottom sheet
//        View bottomSheetView = findViewById(R.id.bottomSheetDragHandle);
//        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetDragHandleView);
//        bottomSheetBehavior.setPeekHeight(200); // Set initial peek height
//        bottomSheetBehavior.setHideable(true); // Allow user to hide the sheet by dragging down
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED); // Set initial state to collapsed
//        // Handle drag events
//        bottomSheetDragHandleView = findViewById(R.id.bottomSheetDragHandle);
//        bottomSheetDragHandleView.setOnTouchListener((view, motionEvent) -> {
//            switch (motionEvent.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//                    break;
//                case MotionEvent.ACTION_UP:
//                case MotionEvent.ACTION_CANCEL:
//                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//                    break;
//            }
//            return true;
//        });
////adding location
//        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(@NonNull Marker marker) {
//
//                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MapsActivity.this);
//                View bottomSheetView = getLayoutInflater().inflate(R.layout.maps_layout, null);
//                bottomSheetDialog.setContentView(bottomSheetView);
//                bottomSheetDialog.show();
//
//                return false;
//            }
//        });
//
//        Button SetLocationButton = bottomSheetDragHandleView.findViewById(R.id.btnSetLocation);
//        SetLocationButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //EditText addressEditText = bottomSheetView.findViewById(R.id.edtSetLocation);
//                String address = addressEditText.getText().toString();
//                EditText locationEditText = findViewById(R.id.edtSetLocation);
//                String location = locationEditText.getText().toString();
                //Toast.makeText(this, "Location saved: " + location, Toast.LENGTH_SHORT).show();

                // Save the location data to your database or storage mechanism
                // ...

//              //  BottomSheetDialog.dismiss();
//            }
//        });
//        // Show the bottom sheet dialog
//       // BottomSheetDialog.show();
//
//        return;
  }
}
