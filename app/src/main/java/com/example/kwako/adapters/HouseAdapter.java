package com.example.kwako.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kwako.MapsActivity;
import com.example.kwako.R;
import com.example.kwako.models.House;

import java.util.ArrayList;

public class HouseAdapter extends RecyclerView.Adapter<HouseAdapter.MyHolder> {
    private ArrayList<House> houses;
    private Context context;

    public HouseAdapter(Context context, ArrayList<House> houses){
        this.context = context;
        this.houses = houses;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the layout file for this item
        View v = LayoutInflater.from(context).inflate(R.layout.house_item_landlord, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        // bind data to the viewHolder
        House house =  houses.get(position);
        holder.tvPrice.setText("Ksh. "+house.getPrice());
        holder.tvLocation.setText(house.getLocation());
        if (house.isAvailable()){
            // house available
            holder.ivSwitchOff.setImageResource(R.drawable.ic_switch_on);
        }else {
            // house not available
            holder.ivSwitchOff.setImageResource(R.drawable.ic_switch_off);
        }

        holder.tvLocation.setOnClickListener(holder.onLocationListener);
    }

    @Override
    public int getItemCount() {
        return houses.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        // declare views
        TextView tvLocation, tvPrice;
        ImageView ivSwitchOn, ivSwitchOff;

        View.OnClickListener  onLocationListener;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            // initialize views
            tvLocation = itemView.findViewById(R.id.tvSetLocation);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvLocation = itemView.findViewById(R.id.tvLocationPin);
            ivSwitchOn = itemView.findViewById(R.id.ivAvailable);
            ivSwitchOff = itemView.findViewById(R.id.ivAvailable);


            //open maps
            onLocationListener = (view -> {
                Intent intent = new Intent(itemView.getContext(), MapsActivity.class);
                itemView.getContext().startActivity(intent);
            });




        }
    }
}
