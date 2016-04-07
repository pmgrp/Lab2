package com.sorbellini.s214631.lab2;

/**
 * Created by eugeniosorbellini on 01/04/16.
 */
public class Customer {
    private String name;
    private String surname;
    private String phone;

    //constructor
    public Customer(){
        this.name = null;
        this.surname = null;
    }

    //getter
    public String getName(){ return this.name; }
    public String getSurname(){ return this.surname; }
    public String getPhone(){ return this.phone; }

    //setter
    public void setName(String name){ this.name = name; }
    public void setSurname(String surname){ this.surname = surname; }
    public void setPhone(String phone){ this.phone = phone; }
}





