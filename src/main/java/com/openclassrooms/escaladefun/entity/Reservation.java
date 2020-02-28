package com.openclassrooms.escaladefun.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Reservation {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "reservation_id" )
    private Long id;

    @Column( name = "date_reservation" )
    private Date date = new Date();

    @OneToOne( cascade = { CascadeType.PERSIST, CascadeType.MERGE } )
    @JoinColumn( name = "user_id" )
    private User user;

    @OneToOne( cascade = { CascadeType.PERSIST, CascadeType.MERGE } )
    @JoinColumn( name = "topo_id" )
    private Topo topo;

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public Reservation() {
        super();

    }

    public User getUser() {
        return user;
    }

    public void setUser( User user ) {
        this.user = user;
    }

    public Topo getTopo() {
        return topo;
    }

    public void setTopo( Topo topo ) {
        this.topo = topo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate( Date date ) {
        this.date = date;
    }

}
