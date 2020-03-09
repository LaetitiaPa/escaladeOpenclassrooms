package com.openclassrooms.escaladefun.controller;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

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

    private boolean availability = true;

    @DateTimeFormat( iso = DateTimeFormat.ISO.DATE )
    private Date    publishingDate;

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

    public Date getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate( Date publishingDate ) {
        this.publishingDate = publishingDate;
    }

    public void setAvailability( boolean availability ) {
        this.availability = availability;
    }

    public TopoForm( @Size( min = 4, message = "Le titre doit comporter au moins 4 caractères" ) @NotBlank String title,
            @NotBlank String region, @NotBlank String description, Date publishingDate, boolean availability,
            User user ) {
        super();
        this.title = title;
        this.region = region;
        this.description = description;
        this.publishingDate = publishingDate;
        this.availability = availability;
        this.user = user;
    }

    public TopoForm() {
        // TODO Auto-generated constructor stub
    }

}
