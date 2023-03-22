package com.example.kwako.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.kwako.models.Item;
import com.example.kwako.R;

import java.util.List;

public class ImageUploadAdapter extends RecyclerView.Adapter<ImageUploadAdapter.ImageUploadViewHolder> {

    Context context;
    List<Item> items;

    public ImageUploadAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ImageUploadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageUploadViewHolder(LayoutInflater.from(context).inflate(R.layout.image_upload_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ImageUploadViewHolder holder, int position) {
        holder.uploadNameView.setText(items.get(position).getFileName());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * You can define your viewHolder class here
     */
    public class ImageUploadViewHolder extends RecyclerView.ViewHolder {
        TextView uploadNameView;
        ImageView ivDelete;

        public ImageUploadViewHolder(@NonNull View itemView) {
            super(itemView);
            uploadNameView = itemView.findViewById(R.id.tvUploadName);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }
    }
}
