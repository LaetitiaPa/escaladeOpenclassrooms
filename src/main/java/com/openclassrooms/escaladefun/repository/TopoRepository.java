package com.openclassrooms.escaladefun.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.openclassrooms.escaladefun.controller.TopoByReservation;
import com.openclassrooms.escaladefun.entity.Topo;

@Repository( "topoRepository" )
public interface TopoRepository extends JpaRepository<Topo, Long> {

    Topo findByTitle( String title );

    Topo findTopoById( Long id );

    List<Topo> findTopoByUserId( Long id );

    @Query( "SELECT NEW com.openclassrooms.escaladefun.controller.TopoByReservation( r.date, r.status, t.title, t.description, t.region, t.availability ) FROM Topo t JOIN Reservation  r ON "
            + "r.topo.id = t.id WHERE r.user.id=:Id" )
    List<TopoByReservation> findTopoByReservation( Long Id );

}
