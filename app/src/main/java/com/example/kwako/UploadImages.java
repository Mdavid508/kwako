package com.example.kwako;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.kwako.adapters.ImageUploadAdapter;
import com.example.kwako.models.Item;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class UploadImages extends AppCompatActivity {
    //declaring the values to determine whether to pick from gallery or take photo
    private static final int PICK_IMAGE_FROM_GALLERY_REQUEST_CODE = 1;
    private static final int PICK_IMAGE_FROM_CAMERA_REQUEST_CODE = 2;
    private static final int MEDIA_TYPE_IMAGE = 1;
    int type;
    DatagramPacket data;
    private Uri fileUri; // file url to store image
    ImageView galleryUpload;
    ImageView cameraUpload;
    Button submitImages;
    ImageUploadAdapter imagesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_images);
        //setting the filename from the inflator to the recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerviewUploadImages);

        List<Item> items = new ArrayList<>();
        items.add(new Item("image20203456789.jpg"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        imagesAdapter = new ImageUploadAdapter(getApplicationContext(),items);
        recyclerView.setAdapter(imagesAdapter);
        //implement the camera upload or gallery upload functionality.
        cameraUpload = findViewById(R.id.uploadCamera);
        galleryUpload = findViewById(R.id.uploadGallery);

        //upload from gallery functionality
        galleryUpload.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE_FROM_GALLERY_REQUEST_CODE);

        });
        //Take photo functionality method implementation
        cameraUpload.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

            byte[] uri = data.getData();
            String s;
            try {
                s = new String(uri, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            Uri uri1 = Uri.parse(s);
            String[] projection = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(uri1, projection, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(projection[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();
            startActivityForResult(intent, PICK_IMAGE_FROM_CAMERA_REQUEST_CODE);
        });



    }

    private Uri getOutputMediaFileUri(int mediaTypeImage) {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MyApp");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;

        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }

        return FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", mediaFile);
    }
}