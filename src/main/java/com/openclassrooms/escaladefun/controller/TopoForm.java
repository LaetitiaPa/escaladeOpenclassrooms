package com.openclassrooms.escaladefun.controller;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.openclassrooms.escaladefun.entity.User;

public class TopoForm {

    @Size( min = 4, message = "Le titre doit comporter au moins 4 caractères" )
    @NotBlank
    @Column( name = "title" )
    private String  title;

    @NotBlank
    private String  region;

    @NotBlank
    private String  description;

    private Boolean availability = true;

    private User    user;

    public String getTitle() {
        return title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion( String region ) {
        this.region = region;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser( User user ) {
        this.user = user;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability( Boolean availability ) {
        this.availability = availability;
    }

}
