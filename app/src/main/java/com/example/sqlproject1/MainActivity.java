package com.example.sqlproject1;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.sqlproject1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton button;
    DBHelper dbh;
    ArrayList<Integer> ids;
    ArrayList<String> unames, unumbers;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        button = findViewById(R.id.floatingActionButton);
        dbh = new DBHelper(this);

        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this , MainActivity2.class);
            startActivity(intent);
        });

        ids = new ArrayList<>();
        unames = new ArrayList<>();
        unumbers = new ArrayList<>();

        myAdapter = new MyAdapter(MainActivity.this, ids, unames, unumbers);
        recyclerView.setAdapter(myAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        displaydata();
    }

    private void displaydata() {
        Cursor cursor = dbh.getdata();
        if (cursor.getCount() == -1){
            Toast.makeText(this, "No Contact", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                ids.add(cursor.getInt(0));
                unames.add(cursor.getString(1));
                unumbers.add(cursor.getString(2));
            }
        }
        myAdapter.notifyDataSetChanged();
    }
}
