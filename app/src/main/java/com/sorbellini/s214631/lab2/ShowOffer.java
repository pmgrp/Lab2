package com.sorbellini.s214631.lab2;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class ShowOffer extends AppCompatActivity {
    ArrayList<DailyOffer> dailyOffers;
    ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_offer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //button to add offer
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickPopupOffer();
            }
        });

        //get data from shared preferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String dataString = preferences.getString("dailyOffers",null);

        if(dataString != null) {
            Gson gson = new Gson();
            dailyOffers = gson.fromJson(dataString, new TypeToken<List<DailyOffer>>() {}.getType());
        }

        else {

            //////// MAKE DATA ///////////
            dailyOffers = new ArrayList<>();
            DailyOffer d1 = new DailyOffer();
            d1.setName("Pizza");
            String imagePath = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                    getResources().getResourcePackageName(R.drawable.pizza) + '/' +
                    getResources().getResourceTypeName(R.drawable.pizza) + '/' +
                    getResources().getResourceEntryName(R.drawable.pizza);
            d1.setPhoto(imagePath);
            d1.setDescription("Really good pizza");
            d1.setPrice(10);
            d1.setAvailability(20);
            dailyOffers.add(d1);

            DailyOffer d2 = new DailyOffer();
            d2.setName("Pasta");
            imagePath = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                    getResources().getResourcePackageName(R.drawable.pasta) + '/' +
                    getResources().getResourceTypeName(R.drawable.pasta) + '/' +
                    getResources().getResourceEntryName(R.drawable.pasta);
            d2.setPhoto(imagePath);
            dailyOffers.add(d2);

            DailyOffer d3 = new DailyOffer();
            d3.setName("Chicken");
            imagePath = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                    getResources().getResourcePackageName(R.drawable.pollo) + '/' +
                    getResources().getResourceTypeName(R.drawable.pollo) + '/' +
                    getResources().getResourceEntryName(R.drawable.pollo);
            d3.setPhoto(imagePath);
            dailyOffers.add(d3);
            //////// END DATA /////////
        }


        //make grid view
        GridView gridView = (GridView) findViewById(R.id.grid_view);
        adapter = new ImageAdapter(dailyOffers);
        gridView.setAdapter(adapter);

        //set listeners on elements of grid view
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(ShowOffer.this, ActivityDailyOffer.class);
                intent.putExtra("index", position);
                startActivity(intent);
            }
        });

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {
                onClickPopupOptions(parent, v, position);
                return true;
            }
        });

    }

    public void onClickPopupOptions(final AdapterView<?> parent, View v, final int position) {
        final CharSequence[] items = { "Delete", "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(ShowOffer.this);
        builder.setTitle("Options");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Delete")) {
                    dailyOffers.remove(position);
                    adapter.notifyDataSetChanged();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void onClickPopupOffer() {
        final CharSequence[] items = { "Yes", "No" };
        AlertDialog.Builder builder = new AlertDialog.Builder(ShowOffer.this);
        builder.setTitle("Do you want to add an offer_item ?");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Yes")) {
                    startActivity(new Intent(ShowOffer.this, FormularAddingAnOffer.class));
                } else if (items[item].equals("No")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }

    //save alla data when switch activity
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
