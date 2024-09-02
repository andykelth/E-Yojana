package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CategoryItemsActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Item> categoryItemList;
    private ItemAdapter adapter;
    private DatabaseReference databaseReference;
    private String selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_items);

        listView = findViewById(R.id.categoryListView);
        categoryItemList = new ArrayList<>();
        adapter = new ItemAdapter(this, categoryItemList, false);
        listView.setAdapter(adapter);

        // Retrieve selected category from intent
        Intent intent = getIntent();
        selectedCategory = intent.getStringExtra("selectedCategory");
        if (selectedCategory == null) {
            Toast.makeText(this, "No category selected", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Set Activity title to selected category
        setTitle(selectedCategory);

        databaseReference = FirebaseDatabase.getInstance().getReference("items");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                categoryItemList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Item item = snapshot.getValue(Item.class);
                    if (item != null && selectedCategory.equals(item.getCategory())) {
                        categoryItemList.add(item);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors.
            }
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Item item = categoryItemList.get(position);
            Intent detailIntent = new Intent(CategoryItemsActivity.this, DetailActivity.class);
            detailIntent.putExtra("item", item);
            startActivity(detailIntent);
        });
    }
}
