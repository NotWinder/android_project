package com.example.sqlproject1;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Contacts", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table Contacts(id INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, contact TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Contacts");
    }

    public long saveuserdata(String name, String contact) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("contact", contact);
        return sqLiteDatabase.insert("Contacts", null, contentValues); // Now returns the actual row ID
    }

    public boolean updatedata(int id, String name, String contact){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("contact", contact);
        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery("select * from Contacts where id = ?", new String[]{String.valueOf(id)});
        if (cursor.getCount() > 0) {
            long result = sqLiteDatabase.update("Contacts", contentValues, "id = ?", new String[]{String.valueOf(id)});
            return result != -1;
        } else {
            return false;
        }
    }

    public boolean deletedata(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery("select * from Contacts where id = ?", new String[]{String.valueOf(id)});
        if (cursor.getCount() > 0) {
            long result = sqLiteDatabase.delete("Contacts", "id = ?", new String[]{String.valueOf(id)});
            return result != -1;
        } else {
            return false;
        }
    }

    public Cursor getdata() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("select id, Name, contact from Contacts", null);
    }
}
