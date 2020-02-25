package com.openclassrooms.escaladefun.service;

import java.util.List;

import com.openclassrooms.escaladefun.entity.Localisation;

public interface LocalisationService {

    public Localisation saveLocal( Localisation local );

    public Boolean deleteLocal( Long localId );

    public Localisation editLocal( Localisation local );

    public Localisation findLocal( Long localId );

    public List<Localisation> getAllLocalisations();

}
