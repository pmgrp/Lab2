package com.sorbellini.s214631.lab2;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

public class ReservationDetails extends AppCompatActivity {

    ArrayList<Reservation> reservations = new ArrayList<>();
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle b = getIntent().getExtras();
        reservations = b.getParcelableArrayList("reservations");
        index = b.getInt("index");
        RecyclerView rv = (RecyclerView) findViewById(R.id.reservation_details);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        final ResDetAdapter adapter = new ResDetAdapter(reservations.get(index).orderedDishes);
        rv.setAdapter(adapter);
        TextView reservationComment = (TextView) findViewById(R.id.reservation_comment);
        reservationComment.setText(reservations.get(index).getComment());
        Button reservationConfirm = (Button) findViewById(R.id.reservation_confirm);
        if (reservationConfirm != null) {
            reservationConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < reservations.get(index).orderedDishes.size(); i++) {
                        Dish dish = reservations.get(index).orderedDishes.get(i);
                        if(dish.getAvailability() > 0) {
                            reservations.get(index).orderedDishes.get(i).setAvailability((dish.getAvailability() - 1));
                        }
                    }
                    Intent in = new Intent(v.getContext(), ShowReservations.class);
                    in.putParcelableArrayListExtra("reservations", reservations);
                    //in.putExtra("index",index);
                    v.getContext().startActivity(in);
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, ShowReservations.class);
        intent.putParcelableArrayListExtra("reservations", reservations);
        this.startActivity(intent);
        return true;
    }
}
