package com.sorbellini.s214631.lab2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class ShowReservations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_reservations);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //dummy data for test
        Restaurant restaurant = new Restaurant();
        ArrayList<Reservation> reservations = restaurant.reservations;
        Customer cus1 = new Customer();
        cus1.setName("Pippo");
        cus1.setSurname("Bianchi");
        cus1.setPhone("123456789012");
        for(int i=0;i<5;i++){
            Reservation res = new Reservation();
            res.setCustomer(cus1);
            res.setTime("13:00");
            reservations.add(res);
        }

        RecyclerView rv = (RecyclerView) findViewById(R.id.reservations);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        ShResAdapter adapter = new ShResAdapter(reservations);
        rv.setAdapter(adapter);






    }

}