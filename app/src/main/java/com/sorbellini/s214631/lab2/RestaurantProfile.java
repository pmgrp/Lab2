package com.sorbellini.s214631.lab2;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.google.gson.Gson;
public class RestaurantProfile extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_profile);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = preferences.getString("restaurant", null);
        Restaurant obj = gson.fromJson(json, Restaurant.class);
        if (obj != null) {
            TextView txt = (TextView) findViewById(R.id.restaurantName);
            txt.setText(obj.getRestaurantName());
            txt = (TextView) findViewById(R.id.restaurantPhone);
            txt.setText(obj.getRestaurantPhone());
            txt = (TextView) findViewById(R.id.restaurantAddress);
            txt.setText(obj.getRestaurantAddress());
            txt = (TextView) findViewById(R.id.restaurantEmail);
            txt.setText(obj.getRestaurantEmail());
            txt = (TextView) findViewById(R.id.restaurantWebsite);
            txt.setText(obj.getRestaurantWebsite());
            txt = (TextView) findViewById(R.id.restaurantIVA);
            txt.setText(obj.getRestaurantPiva());
        }
    }
}