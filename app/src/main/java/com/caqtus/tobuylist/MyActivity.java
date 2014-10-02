package com.caqtus.tobuylist;

import android.app.Activity;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;

import butterknife.InjectView;


public class MyActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {

    private SimpleCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Button addNew = (Button) findViewById(R.id.button);
        final ListView listView = (ListView) findViewById(R.id.list);



        //setting arraylist data to listView
//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.single_row, android.R.id.text1, list);
//        listView.setAdapter(adapter);
//        // TODO: use SimpleCursorAdapter instead ArrayAdapter


        mAdapter = new SimpleCursorAdapter(this, R.layout.single_row, null, new String[]{


                SQLHelper.TITLE, SQLHelper.AMOUNT, SQLHelper.PRICE
        }, new int[]{
                R.id.title, R.id.amount, R.id.price
        })


        {
            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                super.bindView(view, context, cursor);

                ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
                String category = cursor.getString(cursor.getColumnIndexOrThrow(SQLHelper.CATEGORY));



                if("Food".equals(category)) {
                imageView.setImageResource(R.drawable.food);
                } else if("Gifts".equals(category)) {
                imageView.setImageResource(R.drawable.gift);

                }

                //Converting Float to two decimal float & removing currency symbol added by numberFormatter
                NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
                DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) numberFormat).getDecimalFormatSymbols();
                decimalFormatSymbols.setCurrencySymbol("");
                ((DecimalFormat) numberFormat).setDecimalFormatSymbols(decimalFormatSymbols);

                TextView price = (TextView) view.findViewById(R.id.price);
                TextView amount = (TextView) view.findViewById(R.id.amount);

                price.setText(numberFormat.format(Float.parseFloat(price.getText().toString())
                        * Float.parseFloat(amount.getText().toString())) + " Lari");
                amount.setText(amount.getText() + " KG");

            }
        };
        listView.setAdapter(mAdapter);



        //onClick adds "test" to arraylist and redraws it again
        //TODO: onclick should open menu with options to add str + some other options
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyActivity.this, EditActivity.class);
                startActivity(intent);
            // TODO: insert new row to database & update list
            }
        });



        getLoaderManager().initLoader(0, null, this);

    }


//    //saving in sharedPrefs
//        public boolean saveArray(String[] array, String arrayName, Context mContext){
//            SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
//            SharedPreferences.Editor editor = prefs.edit();
//            editor.putInt(arrayName + "_size", array.length);
//                for (int i = 0; i < array.length; i++){
//                    editor.putString(arrayName + "_" + i, array[i]);
//                }
//            return editor.commit();
//        }
//
//    //reading from sharedPrefs
//    public String[] loadArray(String arrayName, Context mContext){
//        SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
//        int size = prefs.getInt(arrayName + "_size", 0);
//        String array[] = new String[size];
//        for (int i = 0; i<size; i++){
//            array[i]= prefs.getString(arrayName + "_" + i, null);
//        }
//        return array;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        CursorLoader cursorLoader = new CursorLoader(this);
        cursorLoader.setUri(MyContentProvider.ITEMS_URI);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        mAdapter.swapCursor(cursor);


    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        mAdapter.swapCursor(null);

    }
}
