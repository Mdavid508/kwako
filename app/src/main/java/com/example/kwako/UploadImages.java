package com.example.kwako;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kwako.adapters.ImageUploadAdapter;
import com.example.kwako.models.Image;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UploadImages extends AppCompatActivity {
    // file url to store image
    Button btnSubmit, btnCancel;
    ImageView cameraUpload, galleryUpload;
    RecyclerView recyclerView;
    ImageUploadAdapter imagesAdapter;
    List<Image> images;
    final FirebaseStorage storage = FirebaseStorage.getInstance();
    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    final StorageReference imagesRef = storage.getReference();
    String currentPhotoPath, imageFileName;
    Uri imageUri;
    private static final int REQUEST_IMAGE_CAPTURE = 1, REQUEST_IMAGE_PICK = 2, REQUEST_CAMERA_PERMISSION = 3;
    private String houseId; // House id of the current house

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_images);
        cameraUpload = findViewById(R.id.uploadCamera);
        galleryUpload = findViewById(R.id.uploadGallery);
        btnSubmit = findViewById(R.id.btnSubmit);
        recyclerView = findViewById(R.id.recyclerviewUploadImages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        images = new ArrayList<>();
        imagesAdapter = new ImageUploadAdapter(getApplicationContext(), images);
        recyclerView.setAdapter(imagesAdapter);

        // get house id passed as intent
        houseId = getIntent().getStringExtra("houseId");

        //upload from gallery functionality
        galleryUpload.setOnClickListener(v -> {
            Intent photoPicker = new Intent(Intent.ACTION_GET_CONTENT);
            photoPicker.setType("image/*");
            startActivityForResult(photoPicker, REQUEST_IMAGE_PICK);
        });

        //Take photo functionality method implementation
        cameraUpload.setOnClickListener(v -> dispatchTakePictureIntent());

        // Upload image firebase
        btnSubmit.setOnClickListener(view -> {
            if (images.size() == 0) {
                Toast.makeText(this, "No images to upload ", Toast.LENGTH_SHORT).show();
                return;
            }
            if (houseId == null){
                Toast.makeText(this, "Error: Cannot save image since houseId is null.", Toast.LENGTH_SHORT).show();
                return;
            }
            for (Image image : images) {
                uploadImage(image);
            }
        });
    }

    /**
     * Save image to firebase Storage
     */
    private void uploadImage(Image image) {
        String imageName = UUID.randomUUID() + "." + getFileExtension(image.getImageUri());
        final StorageReference ref = imagesRef.child("images/" + imageName);
        Toast.makeText(this, "Uploading image " + image.getImageName(), Toast.LENGTH_SHORT).show();
        UploadTask uploadTask = ref.putFile(image.getImageUri());

        uploadTask.addOnSuccessListener(taskSnapshot -> {
            // get image download URI
            Task<Uri> downloadUrlTask = taskSnapshot.getStorage().getDownloadUrl();
            downloadUrlTask.addOnSuccessListener(uri -> {
                // save information to firebase here
                String imageURL = uri.toString();
                Toast.makeText(this, "Image uploaded to firebase successfully: "+imageURL, Toast.LENGTH_SHORT).show();
                // set image download URL
                image.setImageUrl(imageURL);
                // Save Image to FireStore
                uploadToFirebase(image);
            });
        }).addOnProgressListener(progressTask -> {
//            Toast.makeText(this, "Uploading image...", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(exception -> Toast.makeText(this, "Error uploading image: " + exception.getMessage(), Toast.LENGTH_LONG).show());
    }

    /**
     * Get the file extension associated with the specific URI
     *
     * @param imageUri URI of the image you want to get its file extension
     * @return the file extension for the URI
     */
    private String getFileExtension(Uri imageUri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(imageUri));
    }

    /**
     * Create and save and image inside the android os storage
     *
     * @return {File} The created image file
     * @throws IOException Error encountered creating the image file
     */
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        imageFileName = "IMG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        Log.d("CurrentPhotoPath", currentPhotoPath);
        return image;
    }

    /**
     * Create an intent to take a camera photo
     */
    private void dispatchTakePictureIntent() {
        // verify that camera permissions have been granted
        if (!checkCameraPermissions()) return;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
                Log.d("photoFile", photoFile.toString());
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.d("image error", "Error creating image file");
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                imageUri = FileProvider.getUriForFile(this,
                        "com.example.kwako.fileprovider",
                        photoFile);
                Log.d("photoURI", imageUri.toString());
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_IMAGE_CAPTURE: { // image from camera
                if (resultCode == RESULT_OK) {
                    // image successfully captured
                    if (imageUri != null){
                        Toast.makeText(this, "Image captured successfully", Toast.LENGTH_SHORT).show();
                        // display image to listView
                        Image image = new Image(imageFileName+".jpg", imageUri);
                        images.add(image);
                        imagesAdapter.notifyDataSetChanged();
                    }else{
                        Toast.makeText(this, "Unable to capture image", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            }
            case REQUEST_IMAGE_PICK: { // image from gallery
                if (resultCode == RESULT_OK) {
                    Uri imageUri = data.getData();
                    Toast.makeText(this, "Image picked successfully", Toast.LENGTH_SHORT).show();
                    // get picked image name
                    String[] filePathColumn = {MediaStore.Images.Media.DISPLAY_NAME};
                    Cursor cursor = getContentResolver().query(imageUri, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imageFileName = cursor.getString(columnIndex);
                    cursor.close();

                    // display image to listView
                    Image image = new Image(imageFileName, imageUri);
                    images.add(image);
                    imagesAdapter.notifyItemInserted(images.size() - 1);
                }
                break;
            }
        }
    }

    /**
     * Check if access camera permissions have been granted
     */
    private boolean checkCameraPermissions() {
        int result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);
        if (result != PackageManager.PERMISSION_GRANTED) {
            // Request camera permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
            return false;
        }
        // permission already granted
        return true;
    }

    /**
     * Save image download URL to FireStore
     *
     * @param image Image you want to save its URL to firebase
     */
    private void uploadToFirebase(Image image) {
        db.collection("Houses").document(houseId).collection("Images").document().set(image).addOnCompleteListener(task -> {
            Toast.makeText(this, "Image Download URL saved to Firebase successfully", Toast.LENGTH_SHORT).show();
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission granted
                dispatchTakePictureIntent();
            } else {
                // permission denied
                Toast.makeText(this, "Camera permission required", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
