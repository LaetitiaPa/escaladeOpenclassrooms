package com.openclassrooms.escaladefun.service;

import java.util.List;

import com.openclassrooms.escaladefun.entity.Spot;

public interface SpotService {

    public Spot saveSpot( Spot spot );

    public Boolean deleteSpot( Long spotId );

    public Spot editSpot( Spot spot );

    public Spot findById( Long spotId );

    public List<Spot> getAllSpots();

}
