package com.sorbellini.s214631.lab2;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by eugeniosorbellini on 07/04/16.
 */
public class ShResAdapter extends RecyclerView.Adapter<ShResAdapter.ReservationViewHolder> {

    ArrayList<Reservation> reservations;

    ShResAdapter(ArrayList<Reservation> reservations){
        this.reservations =reservations;
    }

    public static class ReservationViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView customerName;
        TextView customerSurname;
        TextView customerPhone;
        TextView lunchTime;

        ReservationViewHolder(View itemView){
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.reservation_card);
            customerName = (TextView)itemView.findViewById(R.id.customer_name);
            customerSurname = (TextView)itemView.findViewById(R.id.customer_surname);
            customerPhone = (TextView)itemView.findViewById(R.id.customer_phone);
            lunchTime = (TextView)itemView.findViewById(R.id.lunch_time);
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
    public void onBindViewHolder(ReservationViewHolder reservationViewHolder, int i) {
        reservationViewHolder.customerName.setText(reservations.get(i).getCustomer().getName());
        reservationViewHolder.customerSurname.setText(reservations.get(i).getCustomer().getSurname());
        reservationViewHolder.customerPhone.setText(reservations.get(i).getCustomer().getPhone());
        reservationViewHolder.lunchTime.setText(reservations.get(i).getTime());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
