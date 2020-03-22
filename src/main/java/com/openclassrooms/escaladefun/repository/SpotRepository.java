package com.openclassrooms.escaladefun.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.openclassrooms.escaladefun.controller.SpotByName;
import com.openclassrooms.escaladefun.controller.SpotByRegion;
import com.openclassrooms.escaladefun.entity.Spot;

@Repository( "spotRepository" )
public interface SpotRepository extends JpaRepository<Spot, Long> {

    List<Spot> findByCotation( String cotation );

    @Query( "SELECT NEW com.openclassrooms.escaladefun.controller.SpotByRegion( s.name, l.region, s.tag, s.id ) FROM Spot  s JOIN Localisation  l ON "
            + "l.spot = s.id" )
    List<SpotByRegion> getSpots();

    Spot findByName( String name );

    List<Spot> findByNameIs( String name );

    @Query( "SELECT NEW com.openclassrooms.escaladefun.controller.SpotByName( s.name, l.region, l.departement, s.trackNumber, s.height, s.cotation, s.climbingType, s.holdsType, s.tracksPract) FROM Spot s JOIN Localisation  l ON "
            + "l.spot = s.id WHERE s.name =:name" )
    List<SpotByName> getSpotByName( String name );

    @Query( "SELECT NEW com.openclassrooms.escaladefun.controller.SpotByName( s.name, l.region, l.departement, s.trackNumber, s.height, s.cotation, s.climbingType, s.holdsType, s.tracksPract) FROM Spot s JOIN Localisation  l ON "
            + "l.spot = s.id WHERE l.region =:region and s.cotation=:cotation and s.height=:height and s.climbingType=:climbingType and s.holdsType=:holdsType and s.trackNumber=:trackNumber and s.tracksPract=:tracksPract" )
    List<SpotByName> findSpotByAll( String region, String cotation, Integer height, String climbingType,
            String holdsType, Integer trackNumber, String tracksPract );

    @Query( "SELECT NEW com.openclassrooms.escaladefun.controller.SpotByName( s.name, l.region, l.departement, s.trackNumber, s.height, s.cotation, s.climbingType, s.holdsType, s.tracksPract ) FROM Spot  s JOIN Localisation  l ON "
            + "l.spot = s.id WHERE l.region=:region and s.cotation=:cotation" )
    List<SpotByName> findSpotByRegionAndCotation( String region, String cotation );

    @Query( "SELECT NEW com.openclassrooms.escaladefun.controller.SpotByName( s.name, l.region, l.departement, s.trackNumber, s.height, s.cotation, s.climbingType, s.holdsType, s.tracksPract ) FROM Spot  s JOIN Localisation  l ON "
            + "l.spot = s.id WHERE s.height=:height and s.trackNumber=:trackNumber" )
    List<SpotByName> findSpotByHeightAndTrackNumber( Integer height, Integer trackNumber );

}
