package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

public class AddItemActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageView;
    private EditText headingEditText, infoEditText, linkEditText;
    private Button saveButton;
    private Uri imageUri;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private RadioGroup categoryRadioGroup; // New RadioGroup
    private RadioButton selectedRadioButton; // Selected RadioButton

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        imageView = findViewById(R.id.imageView);
        headingEditText = findViewById(R.id.headingEditText);
        infoEditText = findViewById(R.id.infoEditText);
        linkEditText = findViewById(R.id.linkEditText);
        saveButton = findViewById(R.id.saveButton);
        categoryRadioGroup = findViewById(R.id.categoryRadioGroup); // Initialize RadioGroup

        databaseReference = FirebaseDatabase.getInstance().getReference("items");
        storageReference = FirebaseStorage.getInstance().getReference("item_images");

        imageView.setOnClickListener(v -> openGallery());

        saveButton.setOnClickListener(v -> saveItem());
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveItem() {
        final String heading = headingEditText.getText().toString().trim();
        final String info = infoEditText.getText().toString().trim();
        final String link = linkEditText.getText().toString().trim();

        // Get selected category
        int selectedId = categoryRadioGroup.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(this, "Please select a category", Toast.LENGTH_SHORT).show();
            return;
        }
        selectedRadioButton = findViewById(selectedId);
        final String category = selectedRadioButton.getText().toString();

        if (heading.isEmpty() || info.isEmpty() || imageUri == null) {
            Toast.makeText(this, "Please fill all fields, select a category, and select an image", Toast.LENGTH_SHORT).show();
            return;
        }

        final StorageReference fileReference = storageReference.child(System.currentTimeMillis() + ".jpg");

        fileReference.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> fileReference.getDownloadUrl()
                        .addOnSuccessListener(uri -> {
                            String id = databaseReference.push().getKey();
                            Item item = new Item(id, heading, info, link, uri.toString(), category);
                            databaseReference.child(id).setValue(item)
                                    .addOnCompleteListener(task -> {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(AddItemActivity.this, "Item added successfully", Toast.LENGTH_SHORT).show();
                                            finish();
                                        } else {
                                            Toast.makeText(AddItemActivity.this, "Failed to add item", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }))
                .addOnFailureListener(e -> Toast.makeText(AddItemActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}
