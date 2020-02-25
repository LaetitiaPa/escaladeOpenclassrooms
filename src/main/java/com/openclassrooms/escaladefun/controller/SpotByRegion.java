package com.openclassrooms.escaladefun.controller;

public class SpotByRegion {

    private String name;

    private String region;

    public SpotByRegion( String name, String region ) {
        this.name = name;
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion( String region ) {
        this.region = region;
    }

}
