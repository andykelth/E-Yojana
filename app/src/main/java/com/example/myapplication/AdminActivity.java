package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Item> itemList;
    private ItemAdapter adapter;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        listView = findViewById(R.id.listView);
        Button addButton = findViewById(R.id.addFab);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, AddItemActivity.class);
            startActivity(intent);
        });

        itemList = new ArrayList<>();
        adapter = new ItemAdapter(this, itemList, true);  // true for admin view
        listView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("items");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Item item = snapshot.getValue(Item.class);
                    if (item != null) {
                        itemList.add(item);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors.
            }
        });
    }

    public void deleteItem(Item item) {
        if (item != null && item.getId() != null) {
            databaseReference.child(item.getId()).removeValue()
                    .addOnSuccessListener(aVoid -> {
                        itemList.remove(item);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(AdminActivity.this, "Item deleted successfully.", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(AdminActivity.this, "Failed to delete item.", Toast.LENGTH_SHORT).show();
                    });
        } else {
            Toast.makeText(AdminActivity.this, "Item ID is null.", Toast.LENGTH_SHORT).show();
        }
    }
}
