package com.sorbellini.s214631.lab2;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.net.URI;

/**
 * Created by eugeniosorbellini on 01/04/16.
 */
public class Dish implements Parcelable{
    private double price;
    private String photo;
    private String name;
    private String description;
    private int availability;

    //constructor
    public Dish(){
        this.price = 0;
        this.photo = null;
        this.name = null;
        this.description = null;
        this.availability = 0;
    }

    //getter
    public double getPrice() { return this.price; }
    public String getPhoto() { return this.photo; }
    public String getName() { return this.name; }
    public String getDescription() { return this.description; }
    public int getAvailability() { return this.availability; }

    //setter
    public void setPrice(double price) { this.price =price; }
    public void setPhoto(String photo) { this.photo = photo; }
    public void setName(String title) { this.name = title; }
    public void setDescription(String description) { this.description = description; }
    public void setAvailability(int availability) { this.availability = availability; }


    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeDouble(this.price);
        dest.writeString(this.photo);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeInt(this.availability);
    }

    //Creator
    public static final Parcelable.Creator<Dish> CREATOR = new Parcelable.Creator<Dish>() {
        public Dish createFromParcel(Parcel in) {
            return new Dish(in);
        }

        public Dish[] newArray(int size) {
            return new Dish[size];
        }
    };

    //De-parcel object
    public Dish(Parcel in){
        this.price = in.readDouble();
        this.photo = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.availability = in.readInt();
    }

}
