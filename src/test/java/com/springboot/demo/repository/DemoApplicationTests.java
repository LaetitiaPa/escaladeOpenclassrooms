package com.springboot.demo.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.openclassrooms.escaladefun.EscladeFunApplication;
import com.openclassrooms.escaladefun.entity.Reservation;
import com.openclassrooms.escaladefun.repository.ReservationRepository;

@RunWith( SpringRunner.class )
@SpringBootTest( classes = EscladeFunApplication.class )
/**
 * 
 * By default, @SpringBootTest does not start the server.
 * 
 * @SpringBootTest(classes =
 *                         DemoApplication.class) @SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT),
 *                         an available port is picked at random each time your
 *                         test runs.
 **/

public class DemoApplicationTests {

    @Autowired
    ReservationRepository resaRepository;

    @Test
    public void resa() {

        Reservation resa = resaRepository.findReservationById( (long) 72 );

        resa.setStatus( true );

        resaRepository.save( resa );

        System.out.println( "statut:" + resa.isStatus() );

    }

}
