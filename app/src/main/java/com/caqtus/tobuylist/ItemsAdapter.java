package com.caqtus.tobuylist;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

/**
 * Created by eye on 10/2/2014.
 */
public class ItemsAdapter extends CursorAdapter {


    public ItemsAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        

        return null;

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {



    }
}
