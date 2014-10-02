package com.caqtus.tobuylist;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLHelper extends SQLiteOpenHelper {

    public static final int VERSION = 2;

    public static final String TABLE_NAME = "items";
    public static final String ID = "_id";
    public static final String TITLE = "title";
    public static final String CATEGORY = "category";
    public static final String AMOUNT = "amount";
    public static final String PRICE = "price";

    private static final String CREATE_DATABASE_TABLE = "CREATE TABLE "
            + TABLE_NAME + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TITLE + " TEXT NOT NULL, "
            + CATEGORY + " TEXT, "
            + AMOUNT + " TEXT, "
            + PRICE + " TEXT)";


    public SQLHelper(Context context) {
        super(context, "database.db", null, VERSION);
    }

    ContentValues values = new ContentValues();

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_DATABASE_TABLE);
        // TODO: insert data here

//
//        values.put(TITLE, "FirstTitleyay");
//        values.put(CATEGORY, "Food");
//        values.put(AMOUNT, 4);
//        values.put(PRICE, 13);
//
//        sqLiteDatabase.insert(TABLE_NAME, null, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
