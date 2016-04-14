package com.sorbellini.s214631.lab2;

<<<<<<< HEAD:app/src/main/java/com/sorbellini/s214631/lab2/ActivityDailyOffer.java
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



=======
/**
 * Created by eugeniosorbellini on 13/04/16.
 */
public class DailyOffer {
    private String name;
    private String description;
    private String photo;
    private int price;
    private int availability;

    //default constructor
    public DailyOffer() {
        this.name = "";
        this.description = "";
        this.photo = "";
        this.price = -1;
        this.availability = -1;
>>>>>>> master:app/src/main/java/com/sorbellini/s214631/lab2/DailyOffer.java
    }

    //getter
    public String getName(){
        return this.name;
    }
    public String getDescription(){
        return this.description;
    }
    public String getPhoto(){
        return this.photo;
    }
    public int getPrice(){
        return this.price;
    }
    public int getAvailability(){
        return this.availability;
    }

    //setter
    public void setName(String name){
        this.name = name;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setPhoto(String photo){
        this.photo = photo;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public void setAvailability(int availability){
        this.availability = availability;
    }
}