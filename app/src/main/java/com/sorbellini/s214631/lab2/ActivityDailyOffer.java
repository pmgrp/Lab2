package com.sorbellini.s214631.lab2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityDailyOffer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_offer);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String saved_offerName = preferences.getString("offerNameModify", null);
        String saved_offerDescription = preferences.getString("offerDescriptionModify", null);
        int saved_offerPrice = preferences.getInt("PriceModify", -1);
        int saved_offerAvailableQuantity = preferences.getInt("AvailableQuantityModify", -1);
        String saved_imgPath = preferences.getString("imgPathModify", null);

        if (saved_imgPath != null){
            ImageView imageView = (ImageView) findViewById(R.id.daily_offer_capturephoto);
            Bitmap imageBitmap = imagePicker.loadImageFromStorage(saved_imgPath);
            if(imageView!=null)
                imageView.setImageBitmap(imageBitmap);
        }

        TextView textView;
        textView = (TextView) findViewById(R.id.daily_offer_name);
        if(textView!=null) {
            textView.setText(saved_offerName);
        }

        textView = (TextView) findViewById(R.id.daily_offer_description);
        if(textView!=null) {
            textView.setText(saved_offerDescription);
        }

        textView= (TextView) findViewById(R.id.daily_offer_price_number);
        if(textView!=null) {
            textView.setText(String.valueOf(saved_offerPrice));
        }

        textView= (TextView) findViewById(R.id.daily_available_quantity_number);
        if(textView!=null) {
            textView.setText(String.valueOf(saved_offerAvailableQuantity));
        }



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
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
