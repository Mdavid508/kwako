package com.example.kwako.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kwako.MapsActivity;
import com.example.kwako.R;
import com.example.kwako.models.House;

import java.util.ArrayList;
import java.util.List;

public class HouseAdapter extends RecyclerView.Adapter<HouseAdapter.MyHolder> {
    private List<House> houses;
    private Context context;

    public HouseAdapter(Context context, List<House> houses) {
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
        House house = houses.get(position);
        holder.bind(house);
    }

    @Override
    public int getItemCount() {
        return houses.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        // declare views
        House currentHouse;
        TextView tvLocation, tvPrice;
        ImageView ivSwitchOn, ivSwitchOff, ivPhone, ivWhatsApp;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            Context mContext = itemView.getContext();
            // initialize views
            tvLocation = itemView.findViewById(R.id.tvSetLocation);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvLocation = itemView.findViewById(R.id.tvLocationPin);
            ivSwitchOn = itemView.findViewById(R.id.ivAvailable);
            ivSwitchOff = itemView.findViewById(R.id.ivAvailable);
            ivPhone = itemView.findViewById(R.id.ivPhone);
            ivWhatsApp = itemView.findViewById(R.id.ivWhatsapp);


            //open maps
            tvLocation.setOnClickListener(view -> {
                Intent intent = new Intent(mContext, MapsActivity.class);
                mContext.startActivity(intent);
            });
            
            // Open WhatsApp chat
            ivWhatsApp.setOnClickListener(v -> {
                Uri uri = Uri.parse("smsto:" + "0713662010");
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.setPackage("com.whatsapp");
                if (intent.resolveActivity(mContext.getPackageManager()) != null) {
                    mContext.startActivity(intent);
                } else {
                    Toast.makeText(mContext, "WhatsApp is not installed", Toast.LENGTH_SHORT).show();
                }

            });

            //open phone call
            ivPhone.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0713662010"));
                mContext.startActivity(intent);
            });

        }

        private void bind(House house) {
            currentHouse = house;
            tvPrice.setText("Ksh. " + house.getPrice());
            tvLocation.setText(house.getLocation());
            if (house.isAvailable()) {
                // house available
                ivSwitchOff.setImageResource(R.drawable.ic_switch_on);
            } else {
                // house not available
                ivSwitchOff.setImageResource(R.drawable.ic_switch_off);
            }
        }

    }
}
