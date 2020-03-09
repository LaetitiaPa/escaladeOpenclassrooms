package com.openclassrooms.escaladefun.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.escaladefun.entity.Reservation;

@Repository( "resaRepository" )
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllReservationByUserId( Long id );

    Reservation findReservationByTopoId( Long id );

    Reservation findReservationById( Long id );

}
