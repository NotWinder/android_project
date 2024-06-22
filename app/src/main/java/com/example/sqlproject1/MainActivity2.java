package com.example.sqlproject1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import com.example.sqlproject1.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity2 extends AppCompatActivity {

    TextInputEditText name, phone;
    Button save;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        name = findViewById(R.id.textedit);
        phone = findViewById(R.id.textedit2);
        save = findViewById(R.id.button);
        db = new DBHelper(this);

        save.setOnClickListener(v -> {
            String nameTEXT = Objects.requireNonNull(name.getText()).toString();
            String contentTEXT = Objects.requireNonNull(phone.getText()).toString();
            long rowId = db.saveuserdata(nameTEXT, contentTEXT); // Now returns long

            if (TextUtils.isEmpty(nameTEXT) || TextUtils.isEmpty(contentTEXT)) {
                Toast.makeText(MainActivity2.this, "ADD Name & Contact", Toast.LENGTH_SHORT).show();
            } else {
                if (rowId != -1) { // Check if row ID is not -1 (insertion successful)
                    Toast.makeText(MainActivity2.this, "Save Contact", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity2.this, "Exists Contact", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
