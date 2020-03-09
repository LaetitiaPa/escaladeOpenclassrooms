package com.openclassrooms.escaladefun.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Topo {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "topo_id" )
    private Long        id;

    @NotBlank( message = "Merci de renseigner un titre" )
    private String      title;

    @NotBlank( message = "Merci de renseigner une description" )
    private String      description;

    @NotBlank( message = "Merci de renseigner une région" )
    private String      region;

    @DateTimeFormat( iso = DateTimeFormat.ISO.DATE )
    private Date        publishingDate;

    private boolean     availability = true;

    @OneToOne
    @JoinColumn( name = "user_id" )
    private User        user;

    @ManyToOne
    @JoinColumn( name = "resa_id" )
    private Reservation resa;

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public void setAvailability( Boolean availability ) {
        this.availability = availability;
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

    public User getUser() {
        return user;
    }

    public void setUser( User user ) {
        this.user = user;
    }

    public Date getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate( Date publishingDate ) {
        this.publishingDate = publishingDate;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability( boolean availability ) {
        this.availability = availability;
    }

    public Reservation getResa() {
        return resa;
    }

    public void setResa( Reservation resa ) {
        this.resa = resa;
    }

    public Topo( @NotBlank( message = "Merci de renseigner un titre" ) String title,
            @NotBlank( message = "Merci de renseigner une description" ) String description,
            @NotBlank( message = "Merci de renseigner une région" ) String region, Date publishingDate,
            boolean availability, User user ) {
        super();
        this.title = title;
        this.description = description;
        this.region = region;
        this.publishingDate = publishingDate;
        this.availability = availability;
        this.user = user;

    }

    public Topo() {
        // TODO Auto-generated constructor stub
    }

}
