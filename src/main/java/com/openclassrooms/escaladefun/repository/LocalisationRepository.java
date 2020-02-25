package com.openclassrooms.escaladefun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.escaladefun.entity.Localisation;

@Repository( "localRepository" )
public interface LocalisationRepository extends JpaRepository<Localisation, Long> {

}
