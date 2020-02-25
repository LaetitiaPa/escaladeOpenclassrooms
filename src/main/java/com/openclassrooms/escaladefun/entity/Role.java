package com.openclassrooms.escaladefun.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity

public class Role {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "role_id" )
    private int              id;

    private String           role;

    @ManyToMany( mappedBy = "roles" )
    private Collection<User> users;

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole( String role ) {
        this.role = role;
    }

}
