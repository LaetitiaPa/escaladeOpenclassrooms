package com.openclassrooms.escaladefun.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.openclassrooms.escaladefun.service.UserServiceImpl;

@Controller
public class TopoController implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger( SpotController.class );

    @Autowired
    TopoServiceImpl             topoServiceImpl;

    @Autowired
    UserServiceImpl             userServiceImpl;

    @Autowired
    TopoRepository              topoRepository;

    @Autowired
    ReservationServiceImpl      resaImpl;

    @RequestMapping( value = "/dashboard", method = RequestMethod.GET )
    public ModelAndView dashboard( Model model, HttpSession httpSession ) {
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute( "AllTopos", topoServiceImpl.getAllTopos() );
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userServiceImpl.findUserByEmail( auth.getName() );

        model.addAttribute( "currentUser", user );

        model.addAttribute( "role", user.getRole() );

        modelAndView.setViewName( "user-dashboard" );

        return modelAndView;
    }

    @PostMapping( "/statut-topo/{id}" )
    public String statut( @PathVariable( "id" ) Long topoId,
            HttpSession httpSession, Model model ) {
        Reservation resaTopo = new Reservation();
        ModelAndView modelAndView = new ModelAndView();
        Topo topo = topoRepository.findTopoById( topoId );

        resaTopo.setStatus( false );
        resaImpl.saveResa( resaTopo );

        modelAndView.addObject( "successMessage", "Un nouvel emprunt a été créé pour le topo " + topo.getTitle() );

        log.info( "Une nouvelle demande de réservation vient d'être faite pour le topo : " + topo.getTitle() );

        return "redirect:/topos";
    }

    /*
     * @PostMapping( value = "/topo/modifier" ) public ModelAndView
     * update( @ModelAttribute( "comment" ) @Valid Comment comment,
     * BindingResult result ) { ModelAndView modelAndView = new ModelAndView();
     * if ( result.hasErrors() ) {
     * 
     * modelAndView.setViewName( "comment-form" ); return modelAndView; } else {
     * commentServiceImpl.editComment( comment );
     * 
     * modelAndView.addObject( "successMessage",
     * "Votre commentaire a bien été modifié" ); modelAndView.setViewName(
     * "list-spots" );
     * 
     * log.info( "Le commentaire est modifié" );
     * 
     * return modelAndView; } }
     * 
     * @PostMapping( value = "/supprimer/{id}" ) public String
     * delete( @PathVariable Long id, Model model ) {
     * commentServiceImpl.deleteComment( id );
     * 
     * log.info( "Le commentaire est supprimé" );
     * 
     * return "list-spots"; }
     */

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
        Topo topoExists = topoRepository.findByTitle( topo.getTitle() );

        if ( topoExists != null ) {
            bindingResult.rejectValue( "title", "error.topo",
                    "Ce topo a déjà été enregistré" );
        }
        if ( bindingResult.hasErrors() ) {
            modelAndView.setViewName( "topo-form" );
        } else {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userServiceImpl.findUserByEmail( auth.getName() );
            httpSession.setAttribute( "loggedUser", user );
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userServiceImpl.findUserByEmail( auth.getName() );
        httpSession.setAttribute( "loggedUser", user );

        model.addAttribute( "currentUser", user );
        return "list-topos";
    }

    @GetMapping( value = "/topo/{id}" )
    public ModelAndView pageConfirmation( @PathVariable( "id" ) Long topoId, Model model, HttpSession httpSession ) {
        ModelAndView modelAndView = new ModelAndView();

        Topo topo = topoRepository.findTopoById( topoId );
        httpSession.setAttribute( "topo", topo );
        model.addAttribute( "topo", topo );

        modelAndView.setViewName( "resa-topo" );

        return modelAndView;
    }

    @PostMapping( "/reservation/{id}" )
    public String Reservation( @PathVariable( "id" ) Long topoId,
            HttpSession httpSession, Model model ) {
        Reservation resaTopo = new Reservation();
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userServiceImpl.findUserByEmail( auth.getName() );
        httpSession.setAttribute( "loggedUser", user );
        Topo topo = topoRepository.findTopoById( topoId );
        resaTopo.setUser( user );
        resaTopo.setTopo( topo );
        resaImpl.saveResa( resaTopo );

        modelAndView.addObject( "successMessage", "Un nouvel emprunt a été créé pour le topo " + topo.getTitle() );

        log.info( "Une nouvelle demande de réservation vient d'être faite pour le topo : " + topo.getTitle() );

        return "redirect:/topos";
    }

}
