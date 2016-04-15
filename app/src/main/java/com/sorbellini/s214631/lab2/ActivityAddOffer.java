package com.sorbellini.s214631.lab2;

import android.app.AlertDialog;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActivityAddOffer extends AppCompatActivity {

    private static final int PICK_IMAGE_ID = 234;

    //Data
    ArrayList<DailyOffer> dailyOffers;
    DailyOffer dailyOffer = new DailyOffer();
    int index = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offer);

        //toolbar part
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get data from shared preferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String dataString = preferences.getString("dailyOffers", null);
        //if there is data get it
        if(dataString != null) {
            Gson gson = new Gson();
            dailyOffers = gson.fromJson(dataString, new TypeToken<List<DailyOffer>>() {
            }.getType());
            index = dailyOffers.size();
        }
        //else create a new array list
        else{
            dailyOffers = new ArrayList<DailyOffer>();
        }

        //set pickers ranges
        NumberPicker pickerPrice = (NumberPicker) findViewById(R.id.add_offer_price);
        pickerPrice.setMaxValue(1000);
        pickerPrice.setMinValue(0);
        pickerPrice.setWrapSelectorWheel(false);
        pickerPrice.setValue(0);

        pickerPrice = (NumberPicker) findViewById(R.id.add_offer_availability);
        pickerPrice.setMaxValue(1000);
        pickerPrice.setMinValue(0);
        pickerPrice.setWrapSelectorWheel(false);
        pickerPrice.setValue(0);


    }

    public void saveData(View view) {
        EditText editText = (EditText) findViewById(R.id.add_offer_name);
        String offerName = editText.getText().toString();
        editText = (EditText) findViewById(R.id.add_offer_description);
        String offerDescription = editText.getText().toString();
        NumberPicker numberPicker = (NumberPicker) findViewById(R.id.add_offer_price);
        int offerPrice = numberPicker.getValue();
        numberPicker = (NumberPicker) findViewById(R.id.add_offer_availability);
        int offerAvailability = numberPicker.getValue();

        //save data in a dailyoffer object
        dailyOffer.setName(offerName);
        dailyOffer.setDescription(offerDescription);
        dailyOffer.setPrice(offerPrice);
        dailyOffer.setAvailability(offerAvailability);
        //save object in array
        dailyOffers.add(dailyOffer);

        Intent intent = new Intent(this, ActivityShowOffers.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    public void addOfferPhoto(View view){
        Intent chooseImageIntent = imagePicker.getPickImageIntent(this);
        startActivityForResult(chooseImageIntent, PICK_IMAGE_ID);
    }

    public void discardButton(View view) {
        Intent intent = new Intent(ActivityAddOffer.this, ActivityShowOffers.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView imageView = (ImageView) findViewById(R.id.add_offer_photo);
        switch(requestCode) {
            case PICK_IMAGE_ID:
                Bitmap imageBitmap = imagePicker.getImageFromResult(this, resultCode, data);
                if(imageBitmap != null) {
                    imageView.setImageBitmap(imageBitmap);
                    String dirPath = imagePicker.saveToInternalStorage(imageBitmap, this, "/offer" + index + ".jpg");
                    dailyOffer.setPhoto(dirPath + "/offer" + index + ".jpg");
                }

            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

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