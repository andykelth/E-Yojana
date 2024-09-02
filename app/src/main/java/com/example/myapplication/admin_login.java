package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class admin_login extends AppCompatActivity {

    private EditText passwordField;
    private Button loginButton;

    private static final String DEFAULT_PASSWORD = "admin@123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_login);
        passwordField = findViewById(R.id.passwordField);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredPassword = passwordField.getText().toString().trim();

                if (enteredPassword.equals(DEFAULT_PASSWORD)) {
                    // Correct password, navigate to the admin home page
                    Intent intent = new Intent(admin_login.this, AdminActivity.class);
                    startActivity(intent);
                    finish(); // Optional: finish the login activity so the user can't go back to it
                } else {
                    // Incorrect password, show a toast message
                    Toast.makeText(admin_login.this, "Incorrect password. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}