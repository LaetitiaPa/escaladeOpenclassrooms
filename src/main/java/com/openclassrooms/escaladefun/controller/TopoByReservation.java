package com.openclassrooms.escaladefun.controller;

import java.util.Date;

public class TopoByReservation {

    private Date    date;

    private boolean status;

    private String  title;

    private String  description;

    private String  region;

    private boolean availability;

    public Date getDate() {
        return date;
    }

    public void setDate( Date date ) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus( boolean status ) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion( String region ) {
        this.region = region;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability( boolean availability ) {
        this.availability = availability;
    }

    public TopoByReservation( Date date, boolean status, String title, String description, String region,
            boolean availability ) {
        super();
        this.date = date;
        this.status = status;
        this.title = title;
        this.description = description;
        this.region = region;
        this.availability = availability;
    }

}
