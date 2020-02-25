package com.openclassrooms.escaladefun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.escaladefun.entity.Localisation;
import com.openclassrooms.escaladefun.repository.LocalisationRepository;

@Service( "LocalService" )
public class LocalisationServiceImpl implements LocalisationService {

    @Autowired
    protected LocalisationRepository localRepository;

    @Override
    public Localisation saveLocal( Localisation local ) {
        return localRepository.save( local );
    }

    @Override
    public Boolean deleteLocal( Long localId ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Localisation editLocal( Localisation local ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Localisation findLocal( Long localId ) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Localisation> getAllLocalisations() {
        return localRepository.findAll();
    }

}
