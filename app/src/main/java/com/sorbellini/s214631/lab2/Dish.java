package com.sorbellini.s214631.lab2;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by eugeniosorbellini on 01/04/16.
 */
public class Dish implements Parcelable {
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

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeFloat(this.price);
        this.photo.writeToParcel(dest, 0);
        dest.writeString(this.description);
        dest.writeInt(this.availability);
    }

    //Creator
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Dish createFromParcel(Parcel in) {
            return new Dish(in);
        }

        public Dish[] newArray(int size) {
            return new Dish[size];
        }
    };

    //De-parcel object
    public Dish(Parcel in){
        this.price = in.readFloat();
        this.photo = Bitmap.CREATOR.createFromParcel(in);
        this.description = in.readString();
        this.price = in.readInt();
    }

}
