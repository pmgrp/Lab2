package com.sorbellini.s214631.lab2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by eugeniosorbellini on 08/04/16.
 */
public class ResDetAdapter extends RecyclerView.Adapter<ResDetAdapter.DishViewHolder> {

    ArrayList<Dish> orderedDishes;

    ResDetAdapter(ArrayList<Dish> orderedDishes){
        this.orderedDishes = orderedDishes;
    }

    public static class DishViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView dishImage;
        TextView dishName;
        TextView dishDescription;
        TextView dishPrice;
        TextView dishAvailability;

        DishViewHolder(View itemView){
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.reservation_dish_card);
            dishImage = (ImageView)itemView.findViewById(R.id.dish_image);
            dishName = (TextView)itemView.findViewById(R.id.dish_name);
            dishDescription = (TextView)itemView.findViewById(R.id.dish_description);
            dishPrice = (TextView)itemView.findViewById(R.id.dish_price);
            dishAvailability = (TextView)itemView.findViewById(R.id.dish_availability);
        }
    }

    @Override
    public int getItemCount(){
        return orderedDishes.size();
    }

    @Override
    public DishViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.reservation_dish_card, viewGroup, false);
        DishViewHolder dvh = new DishViewHolder(v);
        return dvh;
    }

    @Override
    public void onBindViewHolder(final DishViewHolder dishViewHolder, int i) {
        dishViewHolder.dishImage.setImageURI(Uri.parse(orderedDishes.get(i).getPhoto()));
        dishViewHolder.dishName.setText(orderedDishes.get(i).getName());
        dishViewHolder.dishDescription.setText(orderedDishes.get(i).getDescription());
        dishViewHolder.dishPrice.setText(String.format(Locale.getDefault(),"%.2f", orderedDishes.get(i).getPrice()) + "€");
        dishViewHolder.dishAvailability.setText(String.format(Locale.getDefault(),"%d", orderedDishes.get(i).getAvailability()));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}