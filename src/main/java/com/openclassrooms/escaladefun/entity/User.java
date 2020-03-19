package com.openclassrooms.escaladefun.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "user_id" )
    private Long          id;

    @Email( message = "*Please provide a valid Email" )
    @NotEmpty( message = "*Please provide an email" )
    private String        email;

    @Length( min = 5, message = "*Your password must have at least 5 characters" )
    @NotEmpty( message = "*Please provide your password" )
    @Transient
    private String        password;

    @NotEmpty( message = "*Please provide your name" )
    private String        name;

    @NotEmpty( message = "*Please provide your last name" )
    private String        lastName;

    private boolean       active;

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "user" )
    private List<Comment> comments;

    @OneToMany( mappedBy = "user", cascade = CascadeType.ALL )
    private List<Topo>    topos;

    @OneToMany( mappedBy = "user", cascade = CascadeType.ALL )
    private List<Spot>    spots;

    @OneToOne( cascade = CascadeType.ALL )
    @JoinTable( name = "user_role", joinColumns = @JoinColumn( name = "user_id" ), inverseJoinColumns = @JoinColumn( name = "role_id" ) )
    private Role          role;

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive( boolean active ) {
        this.active = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole( Role role ) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", lastName="
                + lastName + ", active=" + active + ", comments=" + comments + ", topos=" + topos + ", spots=" + spots
                + ", role=" + role + "]";
    }

    public void setComments( List<Comment> comments ) {
        this.comments.clear();

        for ( Comment comment : comments ) {
            comment.setUser( this );
            this.comments.add( comment );
        }
    }

}