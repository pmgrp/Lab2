package com.sorbellini.s214631.lab2;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by eugeniosorbellini on 01/04/16.
 */
public class Reservation implements Parcelable {
    public static final int ARRIVED = 0;
    public static final int CONFIRMED = 1;
    public static final int REJECTED = 2;
    public static final int COMPLETED = 3;
    private Customer customer;
    public ArrayList<Dish> orderedDishes;
    private String time;
    private String comment;
<<<<<<< HEAD

=======
    private int status;
>>>>>>> eugenio-reservations

    public Reservation(){
        this.customer = null;
        this.orderedDishes = new ArrayList<>();
        this.time = null;
        this.comment = null;
        this.status = ARRIVED;
    }

    //getter
    public Customer getCustomer(){ return this.customer; }
    public String getTime(){ return this.time; }
    public String getComment(){ return this.comment; }
    public int getStatus(){ return this.status; }

    //setter
    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public void setTime(String time){
        this.time = time;
    }

    public void setComment(String comment){ this.comment = comment; }

    public void setStatus(int status){ this.status = status; }


    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        //write inner class
        dest.writeParcelable(this.customer, flags);
        //write list of classes
        dest.writeTypedList(this.orderedDishes);
        dest.writeString(this.time);
        dest.writeString(this.comment);
        dest.writeInt(this.status);
    }

    //Creator
    public static final Parcelable.Creator<Reservation> CREATOR = new Parcelable.Creator<Reservation>() {
        public Reservation createFromParcel(Parcel in) {
            return new Reservation(in);
        }

        public Reservation[] newArray(int size) {
            return new Reservation[size];
        }
    };

    //De-parcel object
    public Reservation(Parcel in){
        //read inner class
        this.customer = in.readParcelable(Customer.class.getClassLoader());
        //read array list of classes
        this.orderedDishes = in.createTypedArrayList(Dish.CREATOR);
        this.time = in.readString();
        this.comment = in.readString();
        this.status = in.readInt();
    }

}
