package com.sorbellini.s214631.lab2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class ReservationDetails extends AppCompatActivity {

    ArrayList<Reservation> reservations;
    int index;
    Gson gson = new Gson();
    //SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);


        Bundle b = getIntent().getExtras();
        //reservations = b.getParcelableArrayList("reservations");
        String dataString = preferences.getString("reservations",null);
        reservations = gson.fromJson(dataString, new TypeToken<List<Reservation>>(){}.getType());
        index = b.getInt("index");
        RecyclerView rv = (RecyclerView) findViewById(R.id.reservation_details);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        final ResDetAdapter adapter = new ResDetAdapter(reservations.get(index).orderedDishes);
        rv.setAdapter(adapter);



        
        Button reservationConfirm = (Button) findViewById(R.id.reservation_confirm);
        Button reservationRejected = (Button) findViewById(R.id.reservation_reject);


        if (reservationConfirm != null && reservationRejected != null) {
            //change action depending on reservation status
            switch(reservations.get(index).getStatus()){
                //if arrived show button to confirm and button to reject
                case Reservation.ARRIVED:
                    reservationConfirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //update dishes quantity
                            for (int i = 0; i < reservations.get(index).orderedDishes.size(); i++) {
                                Dish dish = reservations.get(index).orderedDishes.get(i);
                                if (dish.getAvailability() > 0) {
                                    reservations.get(index).orderedDishes.get(i).setAvailability((dish.getAvailability() - 1));
                                }
                            }
                            //set reservation as confirmed
                            reservations.get(index).setStatus(Reservation.CONFIRMED);
                            Intent in = new Intent(v.getContext(), ShowReservations.class);
                            JsonElement element = gson.toJsonTree(reservations, new TypeToken<List<Reservation>>(){}.getType());
                            JsonArray jsonArray = element.getAsJsonArray();
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(v.getContext());
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("reservations", jsonArray.toString());
                            editor.commit();
                            //in.putExtra("index",index);
                            v.getContext().startActivity(in);
                        }
                    });
                    reservationRejected.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            reservations.get(index).setStatus(Reservation.REJECTED);
                            Intent in = new Intent(v.getContext(), ShowReservations.class);
                            JsonElement element = gson.toJsonTree(reservations, new TypeToken<List<Reservation>>(){}.getType());
                            JsonArray jsonArray = element.getAsJsonArray();
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(v.getContext());
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("reservations", jsonArray.toString());
                            editor.commit();
                            v.getContext().startActivity(in);
                        }
                    });
                    break;
                //if is confirmed show button to set completed
                case Reservation.CONFIRMED:
                    //hide button confirm
                    reservationConfirm.setVisibility(View.INVISIBLE);
                    //set button for completion
                    reservationRejected.setText("Completed");
                    reservationRejected.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            reservations.get(index).setStatus(Reservation.COMPLETED);
                            Intent in = new Intent(v.getContext(), ShowReservations.class);
                            JsonElement element = gson.toJsonTree(reservations, new TypeToken<List<Reservation>>(){}.getType());
                            JsonArray jsonArray = element.getAsJsonArray();
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(v.getContext());
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("reservations", jsonArray.toString());
                            editor.commit();
                            v.getContext().startActivity(in);
                        }
                    });
                    break;
                //if reservation is completed set button to delete
                case Reservation.COMPLETED:
                    reservationConfirm.setVisibility(View.INVISIBLE);
                    reservationRejected.setText("Delete");
                    reservationRejected.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            reservations.remove(index);
                            Intent in = new Intent(v.getContext(), ShowReservations.class);
                            JsonElement element = gson.toJsonTree(reservations, new TypeToken<List<Reservation>>(){}.getType());
                            JsonArray jsonArray = element.getAsJsonArray();
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(v.getContext());
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("reservations", jsonArray.toString());
                            editor.commit();
                            v.getContext().startActivity(in);
                        }
                    });
                    break;
                case Reservation.REJECTED:
                    reservationConfirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //update dishes quantity
                            for (int i = 0; i < reservations.get(index).orderedDishes.size(); i++) {
                                Dish dish = reservations.get(index).orderedDishes.get(i);
                                if (dish.getAvailability() > 0) {
                                    reservations.get(index).orderedDishes.get(i).setAvailability((dish.getAvailability() - 1));
                                }
                            }
                            //set reservation as confirmed
                            reservations.get(index).setStatus(Reservation.CONFIRMED);
                            Intent in = new Intent(v.getContext(), ShowReservations.class);
                            JsonElement element = gson.toJsonTree(reservations, new TypeToken<List<Reservation>>(){}.getType());
                            JsonArray jsonArray = element.getAsJsonArray();
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(v.getContext());
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("reservations", jsonArray.toString());
                            editor.commit();
                            v.getContext().startActivity(in);
                        }
                    });
                    reservationRejected.setText("Delete");
                    reservationRejected.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            reservations.remove(index);
                            Intent in = new Intent(v.getContext(), ShowReservations.class);
                            JsonElement element = gson.toJsonTree(reservations, new TypeToken<List<Reservation>>(){}.getType());
                            JsonArray jsonArray = element.getAsJsonArray();
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(v.getContext());
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("reservations", jsonArray.toString());
                            editor.commit();
                            v.getContext().startActivity(in);
                        }
                    });
                    break;
            }
        }
    }
}
