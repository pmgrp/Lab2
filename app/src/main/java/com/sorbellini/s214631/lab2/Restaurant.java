package com.sorbellini.s214631.lab2;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by eugeniosorbellini on 01/04/16.
 */
public class Restaurant {

    private String restaurantName;
    private String restaurantPhone;
    private Address restaurantAddress;
    private String restaurantEmail;
    private String restaurantWebsite;
    private String restaurantPiva;
    private Bitmap restaurantPicture;
    private OpeningHours openingHours;
    private ArrayList<Reservation>;
    private ArrayList<DailyOffer>;


    //getter
    public String getRestaurantName() {
        return restaurantName;
    }

    public String getRestaurantPhone() {
        return restaurantPhone;
    }

    public Address getRestaurantAddress(){
        return restaurantAddress;
    }

    public String getRestaurantEmail() {
        return restaurantEmail;
    }

    public String getRestaurantWebsite() {
        return restaurantWebsite;
    }

    public String getRestaurantPiva() {
        return restaurantPiva;
    }

    public Bitmap getRestaurantPicture(){
        return restaurantPicture;
    }


    //TODO
    //setter
    public String setRestaurantName() {
        return restaurantName;
    }

    public String getRestaurantPhone() {
        return restaurantPhone;
    }

    public Address getRestaurantAddress(){
        return restaurantAddress;
    }

    public String getRestaurantEmail() {
        return restaurantEmail;
    }

    public String getRestaurantWebsite() {
        return restaurantWebsite;
    }

    public String getRestaurantPiva() {
        return restaurantPiva;
    }

    public Bitmap getRestaurantPicture(){
        return restaurantPicture;
    }



}