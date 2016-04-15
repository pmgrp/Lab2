package com.sorbellini.s214631.lab2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class ActivityModifyOffer extends AppCompatActivity {


    private NumberPicker pickerPrice = null;
    private NumberPicker pickerAvailableQuantity = null;
    private static final int PICK_IMAGE_ID = 234;

    //Data
    ArrayList<DailyOffer> dailyOffers;
    //index
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_offer);
        //toolbar part
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //here takes data from saved resources and get index from previous activity
        Bundle b = getIntent().getExtras();
        index = b.getInt("index");

        //get data from shared preferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String dataString = preferences.getString("dailyOffers",null);
        Gson gson = new Gson();
        dailyOffers = gson.fromJson(dataString, new TypeToken<List<DailyOffer>>(){}.getType());


        pickerPrice = (NumberPicker) findViewById(R.id.picker_price_modify);
        pickerPrice.setMaxValue(1000);
        pickerPrice.setMinValue(0);
        pickerPrice.setWrapSelectorWheel(false);
        pickerPrice.setValue(10);

        pickerAvailableQuantity = (NumberPicker) findViewById(R.id.picker_availablequantity_modify);
        pickerAvailableQuantity.setMaxValue(1000);
        pickerAvailableQuantity.setMinValue(0);
        pickerAvailableQuantity.setValue(10);
        pickerAvailableQuantity.setWrapSelectorWheel(false);

        //set Values from retrieved data
        //set image
        ImageView imageView = (ImageView) findViewById(R.id.capturephoto_modify);
        imageView.setImageURI(Uri.parse(dailyOffers.get(index).getPhoto()));
        //set name
        TextView textView = (TextView) findViewById(R.id.offer_name_modify);
        textView.setText(dailyOffers.get(index).getName());
        //set description
        textView = (TextView) findViewById(R.id.editoffer_description_modify);
        textView.setText(dailyOffers.get(index).getDescription());
        //set price
        NumberPicker numPicker = (NumberPicker)findViewById(R.id.picker_price_modify);
        numPicker.setValue(dailyOffers.get(index).getPrice());
        //set availability
        numPicker = (NumberPicker)findViewById(R.id.picker_availablequantity_modify);
        numPicker.setValue(dailyOffers.get(index).getAvailability());

    }

    public void selectPhoto(View view){
        Intent chooseImageIntent = imagePicker.getPickImageIntent(this);
        startActivityForResult(chooseImageIntent, PICK_IMAGE_ID);
    }



    public void backToViewOffer(View view) {
        Intent intent = new Intent(ActivityModifyOffer.this, ActivityDisplayOffer.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("index", index);
        startActivity(intent);
    }



    public void saveData2(View view) {
        //save data to shared preferences
        EditText editText = (EditText) findViewById(R.id.offer_name_modify);
        String offerName = editText.getText().toString();
        editText = (EditText) findViewById(R.id.editoffer_description_modify);
        String offerDescription = editText.getText().toString();
        NumberPicker offerAvailableQuantity = (NumberPicker) findViewById(R.id.picker_availablequantity_modify);
        NumberPicker offerPrice = (NumberPicker) findViewById(R.id.picker_price_modify);
        int valuePrice=offerPrice.getValue();
        int valueAvailableQuantity=offerAvailableQuantity.getValue();

        dailyOffers.get(index).setName(offerName);
        dailyOffers.get(index).setDescription(offerDescription);
        dailyOffers.get(index).setPrice(valuePrice);
        dailyOffers.get(index).setAvailability(valueAvailableQuantity);


        Intent intent = new Intent(this, ActivityDisplayOffer.class);
        intent.putExtra("index", index);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    //saves all data when another activity is started

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView imageView = (ImageView) findViewById(R.id.capturephoto_modify);
        switch(requestCode) {
            case PICK_IMAGE_ID:
                Bitmap imageBitmap = imagePicker.getImageFromResult(this, resultCode, data);
                if(imageBitmap != null) {
                    imageView.setImageBitmap(imageBitmap);
                    String dirPath = imagePicker.saveToInternalStorage(imageBitmap, this, "/offer" + index + ".jpg");
                    dailyOffers.get(index).setPhoto(dirPath + "/offer" + index + ".jpg");
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
