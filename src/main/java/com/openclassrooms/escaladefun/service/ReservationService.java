package com.openclassrooms.escaladefun.service;

import java.util.List;

import com.openclassrooms.escaladefun.entity.Reservation;

public interface ReservationService {

    public Reservation saveResa( Reservation resa );

    public Boolean deleteResa( Long resaId );

    public Reservation editResa( Reservation resa );

    public Reservation findResa( Long resaId );

    public List<Reservation> getAllReservations();
}
