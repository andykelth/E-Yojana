package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<Item> {
    private Context context;
    private ArrayList<Item> itemList;
    private boolean isAdmin; // Flag to determine admin view

    public ItemAdapter(Context context, ArrayList<Item> items, boolean isAdmin) {
        super(context, 0, items);
        this.context = context;
        this.itemList = items;
        this.isAdmin = isAdmin;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_item, parent, false);
        }

        Item item = itemList.get(position);

        TextView headingTextView = convertView.findViewById(R.id.headingTextView);
        ImageView imageView = convertView.findViewById(R.id.imageView);
        Button deleteButton = convertView.findViewById(R.id.deleteButton);

        headingTextView.setText(item.getHeading());
        // Load image using Glide
        Glide.with(context).load(item.getImageUrl()).into(imageView);

        if (isAdmin) {
            deleteButton.setVisibility(View.VISIBLE);
            deleteButton.setOnClickListener(v -> {
                // Handle delete logic here
                if (context instanceof AdminActivity) {
                    ((AdminActivity) context).deleteItem(item);
                }
            });
        } else {
            deleteButton.setVisibility(View.GONE);
        }

        return convertView;
    }
}
