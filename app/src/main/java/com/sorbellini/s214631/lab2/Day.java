package com.sorbellini.s214631.lab2;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by eugeniosorbellini on 04/04/16.
 */
public class Day {
    private final String dayName;
    private Boolean open;
    public final TimeRange morning;
    public final TimeRange afternoon;

    public Day(int type){
        if(type < 1 || type > 7)
            throw new IllegalArgumentException("Day should be in the range [1-7]");
        String[] namesOfDays = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
        this.dayName = namesOfDays[type];
        this.open = false;
        this.morning = new TimeRange();
        this.afternoon = new TimeRange();
    }



    //getter
    public Boolean getOpen(){ return this.open; }
    public String getDayName(){ return this.dayName;}


    //setter
    public void setOpen(Boolean open){
        this.open = open;
    }

}

class TimeRange {
    private String timeFrom;
    private String timeTo;

    //constructor
    protected TimeRange(){
        this.timeFrom = null;
        this.timeTo = null;
    }

    //getter
    public String getTimeFrom(){ return this.timeFrom; }
    public String getTimeTo(){ return this.timeTo; }

    //setter
    public void setTimeFrom(String timeFrom){ this.timeFrom = timeFrom; }
    public void setTimeTo(String timeTo){ this.timeTo = timeTo; }


}
