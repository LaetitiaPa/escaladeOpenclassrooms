package com.openclassrooms.escaladefun.controller;

public class SpotByRegion {

    private String  name;

    private String  region;

    private boolean tag;

    private Long    id;

    public SpotByRegion( String name, String region, boolean tag, Long id ) {
        this.name = name;
        this.region = region;
        this.tag = tag;
        this.setId( id );
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

    public boolean isTag() {
        return tag;
    }

    public void setTag( boolean tag ) {
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

}
