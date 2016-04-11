package com.sorbellini.s214631.lab2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by eugeniosorbellini on 07/04/16.
 */
public class ShResAdapter extends RecyclerView.Adapter<ShResAdapter.ReservationViewHolder> {

    ArrayList<Reservation> reservations;

    ShResAdapter(ArrayList<Reservation> reservations){
        this.reservations = reservations;
    }

    public static class ReservationViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView customerName;
        TextView customerSurname;
        TextView customerPhone;
        TextView customerComment;
        TextView lunchTime;
        TextView lunchTimeTitle;
        TextView reservationStatus;
        TextView reservationTotPrice;
        Button deleteButton;

        ReservationViewHolder(View itemView){
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.reservation_card);
            customerName = (TextView)itemView.findViewById(R.id.customer_name);
            customerSurname = (TextView)itemView.findViewById(R.id.customer_surname);
            customerPhone = (TextView)itemView.findViewById(R.id.customer_phone);
            customerComment = (TextView)itemView.findViewById(R.id.customer_comment);
            lunchTime = (TextView)itemView.findViewById(R.id.lunch_time);
            lunchTimeTitle = (TextView)itemView.findViewById(R.id.lunch_time_title);
            reservationStatus = (TextView)itemView.findViewById(R.id.reservation_status);
            reservationTotPrice = (TextView)itemView.findViewById(R.id.reservation_tot_price);
            deleteButton = (Button)itemView.findViewById(R.id.reservation_delete);
        }
    }

    @Override
    public int getItemCount(){
        return reservations.size();
    }

    @Override
    public ReservationViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.reservation_card, viewGroup, false);
        ReservationViewHolder rvh = new ReservationViewHolder(v);
        return rvh;
    }

    @Override
    public void onBindViewHolder(final ReservationViewHolder reservationViewHolder, int i) {
        float totPrice=0;
        //get price of all dishes in the current reservation
        for(int j=0; j < reservations.get(i).orderedDishes.size(); j++){
            totPrice += reservations.get(i).orderedDishes.get(j).getPrice();
        }
        reservationViewHolder.customerName.setText(reservations.get(i).getCustomer().getName());
        reservationViewHolder.customerSurname.setText(reservations.get(i).getCustomer().getSurname());
        reservationViewHolder.customerPhone.setText("Tel: " + reservations.get(i).getCustomer().getPhone());
        reservationViewHolder.customerComment.setText(reservations.get(i).getComment());
        reservationViewHolder.lunchTime.setText(reservations.get(i).getTime());
        reservationViewHolder.reservationTotPrice.setText
                (String.format(Locale.getDefault(),"%.2f",totPrice));
        int color;
        //reservationViewHolder.deleteButton.sty
        switch(reservations.get(i).getStatus()){
            case Reservation.ARRIVED:
                reservationViewHolder.reservationStatus.setText("Arrived");
                reservationViewHolder.deleteButton.setVisibility(View.INVISIBLE);
                //color = ContextCompat.getColor(reservationViewHolder.cv.getContext(), R.color.status_arrived);
                //reservationViewHolder.cv.setCardBackgroundColor(color);
                break;
            case Reservation.CONFIRMED:
                reservationViewHolder.reservationStatus.setText("Confirmed");
                reservationViewHolder.deleteButton.setVisibility(View.INVISIBLE);
                color = ContextCompat.getColor(reservationViewHolder.cv.getContext(), R.color.status_confirmed);
                reservationViewHolder.cv.setCardBackgroundColor(color);
                break;
            case Reservation.REJECTED:
                reservationViewHolder.reservationStatus.setText("Rejected");
                reservationViewHolder.lunchTime.setVisibility(View.INVISIBLE);
                reservationViewHolder.lunchTimeTitle.setVisibility(View.INVISIBLE);
                color = ContextCompat.getColor(reservationViewHolder.cv.getContext(), R.color.status_rejected);
                reservationViewHolder.cv.setCardBackgroundColor(color);
                break;
            case Reservation.COMPLETED:
                reservationViewHolder.reservationStatus.setText("Completed");
                reservationViewHolder.lunchTime.setVisibility(View.INVISIBLE);
                reservationViewHolder.lunchTimeTitle.setVisibility(View.INVISIBLE);
                color = ContextCompat.getColor(reservationViewHolder.cv.getContext(), R.color.status_completed);
                reservationViewHolder.cv.setCardBackgroundColor(color);
                break;
            default:
                reservationViewHolder.reservationStatus.setText("Arrived");
                reservationViewHolder.deleteButton.setVisibility(View.INVISIBLE);
                color = ContextCompat.getColor(reservationViewHolder.cv.getContext(), R.color.status_arrived);
                reservationViewHolder.cv.setCardBackgroundColor(color);
                break;
        }
        reservationViewHolder.cv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new Intent(v.getContext(), ReservationDetails.class);
                //in.putParcelableArrayListExtra("reservations", (ArrayList)reservations);
                in.putExtra("index", reservationViewHolder.getAdapterPosition());
                v.getContext().startActivity(in);
            }
        });
        reservationViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reservations.remove(reservationViewHolder.getAdapterPosition());
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(v.getContext());
                SharedPreferences.Editor editor = preferences.edit();
                Gson gson = new Gson();
                JsonElement element = gson.toJsonTree(reservations, new TypeToken<List<Reservation>>(){}.getType());
                JsonArray jsonArray = element.getAsJsonArray();
                editor.putString("reservations", jsonArray.toString());
                editor.commit();
                notifyItemRemoved(reservationViewHolder.getAdapterPosition());
            }
        });

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
