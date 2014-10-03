package com.caqtus.tobuylist;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TimePicker;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class EditActivity extends Activity {



    @InjectView(R.id.edit_title)
    EditText edit_title;

    @InjectView(R.id.edit_weight)
    EditText edit_weight;

    @InjectView(R.id.edit_price)
    EditText edit_price;

    @InjectView(R.id.category_spinner)
    Spinner categorySpinner;

    @InjectView(R.id.timePicker)
    TimePicker timePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.inject(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, new String[]{"Food", "Gifts"});
        categorySpinner.setAdapter(adapter);

        timePicker.setCurrentHour(19);
        timePicker.setCurrentMinute(0);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit, menu);
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
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.edit_save)
    public void onClickSave() {

        Intent intent = new Intent(this, MyActivity.class);
        startActivity(intent);

        Toast.makeText(this, "Make stuff here", Toast.LENGTH_SHORT).show();
        ContentValues contentValues = new ContentValues();

        String title = String.valueOf(edit_title.getText());
        String category = categorySpinner.getSelectedItem().toString();
        String weight = String.valueOf(edit_weight.getText());
        String price = String.valueOf(edit_price.getText());
        int alarmHour = timePicker.getCurrentHour();
        int alarmMinute = timePicker.getCurrentMinute();


        contentValues.put("TITLE", title);
        contentValues.put("CATEGORY", category);
        contentValues.put("AMOUNT", weight);
        contentValues.put("PRICE", price);
        contentValues.put("ALARM_HOURS", alarmHour);
        contentValues.put("ALARM_MINUTES", alarmMinute);
        getContentResolver().insert(MyContentProvider.ITEMS_URI, contentValues);
    }
}
