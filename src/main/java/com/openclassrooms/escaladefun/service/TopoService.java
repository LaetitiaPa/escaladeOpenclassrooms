package com.openclassrooms.escaladefun.service;

import java.util.List;

import com.openclassrooms.escaladefun.entity.Topo;

public interface TopoService {

    public Topo saveTopo( Topo topo );

    public Boolean deleteTopo( Long topoId );

    public Topo editTopo( Topo topo );

    public Topo findTopo( Long topoId );

    public List<Topo> getAllTopos();

}
