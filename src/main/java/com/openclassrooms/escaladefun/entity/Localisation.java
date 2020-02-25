package com.openclassrooms.escaladefun.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Localisation {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "local_id" )
    private Long   id;

    @NotBlank( message = "Merci de renseigner un departement" )
    private String departement;

    @NotBlank( message = "Merci de renseigner une region" )
    private String region;

    @OneToOne
    private Spot   spot;

    public String getDepartement() {
        return departement;
    }

    public void setDepartement( String departement ) {
        this.departement = departement;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion( String region ) {
        this.region = region;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot( Spot spot ) {
        this.spot = spot;
    }

    public Localisation( @NotBlank( message = "Merci de renseigner un departement" ) String departement,
            @NotBlank( message = "Merci de renseigner une region" ) String region, Spot spot ) {
        super();
        this.departement = departement;
        this.region = region;
        this.spot = spot;
    }

    public Localisation() {
        super();
        // TODO Auto-generated constructor stub
    }

}
