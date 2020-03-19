package com.openclassrooms.escaladefun.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Comment {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "comment_id" )
    private Long   id;

    @NotNull( message = "Merci de saisir un commentaire" )
    private String comments;

    private Date   date = new Date();

    @OneToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "spot_id", nullable = false, updatable = false, insertable = true )
    @JsonBackReference
    private Spot   spot;

    @OneToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "user_id", nullable = false, updatable = false, insertable = true )
    @JsonBackReference
    private User   user;

    public Comment() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot( Spot spot ) {
        this.spot = spot;
    }

    public User getUser() {
        return user;
    }

    public void setUser( User user ) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments( String comments ) {
        this.comments = comments;
    }

    public Date getDate() {
        return date;
    }

    public void setDate( Date date ) {
        this.date = date;
    }

}
