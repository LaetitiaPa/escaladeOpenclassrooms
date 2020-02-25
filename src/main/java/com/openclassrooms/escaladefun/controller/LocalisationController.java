package com.openclassrooms.escaladefun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.openclassrooms.escaladefun.repository.LocalisationRepository;
import com.openclassrooms.escaladefun.repository.SpotRepository;

@Controller
public class LocalisationController implements WebMvcConfigurer {

    @Autowired
    LocalisationRepository localRepository;

    @Autowired
    SpotRepository         spotRepository;
    /*
     * @GetMapping( value = "/trouver-un-spot" ) public ModelAndView
     * findSpotByLocalisation( @RequestParam( "search" ) String search, Model
     * model ) { ModelAndView modelAndView = new ModelAndView();
     * model.addAttribute( "AllSpots", spotRepository.getSpots() );
     * modelAndView.setViewName( "list-spots" );
     * 
     * return modelAndView; }
     */

}
