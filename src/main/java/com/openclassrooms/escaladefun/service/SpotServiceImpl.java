package com.openclassrooms.escaladefun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openclassrooms.escaladefun.entity.Spot;
import com.openclassrooms.escaladefun.repository.SpotRepository;

@Service( "spotService" )
public class SpotServiceImpl implements SpotService {

    @Autowired
    protected SpotRepository spotRepository;

    @Override
    public Spot saveSpot( Spot spot ) {
        return spotRepository.save( spot );
    }

    @Override
    public Boolean deleteSpot( Long spotId ) {
        Spot spot = spotRepository.getOne( spotId );
        if ( spot != null ) {
            spotRepository.delete( spot );
            return true;
        }
        return false;
    }

    @Override
    public Spot editSpot( Spot spot ) {
        return spotRepository.save( spot );
    }

    @Override
    public Spot findById( Long spotId ) {
        return spotRepository.getOne( spotId );
    }

    @Override
    public List<Spot> getAllSpots() {
        return spotRepository.findAll();
    }

    @Transactional
    public Spot findByName( String name ) {
        return spotRepository.findByName( name );
    }

}
