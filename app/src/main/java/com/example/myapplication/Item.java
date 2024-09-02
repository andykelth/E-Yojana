package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
    private String id;
    private String heading;
    private String info;
    private String link;
    private String imageUrl;
    private String category; // New field for category

    public Item() {
        // Default constructor required for calls to DataSnapshot.getValue(Item.class)
    }

    public Item(String id, String heading, String info, String link, String imageUrl, String category) {
        this.id = id;
        this.heading = heading;
        this.info = info;
        this.link = link;
        this.imageUrl = imageUrl;
        this.category = category; // Initialize category
    }

    protected Item(Parcel in) {
        id = in.readString();
        heading = in.readString();
        info = in.readString();
        link = in.readString();
        imageUrl = in.readString();
        category = in.readString(); // Read category
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    // Getters
    public String getId() {
        return id;
    }

    public String getHeading() {
        return heading;
    }

    public String getInfo() {
        return info;
    }

    public String getLink() {
        return link;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCategory() {
        return category;
    }

    // Setters (if needed)
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(heading);
        parcel.writeString(info);
        parcel.writeString(link);
        parcel.writeString(imageUrl);
        parcel.writeString(category); // Write category
    }
}
