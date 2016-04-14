package com.sorbellini.s214631.lab2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ActivityDailyOffer extends AppCompatActivity {

    ArrayList<DailyOffer> dailyOffers;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_offer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get index of current element from bundle
        Bundle b = getIntent().getExtras();
        index = b.getInt("index");

        //get data from shared preferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String dataString = preferences.getString("dailyOffers",null);
        Gson gson = new Gson();
        dailyOffers = gson.fromJson(dataString, new TypeToken<List<DailyOffer>>(){}.getType());

        ImageView imageView;
        imageView = (ImageView) findViewById(R.id.daily_offer_capturephoto);
        imageView.setImageURI(Uri.parse(dailyOffers.get(index).getPhoto()));
        TextView textView;
        textView = (TextView) findViewById(R.id.daily_offer_name);
        if(textView!=null) {
            textView.setText(dailyOffers.get(index).getName());
        }

        textView = (TextView) findViewById(R.id.daily_offer_description);
        if(textView!=null) {
            textView.setText(dailyOffers.get(index).getDescription());
        }

        textView = (TextView) findViewById(R.id.daily_offer_price_number);
        textView.setText(String.format(Locale.getDefault(),"%d", dailyOffers.get(index).getPrice()));
        textView = (TextView) findViewById(R.id.daily_available_quantity_number);
        textView.setText(String.format(Locale.getDefault(),"%d", dailyOffers.get(index).getAvailability()));



    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_daily_offer, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit) {
            Intent intent = new Intent(this, ModifyOffer.class);
            intent.putExtra("index", index);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //saves all data when another activity is started
    @Override
    public void onPause(){
        super.onPause();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson2 = new Gson();
        JsonElement element = gson2.toJsonTree(dailyOffers, new TypeToken<List<DailyOffer>>(){}.getType());
        JsonArray jsonArray = element.getAsJsonArray();
        editor.putString("dailyOffers", jsonArray.toString());
        editor.commit();
    }


}
