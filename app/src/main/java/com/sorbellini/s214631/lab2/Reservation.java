package com.sorbellini.s214631.lab2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by eugeniosorbellini on 01/04/16.
 */
public class Reservation {
    private Customer customer;
    public ArrayList<Dish> orderedDishes;
    private String time;
    private String comment;

    public Reservation(){
        this.customer = null;
        this.orderedDishes = null;
        this.time = null;
        this.comment = null;
    }

    //getter
    public Customer getCustomer(){ return this.customer; }
    public String getTime(){ return this.time; }
    public String getComment(){ return this.comment; }

    //setter
    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public void setTime(String time){
        this.time = time;
    }

    public void setComment(String comment){ this.comment = comment; }

}
