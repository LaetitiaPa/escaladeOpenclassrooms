package com.openclassrooms.escaladefun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.escaladefun.entity.Reservation;
import com.openclassrooms.escaladefun.repository.ReservationRepository;

@Service( "resaService" )
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public Reservation saveResa( Reservation resa ) {

        return reservationRepository.save( resa );
    }

    @Override
    public Boolean deleteResa( Long resaId ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Reservation editResa( Reservation resa ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Reservation findResa( Long resaId ) {
        return reservationRepository.getOne( resaId );
    }

    @Override
    public List<Reservation> getAllReservations() {

        return reservationRepository.findAll();
    }
}