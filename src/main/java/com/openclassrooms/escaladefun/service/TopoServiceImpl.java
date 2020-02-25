package com.openclassrooms.escaladefun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.escaladefun.entity.Topo;
import com.openclassrooms.escaladefun.repository.TopoRepository;

@Service( "topoService" )
public class TopoServiceImpl implements TopoService {

    @Autowired
    protected TopoRepository topoRepository;

    @Override
    public Topo saveTopo( Topo topo ) {
        return topoRepository.save( topo );
    }

    @Override
    public Boolean deleteTopo( Long topoId ) {
        Topo topo = topoRepository.getOne( topoId );
        if ( topo != null ) {
            topoRepository.delete( topo );
            return true;
        }
        return false;
    }

    @Override
    public Topo editTopo( Topo topo ) {
        return topoRepository.save( topo );
    }

    @Override
    public List<Topo> getAllTopos() {
        return topoRepository.findAll();
    }

    @Override
    public Topo findTopo( Long topoId ) {
        return topoRepository.getOne( topoId );
    }

}
