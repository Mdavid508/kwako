package com.example.kwako.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import com.example.kwako.MapsActivity;

import com.example.kwako.HouseBooking;

import com.example.kwako.R;
import com.example.kwako.models.House;
import com.example.kwako.models.Image;
import com.example.kwako.models.User;

import java.util.ArrayList;
import java.util.List;

public class AllHousesAdapter extends RecyclerView.Adapter<AllHousesAdapter.MyViewHolder> {
    private static Context context;
    private List<House> houses;

    public AllHousesAdapter(Context context, List<House> houses) {
        this.context = context;
        this.houses = houses;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.house_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        House house = houses.get(position);
        User houseOwner = house.getOwner();
        // bind data to view in order to be rendered to the user

        holder.tvLocationPin.setOnClickListener(holder.onLocationListener);
        holder.btnBook.setOnClickListener(holder.onBookListener);
        holder.imageSlider.setImageList(generateSlideModels(house.getImages()));
        holder.tvLocation.setText(house.getLocation());
        holder.tvPrice.setText("Ksh. "+house.getPrice());
        holder.tvPhone.setText("0723329281"); // TODO: use houseOwner.getPhoneNumber() instead
        holder.tvHouseType.setText(house.getHouseType());

        holder.btnBook.setOnClickListener(view -> {
            Toast.makeText(context, "Booking coming soon", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context,HouseBooking.class);
            intent.putExtra("house", house);
            // pass house data along
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return houses.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // declare views
        ImageSlider imageSlider;
        TextView tvLocation, tvPrice, tvPhone, tvHouseType,tvLocationPin;
        Button btnBook;

        View.OnClickListener onBookListener, onLocationListener;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            // initialize views
            imageSlider = itemView.findViewById(R.id.imageSlider);
            tvLocation = itemView.findViewById(R.id.tvSetLocation);
            tvPrice = itemView.findViewById(R.id.tvPrice);
//            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvHouseType = itemView.findViewById(R.id.tvHouseType);
            btnBook = itemView.findViewById(R.id.btnBook);

            tvLocationPin = itemView.findViewById(R.id.tvLocationPin);

            //open maps
            onLocationListener = (view -> {
                Intent intent = new Intent(itemView.getContext(), MapsActivity.class);
                itemView.getContext().startActivity(intent);
            });
            // House Booked Listener
            onBookListener = view -> {
                Toast.makeText(itemView.getContext(), "Booking coming soon", Toast.LENGTH_SHORT).show();
            };

        }
    }


    /**
     * Create SlideModels from house images
     */
    private List<SlideModel> generateSlideModels(List<Image> houseImages){
        List<SlideModel> slideModels = new ArrayList<>(houseImages.size());
        for (Image image: houseImages){
            slideModels.add(new SlideModel(image.getImageUrl(), "", null));
        }
        return slideModels;
    }
}
