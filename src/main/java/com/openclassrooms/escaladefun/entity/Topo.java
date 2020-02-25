package com.openclassrooms.escaladefun.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Topo {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "topo_id" )
    private Long    id;

    @NotBlank( message = "Merci de renseigner un titre" )
    private String  title;

    @NotBlank( message = "Merci de renseigner une description" )
    private String  description;

    @NotBlank( message = "Merci de renseigner une région" )
    private String  region;

    private Boolean availability = true;

    @ManyToOne
    @JoinColumn( name = "user_id" )
    private User    user;

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

    public Boolean getAvailability() {
        return availability;
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

    public Topo( @NotBlank( message = "Merci de renseigner un titre" ) String title,
            @NotBlank( message = "Merci de renseigner une description" ) String description,
            @NotBlank( message = "Merci de renseigner une région" ) String region, Boolean availability,
            User user ) {
        super();
        this.title = title;
        this.description = description;
        this.region = region;
        this.availability = availability;
        this.user = user;
    }

    public Topo() {
        // TODO Auto-generated constructor stub
    }

}
