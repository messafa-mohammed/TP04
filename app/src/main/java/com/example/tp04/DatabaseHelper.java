package com.example.tp04;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "Users";
    public static final String COLUMN_ID = "_id"; // Use "_id" for auto-incrementing ID
    public static final String COLUMN_NAME = "v_username";
    public static final String COLUMN_PASSWORD = "v_password";
//    public static final String COLUMN_EMAIL = "v_email";
//    public static final String COLUMN_PHONE = "v_phone";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " VARCHAR, " + COLUMN_PASSWORD + " VARCHAR" +//add ,
//                COLUMN_EMAIL + " VARCHAR, " + COLUMN_PHONE + " VARCHAR" +
                ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public long insertData(String name, String prenom ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PASSWORD, prenom);
//        values.put(COLUMN_EMAIL, email);
//        values.put(COLUMN_PHONE, phone);
        long newRowId = db.insert(TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }

    public Cursor getData() {
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public Integer dbSize(){
        Cursor help = getData();
        int size =0;
        while(help.moveToNext()) {

            size++;

        }

        return size;
    }


    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+ TABLE_NAME);
        db.close();
    }
}
