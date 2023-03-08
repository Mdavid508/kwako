package com.example.kwako;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        holder.tvPrice.setText(house.getPrice());
        holder.tvLocation.setText(house.getLocation());
    }

    @Override
    public int getItemCount() {
        return houses.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        // declare views
        TextView tvLocation, tvPrice;
        ImageView ivSwitchOn, ivSwitchOff;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            // initialize views
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}
