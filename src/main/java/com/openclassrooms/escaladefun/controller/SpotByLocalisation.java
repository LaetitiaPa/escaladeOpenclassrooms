package com.openclassrooms.escaladefun.controller;

public class SpotByLocalisation {

    private String region;

    private String cotation;

    public String getRegion() {
        return region;
    }

    public void setRegion( String region ) {
        this.region = region;
    }

    public String getCotation() {
        return cotation;
    }

    public void setCotation( String cotation ) {
        this.cotation = cotation;
    }

    public SpotByLocalisation( String region, String cotation ) {
        super();
        this.region = region;
        this.cotation = cotation;

    }

}
