package com.sorbellini.s214631.lab2;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by eugeniosorbellini on 01/04/16.
 */
public class Restaurant {

    private String restaurantName;
    private String restaurantPhone;
    private String restaurantAddress;
    private String restaurantEmail;
    private String restaurantWebsite;
    private String restaurantPiva;
    private Bitmap restaurantPhoto;
    private OpeningHours openingHours;
    public ArrayList<Reservation> reservations;
    //public ArrayList<ActivityDisplayOffer> dailyOffers;

    public Restaurant(){
        this.restaurantName = null;
        this.restaurantPhone = null;
        this.restaurantAddress = null;
        this.restaurantEmail = null;
        this.restaurantWebsite = null;
        this.restaurantPiva = null;
        this.restaurantPhoto = null;
        this.openingHours = null;
        this.reservations = null;
        //this.dailyOffers = null;
    }


    //getter
    public String getRestaurantName() {
        return restaurantName;
    }

    public String getRestaurantPhone() {
        return restaurantPhone;
    }

    public String getRestaurantAddress(){
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

    public Bitmap getRestaurantPhoto(){
        return restaurantPhoto;
    }

    public OpeningHours getOpeningHours(){ return  openingHours; }


    //setter
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setRestaurantPhone(String restaurantPhone) {
        this.restaurantPhone = restaurantPhone;
    }

    public void setRestaurantAddress(String restaurantAddress){
        this.restaurantAddress = restaurantAddress;
    }

    public void setRestaurantEmail(String restaurantEmail) {
        this.restaurantEmail = restaurantEmail;
    }

    public void setRestaurantWebsite(String restaurantWebsite) {
        this.restaurantWebsite = restaurantWebsite;
    }

    public void setRestaurantPiva(String restaurantPiva) {
        this.restaurantPiva = restaurantPiva;
    }

    public void setRestaurantPhoto(Bitmap restaurantPhoto){
        this.restaurantPhoto = restaurantPhoto;
    }

    public void setOpeningHours(OpeningHours openingHours){
        this.openingHours = openingHours;
    }



}