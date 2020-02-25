package com.openclassrooms.escaladefun.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.openclassrooms.escaladefun.entity.Reservation;
import com.openclassrooms.escaladefun.entity.Topo;
import com.openclassrooms.escaladefun.entity.User;
import com.openclassrooms.escaladefun.repository.TopoRepository;
import com.openclassrooms.escaladefun.service.ReservationServiceImpl;
import com.openclassrooms.escaladefun.service.TopoServiceImpl;

@Controller
public class TopoController implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger( SpotController.class );

    @Autowired
    TopoServiceImpl             topoServiceImpl;

    @Autowired
    TopoRepository              topoRepository;

    @Autowired
    ReservationServiceImpl      resaImpl;

    @RequestMapping( value = "/ajouter-un-topo", method = RequestMethod.GET )
    public ModelAndView createNewTopo() {
        ModelAndView modelAndView = new ModelAndView();
        TopoForm topoForm = new TopoForm();
        modelAndView.addObject( "topoForm", topoForm );
        modelAndView.setViewName( "topo-form" );

        return modelAndView;
    }

    @RequestMapping( value = "/ajouter-un-topo/enregistré", method = RequestMethod.POST )
    public ModelAndView saveTopo( @Valid TopoForm topoForm, BindingResult bindingResult, Model model,
            HttpSession httpSession ) {
        Topo topo = convertTopoFormToTopo( topoForm );
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) httpSession.getAttribute( "loggedUser" );
        Topo topoExists = topoRepository.findByTitle( topo.getTitle() );
        if ( topoExists != null ) {
            bindingResult.rejectValue( "title", "error.topo",
                    "Ce topo a déjà été enregistré" );
        }
        if ( bindingResult.hasErrors() ) {
            modelAndView.setViewName( "topo-form" );
        } else {
            topo.setUser( user );
            topoServiceImpl.saveTopo( topo );
            modelAndView.addObject(
                    "successMessage", "Votre topo a bien été enregistré" );
            modelAndView.addObject( "topo", new Topo() );
            model.addAttribute(
                    "AllTopos", topoServiceImpl.getAllTopos() );

            modelAndView.setViewName(
                    "list-topos" );

            log.info( "Le topo" + topo.getTitle() + "vient d'être ajouté" );
        }
        return modelAndView;
    }

    private Topo convertTopoFormToTopo( TopoForm topoForm ) {

        Topo topo = new Topo( topoForm.getTitle(), topoForm.getDescription(),
                topoForm.getRegion(),
                topoForm.getAvailability(), topoForm.getUser() );

        return topo;
    }

    @GetMapping( value = "/topos" )
    public String displayListTopos( Model model, HttpSession httpSession ) {
        model.addAttribute( "AllTopos", topoServiceImpl.getAllTopos() );
        User users = (User) httpSession.getAttribute( "loggedUser" );
        // null value !
        model.addAttribute( "currentUser", users );

        return "list-topos";
    }

    @GetMapping( value = "/topo/{id}" )
    public ModelAndView pageConfirmation( @PathVariable( "id" ) Long topoId, Model model ) {
        ModelAndView modelAndView = new ModelAndView();
        Topo topoCible = topoRepository.findTopoById( topoId );

        model.addAttribute( "topo", topoCible );

        modelAndView.setViewName( "resa-topo" );

        return modelAndView;
    }

    @PostMapping( "/reservation/{id}" )
    public ModelAndView Reservation( @ModelAttribute Reservation reservation, @PathVariable( "id" ) Long topoId,
            HttpSession httpSession ) {
        Reservation resaTopo = new Reservation();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName( "list-topos" );
        Topo topo = topoRepository.findTopoById( topoId );
        resaTopo.setTopo( topo );
        User currentUser = (User) httpSession.getAttribute( "loggedUser" );
        resaTopo.setUser( currentUser );
        resaImpl.saveResa( reservation );
        /*
         * topo.setAvailability( false ); topoRepository.save( topo );
         */

        modelAndView.addObject( "successMessage", "Un nouvel emprunt a été créé pour le topo " + topo.getTitle() );

        log.info( "Une nouvelle demande de réservation vient d'être faite pour le topo : " + topo.getTitle() );

        return modelAndView;
    }

}
