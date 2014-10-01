package com.caqtus.tobuylist;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Button addNew = (Button) findViewById(R.id.button);
        ListView listView = (ListView) findViewById(R.id.list);

        //list values array
        String[] values = new String[]{

        };


        // TODO: onClick adds value to arrayList and displayes in listView
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //converting into array list
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; i++){
            list.add(values[i]);
            System.out.println(list.get(i));
        }

        //setting arraylist data to listView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

    }


    //saving in sharedPrefs
        public boolean saveArray(String[] array, String arrayName, Context mContext){
            SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(arrayName + "_size", array.length);
                for (int i = 0; i < array.length; i++){
                    editor.putString(arrayName + "_" + i, array[i]);
                }
            return editor.commit();
        }

    //reading from sharedPrefs
    public String[] loadArray(String arrayName, Context mContext){
        SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        String array[] = new String[size];
        for (int i = 0; i<size; i++){
            array[i]= prefs.getString(arrayName + "_" + i, null);
        }
        return array;
    }

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
}
