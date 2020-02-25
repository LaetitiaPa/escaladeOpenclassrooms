package com.openclassrooms.escaladefun.service;

import com.openclassrooms.escaladefun.entity.User;

public interface UserService {
    public User findUserByName( String name );

    public User findUserByEmail( String email );

    public void saveUser( User user );
}
