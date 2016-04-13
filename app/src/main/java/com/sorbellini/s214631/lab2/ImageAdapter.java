package com.sorbellini.s214631.lab2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by ISEN on 11/04/2016.
 */
public class ImageAdapter extends BaseAdapter {

    ArrayList<DailyOffer> dailyOffers = new ArrayList<>();

    public ImageAdapter(ArrayList<DailyOffer> dailyOffers) {
        this.dailyOffers = dailyOffers;
    }

    @Override
    public int getCount() {
        return dailyOffers.size();
    }

    @Override
    public Object getItem(final int position) {
        return dailyOffers.get(position);
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_item, parent, false);
        }

        TextView text = (TextView) view.findViewById(R.id.offer_text);
        ImageView image = (ImageView) view.findViewById(R.id.offer_image);


        text.setText(dailyOffers.get(position).getName());
        image.setImageURI(Uri.parse(dailyOffers.get(position).getPhoto()));

        return view;
    }
}