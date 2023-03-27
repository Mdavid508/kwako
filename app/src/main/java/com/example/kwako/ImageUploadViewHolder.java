package com.example.kwako;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageUploadViewHolder extends RecyclerView.ViewHolder {
    public TextView uploadNameView;
//    ImageView ivdelete;

    public ImageUploadViewHolder(@NonNull View itemView) {
        super(itemView);
        uploadNameView = itemView.findViewById(R.id.tvuploadName);
//        ivdelete = itemView.findViewById(R.id.ivdelete);
    }
}
