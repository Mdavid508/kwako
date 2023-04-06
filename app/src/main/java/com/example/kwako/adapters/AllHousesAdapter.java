package com.example.kwako.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.kwako.ActivityHouseBooking;
import com.example.kwako.MapsActivity;
import com.example.kwako.R;
import com.example.kwako.models.House;
import com.example.kwako.models.Image;

import java.util.ArrayList;
import java.util.List;

public class AllHousesAdapter extends RecyclerView.Adapter<AllHousesAdapter.MyViewHolder> {

    private Context context;
    private List<SlideModel> imageList;

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
        holder.bind(house);



    }

    @Override
    public int getItemCount() {
        return houses.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{
        // declare views
        House currentHouse;
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
//            tvPhone = tvPhoneitemView.findViewById(R.id.tvPhone);
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
                Intent intent = new Intent(context, ActivityHouseBooking.class);
                intent.putExtra("house", currentHouse);
                // pass house data along
                context.startActivity(intent);
            };

        }

        // bind data
        private void bind(House house){
            currentHouse = house;
            tvLocationPin.setOnClickListener(onLocationListener);
            btnBook.setOnClickListener(onBookListener);
            imageSlider.setImageList(generateSlideModels(house.getImages()));
            tvLocation.setText(house.getLocation());
            tvPrice.setText("Ksh. "+house.getPrice());
            tvHouseType.setText(house.getHouseType());
        }
    }


    /**
     * Create SlideModels from house images
     */
    private List<SlideModel> generateSlideModels(List<Image> houseImages){
        List<SlideModel> slideModels = new ArrayList<>(houseImages.size());
        int i=0;
        for (Image image: houseImages){
            i++;
            slideModels.add(new SlideModel(image.getImageUrl(), "Image "+i, null));
        }
        return slideModels;
    }

    private List<SlideModel> getSlideModels(){
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://bit.ly/2YoJ77H", "Some  random image", null));
        slideModels.add(new SlideModel("https://bit.ly/2YoJ77H", "Image Two", null));
        slideModels.add(new SlideModel("https://bit.ly/2YoJ77H", "Image 3", null));
        return slideModels;

    }
}
