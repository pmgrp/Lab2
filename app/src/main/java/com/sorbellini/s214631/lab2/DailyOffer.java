package com.sorbellini.s214631.lab2;

/**
 * Created by eugeniosorbellini on 13/04/16.
 */
public class DailyOffer {
    private String name;
    private String description;
    private String photo;
    private int price;
    private int availability;

    //default constructor
    public DailyOffer() {
        this.name = "";
        this.description = "";
        this.photo = "";
        this.price = -1;
        this.availability = -1;
    }

    //getter
    public String getName(){
        return this.name;
    }
    public String getDescription(){
        return this.description;
    }
    public String getPhoto(){
        return this.photo;
    }
    public int getPrice(){
        return this.price;
    }
    public int getAvailability(){
        return this.availability;
    }

    //setter
    public void setName(String name){
        this.name = name;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setPhoto(String photo){
        this.photo = photo;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public void setAvailability(int availability){
        this.availability = availability;
    }
}

