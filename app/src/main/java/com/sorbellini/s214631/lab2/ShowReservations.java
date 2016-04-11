package com.sorbellini.s214631.lab2;

import android.content.ContentResolver;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class ShowReservations extends AppCompatActivity {

    static ArrayList<Reservation> reservations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_reservations);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        //String savedData = preferences.getString("reservations", null)
        String dataString = preferences.getString("reservations",null);


        if(dataString != null){
            Gson gson = new Gson();
            reservations = gson.fromJson(dataString, new TypeToken<List<Reservation>>(){}.getType());
        }
        else {

            //dummy data for test
            //Restaurant restaurant = new Restaurant();
            reservations = new ArrayList<>();
            Dish dish = new Dish();
            dish.setAvailability(10);
            dish.setName("Pizza");
            dish.setDescription("A great pizza to eat");
            String imagePath = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                    getResources().getResourcePackageName(R.drawable.pizza) + '/' +
                    getResources().getResourceTypeName(R.drawable.pizza) + '/' +
                    getResources().getResourceEntryName(R.drawable.pizza);
            dish.setPhoto(imagePath);
            dish.setPrice(10.45);

            Customer cus = new Customer();
            cus.setName("Luca");
            cus.setSurname("Bianchi");
            cus.setPhone("3375643256");

            Reservation res = new Reservation();
            res.setCustomer(cus);
            res.setTime("13:00");
            res.setComment("I will need a big table");
            res.orderedDishes.add(dish);
            reservations.add(res);

            Dish dish2 = new Dish();
            dish2.setAvailability(5);
            dish2.setName("Pasta");
            dish2.setDescription("A really good vegan Pasta");
            imagePath = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                    getResources().getResourcePackageName(R.drawable.pasta) + '/' +
                    getResources().getResourceTypeName(R.drawable.pasta) + '/' +
                    getResources().getResourceEntryName(R.drawable.pasta);
            dish2.setPhoto(imagePath);
            dish2.setPrice(11.50);

            Customer cus2 = new Customer();
            cus2.setName("Marco");
            cus2.setSurname("Rossi");
            cus2.setPhone("3337645678");

            Reservation res2 = new Reservation();
            res2.setCustomer(cus2);
            res2.setTime("12.00");
            res2.setComment("");
            res2.orderedDishes.add(dish2);
            res2.orderedDishes.add(dish);
            reservations.add(res2);

            Dish dish3 = new Dish();
            dish3.setAvailability(7);
            dish3.setName("Chicken");
            dish3.setDescription("The greatest chicken you have ever eaten");
            imagePath = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                    getResources().getResourcePackageName(R.drawable.pollo) + '/' +
                    getResources().getResourceTypeName(R.drawable.pollo) + '/' +
                    getResources().getResourceEntryName(R.drawable.pollo);
            dish3.setPhoto(imagePath);
            dish3.setPrice(8.70);

            Customer cus3 = new Customer();
            cus3.setName("Giovanni");
            cus3.setSurname("Verdi");
            cus3.setPhone("3287567678");

            Reservation res3 = new Reservation();
            res3.setCustomer(cus2);
            res3.setTime("14.30");
            res3.setComment("");
            res3.orderedDishes.add(dish3);
            res3.orderedDishes.add(dish2);
            res3.orderedDishes.add(dish);
            reservations.add(res3);

        }

        //reservations = new Gson().fromJson(jsonArray, new TypeToken<List<Reservation>>(){}.getType());
        Gson gson2 = new Gson();
        JsonElement element = gson2.toJsonTree(reservations, new TypeToken<List<Reservation>>(){}.getType());
        JsonArray jsonArray = element.getAsJsonArray();
        editor.putString("reservations", jsonArray.toString());
        editor.commit();




        //get updated data from other activities
        //Reservation res = new Reservation();
        /*
        Bundle b = getIntent().getExtras();
        //int index=0;
        if (b != null) {
            Gson gson = new Gson();
            String data = b.getString("reservations");
            reservations = gson.fromJson(data, new TypeToken<List<Reservation>>(){}.getType());
        }*/

        RecyclerView rv = (RecyclerView) findViewById(R.id.reservations);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        ShResAdapter adapter = new ShResAdapter(reservations);
        rv.setAdapter(adapter);

    }
}
