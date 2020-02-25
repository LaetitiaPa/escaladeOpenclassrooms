package com.openclassrooms.escaladefun.controller;

public class SpotByName {

    private String  name;

    private String  region;

    private String  departement;

    private Integer track_number;

    private Integer height;

    private String  cotation;

    private String  climbing_type;

    private String  holds_type;

    private String  tracks_pract;

    private boolean tag;

    public String getDepartement() {
        return departement;
    }

    public void setDepartement( String departement ) {
        this.departement = departement;
    }

    public Integer getTrack_number() {
        return track_number;
    }

    public void setTrack_number( Integer track_number ) {
        this.track_number = track_number;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight( Integer height ) {
        this.height = height;
    }

    public String getCotation() {
        return cotation;
    }

    public void setCotation( String cotation ) {
        this.cotation = cotation;
    }

    public String getClimbing_type() {
        return climbing_type;
    }

    public void setClimbing_type( String climbing_type ) {
        this.climbing_type = climbing_type;
    }

    public String getHolds_type() {
        return holds_type;
    }

    public void setHolds_type( String holds_type ) {
        this.holds_type = holds_type;
    }

    public String getTracks_pract() {
        return tracks_pract;
    }

    public void setTracks_pract( String tracks_pract ) {
        this.tracks_pract = tracks_pract;
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

    public SpotByName( String name, String region, String departement, Integer track_number, Integer height,
            String cotation, String climbing_type, String holds_type, String tracks_pract ) {
        super();
        this.name = name;
        this.region = region;
        this.departement = departement;
        this.track_number = track_number;
        this.height = height;
        this.cotation = cotation;
        this.climbing_type = climbing_type;
        this.holds_type = holds_type;
        this.tracks_pract = tracks_pract;
    }

    public boolean isTag() {
        return tag;
    }

    public void setTag( boolean tag ) {
        this.tag = tag;
    }

}
