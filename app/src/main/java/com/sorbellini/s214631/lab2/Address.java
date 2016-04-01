package com.sorbellini.s214631.lab2;

/**
 * Created by eugeniosorbellini on 01/04/16.
 */
public class Address {

    private String city;
    private String zipCode;
    private String street;
    private int number;

    //constructor
    public Address(){
        this.city = null;
        this.zipCode = null;
        this.street = null;
        this.number = 0;
    }

    public Address(String city, String zipCode, String street, int number ){
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
        this.number = number;
    }


    //getter
    public String getCity(){
        return city;
    }

    public String getZipCode(){
        return zipCode;
    }

    public String getStreet(){
        return street;
    }

    public int getNumber(){
        return number;
    }

    //setter
    public void setCity(String city){
        this.city = city;
    }

    public void setZipCode(String zipCode){
        this.zipCode = zipCode;
    }

    public void setStreet(String street){
        this.street = street;
    }

    public void setNumber(int number){
        this.number = number;
    }



}
