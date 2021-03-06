package com.openclassrooms.escaladefun.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.escaladefun.entity.User;

@Repository( "userRepository" )
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail( String email );

    User findByName( String name );

    Optional<User> findById( Long id );

}
