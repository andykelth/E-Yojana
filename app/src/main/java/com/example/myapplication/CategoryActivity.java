package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.cardview.widget.CardView;

import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Map;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {

    // Category CardViews
    private CardView category1Card, category2Card, category3Card, category4Card;

    // Navigation ImageViews
    private ImageView navHomeFromCat, navChatFromCat, navUserFromCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category);

        // Apply system window insets for Edge-to-Edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize CardViews
        category1Card = findViewById(R.id.category1Card);
        category2Card = findViewById(R.id.category2Card);
        category3Card = findViewById(R.id.category3Card);
        category4Card = findViewById(R.id.category4Card);

        // Initialize Navigation ImageViews
        navHomeFromCat = findViewById(R.id.homefromcategory);
        navChatFromCat = findViewById(R.id.chatbotfromcategory);

        // Set onClick listeners for CardViews
        category1Card.setOnClickListener(this);
        category2Card.setOnClickListener(this);
        category3Card.setOnClickListener(this);
        category4Card.setOnClickListener(this);

        // Set onClick listeners for Navigation ImageViews
        navHomeFromCat.setOnClickListener(view -> {
            Intent intent = new Intent(CategoryActivity.this, MainActivity.class);
            startActivity(intent);
        });
        navChatFromCat.setOnClickListener(view -> {
            Intent intent = new Intent(CategoryActivity.this, ChatBot.class);
            startActivity(intent);
        });
    }

    @Override
    public void onClick(View v) {
        // Create a map to associate view IDs with category names
        Map<Integer, String> categoryMap = new HashMap<>();
        categoryMap.put(R.id.category1Card, "Category 1");
        categoryMap.put(R.id.category2Card, "Category 2");
        categoryMap.put(R.id.category3Card, "Category 3");
        categoryMap.put(R.id.category4Card, "Category 4");

        // Get the selected category based on the clicked view ID
        String selectedCategory = categoryMap.get(v.getId());

        if (selectedCategory != null) {
            // Proceed with the intent if a valid category was selected
            Intent intent = new Intent(CategoryActivity.this, CategoryItemsActivity.class);
            intent.putExtra("selectedCategory", selectedCategory);
            startActivity(intent);
        } else {
            // Show a toast message if the category is unknown
            Toast.makeText(this, "Unknown Category", Toast.LENGTH_SHORT).show();
        }
    }
}
