package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class category extends AppCompatActivity {

    ImageView navhomefromcat, navchatfromcat, navuserfromcat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        navhomefromcat = findViewById(R.id.homefromcategory);
        navchatfromcat = findViewById(R.id.chatbotfromcategory);
        navuserfromcat = findViewById(R.id.userfromcategory);

        navhomefromcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(category.this,MainActivity.class);
                startActivity(intent);
            }
        });
        navchatfromcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(category.this,ChatBot.class);
                startActivity(intent);
            }
        });
        navuserfromcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(category.this,Profile.class);
                startActivity(intent);
            }
        });


    }
}