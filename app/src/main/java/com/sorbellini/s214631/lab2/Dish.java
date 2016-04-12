package com.sorbellini.s214631.lab2;

import android.graphics.Bitmap;
<<<<<<< HEAD
import android.os.Parcel;
import android.os.Parcelable;
=======
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.net.URI;
>>>>>>> eugenio-reservations

/**
 * Created by eugeniosorbellini on 01/04/16.
 */
<<<<<<< HEAD
public class Dish implements Parcelable {
    String name;
    float price;
    Bitmap photo;
    String description;
    int availability;
=======
public class Dish implements Parcelable{
    private double price;
    private String photo;
    private String name;
    private String description;
    private int availability;
>>>>>>> eugenio-reservations

    //constructor
    public Dish(){
        this.price = 0;
        this.photo = null;
        this.name = null;
        this.description = null;
        this.availability = 0;
        this.name = null;
    }

    //getter
    public double getPrice() { return this.price; }
    public String getPhoto() { return this.photo; }
    public String getName() { return this.name; }
    public String getDescription() { return this.description; }
    public int getAvailability() { return this.availability; }
    public String getName() { return this.name; }

    //setter
    public void setPrice(double price) { this.price =price; }
    public void setPhoto(String photo) { this.photo = photo; }
    public void setName(String title) { this.name = title; }
    public void setDescription(String description) { this.description = description; }
    public void setName(String Name) { this.name = name; }
    public void setAvailability(int availability) { this.availability = availability; }

<<<<<<< HEAD
=======

>>>>>>> eugenio-reservations
    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
<<<<<<< HEAD
        dest.writeFloat(this.price);
        this.photo.writeToParcel(dest, 0);
=======
        dest.writeDouble(this.price);
        dest.writeString(this.photo);
        dest.writeString(this.name);
>>>>>>> eugenio-reservations
        dest.writeString(this.description);
        dest.writeInt(this.availability);
    }

    //Creator
<<<<<<< HEAD
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
=======
    public static final Parcelable.Creator<Dish> CREATOR = new Parcelable.Creator<Dish>() {
>>>>>>> eugenio-reservations
        public Dish createFromParcel(Parcel in) {
            return new Dish(in);
        }

        public Dish[] newArray(int size) {
            return new Dish[size];
        }
    };

    //De-parcel object
    public Dish(Parcel in){
<<<<<<< HEAD
        this.price = in.readFloat();
        this.photo = Bitmap.CREATOR.createFromParcel(in);
        this.description = in.readString();
        this.price = in.readInt();
    }

}
=======
        this.price = in.readDouble();
        this.photo = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.availability = in.readInt();
    }

}
>>>>>>> eugenio-reservations
