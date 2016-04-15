package com.sorbellini.s214631.lab2;
//import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.google.gson.Gson;
public class EditRestaurantProfile extends AppCompatActivity {
    Restaurant restaurant = new Restaurant();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_restaurant_profile);
    }
    public void saveRestaurantData(View view) {
        final EditText name = (EditText) findViewById(R.id.restaurantNameField);
        final EditText phone = (EditText) findViewById(R.id.restaurantPhoneField);
        final EditText address = (EditText) findViewById(R.id.restaurantAddressField);
        final EditText email = (EditText) findViewById(R.id.restaurantEmailField);
        final EditText website = (EditText) findViewById(R.id.restaurantWebsiteField);
        final EditText pIVA = (EditText) findViewById(R.id.restaurantIVAField);
        restaurant.setRestaurantName(name.getText().toString());
        restaurant.setRestaurantPhone(phone.getText().toString());
        restaurant.setRestaurantAddress(address.getText().toString());
        restaurant.setRestaurantEmail(email.getText().toString());
        restaurant.setRestaurantWebsite(website.getText().toString());
        restaurant.setRestaurantPiva(pIVA.getText().toString());
        //save data to shared preferences
        //Intent intent = new Intent(this, ViewRestaurantProfile.class);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(restaurant);
        editor.putString("restaurant", json);
        editor.commit();
        /*intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
    }
}
