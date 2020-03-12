package com.openclassrooms.escaladefun.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity

public class Role {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "role_id" )
    private int    id;

    private String role;

    @OneToOne( mappedBy = "role" )
    private User   user;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public String setRole( String role ) {
        return this.role = role;
    }

}