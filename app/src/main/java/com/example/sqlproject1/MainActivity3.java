package com.example.sqlproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sqlproject1.R;

public class MainActivity3 extends AppCompatActivity {

    EditText name, phone;
    ImageView delete, edit;
    DBHelper db;
    int contactId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        name = findViewById(R.id.editTextPersonName);
        phone = findViewById(R.id.editTextPhone);
        delete = findViewById(R.id.imageView3);
        edit = findViewById(R.id.imageView4);
        db = new DBHelper(this);

        Intent intent = getIntent(); // Use getIntent() instead of new Intent()
        contactId = intent.getIntExtra("id", -1); // Get the ID
        name.setText(intent.getStringExtra("names"));
        phone.setText(intent.getStringExtra("numbers"));

        delete.setOnClickListener(v -> {
            if (contactId != -1) {
                boolean status = db.deletedata(contactId); // Use the ID for deletion
                if (status) {
                    Toast.makeText(MainActivity3.this, "Contact Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity3.this, "Deletion Failed", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity3.this, "Invalid Contact ID", Toast.LENGTH_SHORT).show();
            }
        });

        edit.setOnClickListener(v -> {
            if (contactId != -1) {
                String contactName = name.getText().toString();
                String contactPhone = phone.getText().toString();
                boolean status = db.updatedata(contactId, contactName, contactPhone); // Use the ID for update
                if (status) {
                    Toast.makeText(MainActivity3.this, "Contact Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity3.this, "Update Failed", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity3.this, "Invalid Contact ID", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
