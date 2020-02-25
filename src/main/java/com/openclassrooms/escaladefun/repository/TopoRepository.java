package com.openclassrooms.escaladefun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.escaladefun.entity.Topo;

@Repository
public interface TopoRepository extends JpaRepository<Topo, Long> {

    Topo findByTitle( String title );

    Topo findTopoById( Long id );

}
