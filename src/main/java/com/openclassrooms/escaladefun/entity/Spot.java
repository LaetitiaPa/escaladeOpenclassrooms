package com.openclassrooms.escaladefun.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Spot {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "spot_id" )
    private Long          id;

    @NotBlank( message = "Merci de renseigner le nom de la falaise" )
    private String        name;

    @NotNull( message = "Merci de renseigner un nombre de voies" )
    private Integer       track_number;

    @NotNull( message = "Merci de renseigner une hauteur" )
    private Integer       height;

    @NotBlank( message = "Merci de renseigner une cotation" )
    private String        cotation;

    @NotBlank( message = "Merci de renseigner un type d'escalade" )
    private String        climbingType;

    @NotBlank( message = "Merci de renseigner un type de prise" )
    private String        holdsType;

    @NotBlank( message = "Merci de renseigner la praticabilité" )
    private String        tracks_pract;

    private Boolean       tag = false;

    @OneToMany( mappedBy = "spot", cascade = CascadeType.REMOVE )
    private List<Comment> comments;

    @ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.REMOVE )
    @JoinColumn( name = "user_id", nullable = false, updatable = false, insertable = true )
    @JsonBackReference
    private User          user;

    @Override
    public String toString() {
        return "Spot [id=" + id + ", name=" + name + ", track_number=" + track_number + ", height=" + height
                + ", cotation=" + cotation + ", climbingType=" + climbingType + ", holdsType=" + holdsType
                + ", tracks_pract=" + tracks_pract + ", tag=" + tag + ", comment=" + comments + ", user=" + user + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
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

    public String getClimbingType() {
        return climbingType;
    }

    public void setClimbingType( String climbing_type ) {
        this.climbingType = climbing_type;
    }

    public String getTracks_pract() {
        return tracks_pract;
    }

    public void setTracks_pract( String tracks_pract ) {
        this.tracks_pract = tracks_pract;
    }

    public Boolean getTag() {
        return tag;
    }

    public void setTag( Boolean tag ) {
        this.tag = tag;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments( List<Comment> comments ) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser( User user ) {
        this.user = user;
    }

    public Spot( @NotBlank( message = "Merci de renseigner le nom de la falaise" ) String name,
            @NotNull( message = "Merci de renseigner un nombre de voies" ) Integer track_number,
            @NotNull( message = "Merci de renseigner une hauteur" ) Integer height,
            @NotBlank( message = "Merci de renseigner une cotation" ) String cotation,
            @NotBlank( message = "Merci de renseigner un type d'escalade" ) String climbingType,
            @NotBlank( message = "Merci de renseigner un type de prise" ) String holdsType,
            @NotBlank( message = "Merci de renseigner la praticabilité" ) String tracks_pract, Boolean tag ) {
        super();
        this.name = name;

        this.track_number = track_number;
        this.height = height;
        this.cotation = cotation;
        this.climbingType = climbingType;
        this.holdsType = holdsType;
        this.tracks_pract = tracks_pract;
        this.tag = tag;

    }

    public Spot() {
        // TODO Auto-generated constructor stub
    }

    public String getHoldsType() {
        return holdsType;
    }

    public void setHoldsType( String holdsType ) {
        this.holdsType = holdsType;
    }

}
