package com.caqtus.tobuylist;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by eye on 10/2/2014.
 */
public class MyContentProvider extends ContentProvider {

    public static final Uri ITEMS_URI = Uri.parse("content://com.caqtus.tobuylist/items");

    public static final int CODE_ITEMS = 1;
    UriMatcher uriMatcher = new UriMatcher(-1);

    private SQLHelper database;

    @Override
    public boolean onCreate() {
        database = new SQLHelper(getContext());
        database.getWritableDatabase();
        uriMatcher.addURI("com.caqtus.tobuylist", "items", CODE_ITEMS);

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String orderBy) {
        switch (uriMatcher.match(uri)) {
            case CODE_ITEMS:
                Cursor cursor = database.getReadableDatabase().query(SQLHelper.TABLE_NAME, projection, selection, selectionArgs, null, null, orderBy);
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
            default:
                throw new IllegalArgumentException("Unknown uri: " + uri);
        }
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        switch(uriMatcher.match(uri)) {
            case CODE_ITEMS:
                long newId = database.getWritableDatabase().insert(SQLHelper.TABLE_NAME, null, contentValues);
                getContext().getContentResolver().notifyChange(uri,null,false);
                return ContentUris.withAppendedId(ITEMS_URI, newId);

            default:
                throw new IllegalArgumentException("Unknown uri: " + uri);
        }
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
