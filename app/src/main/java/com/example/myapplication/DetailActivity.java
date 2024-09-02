package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView detailImageView = findViewById(R.id.imageView);
        TextView detailHeadingTextView = findViewById(R.id.headingTextView);
        TextView detailInfoTextView = findViewById(R.id.infoTextView);
        Button detailLinkButton = findViewById(R.id.linkButton); // Modified

        // Retrieve the passed data from the intent
        Intent intent = getIntent();
        Item item = intent.getParcelableExtra("item");

        if (item != null) {
            // Set the data to views
            Glide.with(this).load(item.getImageUrl()).into(detailImageView);
            detailHeadingTextView.setText(item.getHeading());
            detailInfoTextView.setText(item.getInfo());

            String link = item.getLink();
            if (!TextUtils.isEmpty(link)) {
                detailLinkButton.setText(link);
                detailLinkButton.setVisibility(View.VISIBLE);
                detailLinkButton.setOnClickListener(v -> {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                    startActivity(browserIntent);
                });
            } else {
                detailLinkButton.setVisibility(View.GONE);
            }
        }
    }
}
