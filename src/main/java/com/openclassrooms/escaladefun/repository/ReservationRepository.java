package com.openclassrooms.escaladefun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.escaladefun.entity.Reservation;

@Repository( "resaRepository" )
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
