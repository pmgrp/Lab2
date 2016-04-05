package com.sorbellini.s214631.lab2;

import android.graphics.Bitmap;

/**
 * Created by eugeniosorbellini on 01/04/16.
 */
public class Dish {
    float price;
    Bitmap photo;
    String description;
    int availability;

    //constructor
    public Dish(){
        this.price = 0;
        this.photo = null;
        this.description = null;
        this.availability = 0;
    }

    //getter
    public float getPrice() { return this.price; }
    public Bitmap getPhoto() { return this.photo; }
    public String getDescription() { return this.description; }
    public int getAvailability() { return this.availability; }

    //setter
    public void setPrice(float price) { this.price =price; }
    public void setPhoto(Bitmap photo) { this.photo = photo; }
    public void setDescription(String description) { this.description = description; }
    public void setAvailability(int availability) { this.availability = availability; }
}
