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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.openclassrooms.escaladefun.entity.Comment;
import com.openclassrooms.escaladefun.entity.Localisation;
import com.openclassrooms.escaladefun.entity.Spot;
import com.openclassrooms.escaladefun.entity.User;
import com.openclassrooms.escaladefun.repository.CommentRepository;
import com.openclassrooms.escaladefun.repository.SpotRepository;
import com.openclassrooms.escaladefun.repository.UserRepository;
import com.openclassrooms.escaladefun.service.CommentServiceImpl;
import com.openclassrooms.escaladefun.service.LocalisationServiceImpl;
import com.openclassrooms.escaladefun.service.SpotServiceImpl;
import com.openclassrooms.escaladefun.service.UserServiceImpl;

@Controller
public class SpotController implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger( SpotController.class );

    @Autowired
    SpotServiceImpl             spotServiceImpl;

    @Autowired
    LocalisationServiceImpl     localServiceImpl;

    @Autowired
    SpotRepository              spotRepository;

    @Autowired
    CommentRepository           commentRepository;

    @Autowired
    UserRepository              userRepository;

    @Autowired
    CommentServiceImpl          commentServiceImpl;

    @Autowired
    UserServiceImpl             userServiceImpl;

    @RequestMapping( value = "/ajouter-un-spot", method = RequestMethod.GET )
    public ModelAndView createNewSpot( Model model ) {

        ModelAndView modelAndView = new ModelAndView();
        Spot Spot = new Spot();
        Localisation Local = new Localisation();
        modelAndView.addObject( "spot", Spot );
        modelAndView.addObject( "local", Local );
        modelAndView.setViewName( "spot-form" );

        return modelAndView;
    }

    @RequestMapping( value = "/ajouter-un-spot/enregistré", method = RequestMethod.POST )
    public ModelAndView saveSpot( @ModelAttribute( "spot" ) Spot spot, BindingResult resultSpot,
            @ModelAttribute( "local" ) Localisation local,
            BindingResult resultLocal,
            Model model, HttpSession httpSession ) {
        ModelAndView modelAndView = new ModelAndView();
        Spot spotExists = spotRepository.findByName( spot.getName() );
        if ( spotExists != null ) {
            resultSpot
                    .rejectValue( "name", "error.spot",
                            "Ce spot a déjà été enregistré" );
        }
        if ( resultSpot.hasErrors() || resultLocal.hasErrors() ) {
            modelAndView.setViewName( "spot-form" );
        } else {

            User user = (User) httpSession.getAttribute( "loggedUser" );
            spot.setUser( user );
            localServiceImpl.saveLocal( local );
            spotServiceImpl.saveSpot( spot );

            modelAndView.addObject( "successMessage", "Votre site d'escalade a bien été enregistré" );
            modelAndView.addObject( "spot", new Spot() );
            modelAndView.addObject( "local", new Localisation() );

            model.addAttribute( "AllSpots", spotRepository.getSpots() );

            modelAndView.setViewName( "list-spots" );

            log.info( "Un nouveau spot a été créé: " + "Titre :" + spot.getName() );

        }
        return modelAndView;
    }

    @GetMapping( value = "/spots" )
    public ModelAndView getAllSpots( Model model ) {
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute( "AllSpots", spotRepository.getSpots() );

        modelAndView.setViewName( "list-spots" );

        return modelAndView;
    }

    @GetMapping( value = "/afficher-un-spot{name}" )
    public ModelAndView displaySpot( @RequestParam( "name" ) String name, @PathVariable( "name" ) String namePath,
            Model model ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userServiceImpl.findUserByEmail( auth.getName() );

        model.addAttribute( "currentUser", user );

        model.addAttribute( "role", user.getRole() );

        model.addAttribute( "singleSpot", spotRepository.getSpotByName( name ) );
        Spot spot = spotRepository.findByName( name );
        String spotName = spot.getName();
        model.addAttribute( "spotName", spotName );
        Long spotId = spot.getId();
        model.addAttribute( "comments", commentRepository.findAllBySpotId( spotId ) );
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName( "details-spot" );

        log.info( "Le spot " + spot.getName() + "est affiché" );

        return modelAndView;
    }

    @RequestMapping( value = "/ajouter-commentaire-spot{name}", method = RequestMethod.GET )
    public ModelAndView posterCommentaire( @RequestParam( "name" ) String name,
            Model model, HttpSession httpSession ) {
        ModelAndView modelAndView = new ModelAndView();
        Spot spot = spotRepository.findByName( name );
        Long spotId = spot.getId();

        httpSession.setAttribute( "currentSpot", spot );
        model.addAttribute( "singleSpot", spotRepository.getSpotByName( name ) );
        model.addAttribute( "comments", commentRepository.findAllBySpotId( spotId ) );
        modelAndView.addObject( "comment", new Comment() );
        modelAndView.setViewName( "comment-form" );

        return modelAndView;

    }

    @RequestMapping( value = "/ajouter-commentaire/enregistré", method = RequestMethod.POST )
    public ModelAndView saveComment( @ModelAttribute( "comment" ) Comment comment,
            BindingResult resultComment, Model model,
            HttpSession httpSession ) {

        User user = (User) httpSession.getAttribute( "loggedUser" );
        Spot spot = (Spot) httpSession.getAttribute( "currentSpot" );

        ModelAndView modelAndView = new ModelAndView();
        if ( resultComment.hasErrors() ) {
            modelAndView.setViewName( "details-spot" );
        } else {
            comment.setUser( user );
            comment.setSpot( spot );
            commentServiceImpl.saveComment( comment );
            modelAndView.setViewName( "comment-form" );
            httpSession.setAttribute( "currentComment", comment );
            modelAndView.addObject( "successMessage", "Votre commentaire a bien été enregistré !" );
            modelAndView.addObject( "comment", new Comment() );

            log.info( "Le commentaire de l'utilisateur " + user.getName() + "est enregistré" );

        }
        return modelAndView;

    }

    /*
     * private Spot convertSpotByNameToSpot( SpotByName spotByName ) {
     * 
     * Spot spot = new Spot( spotByName.getName(), spotByName.getTrack_number(),
     * spotByName.getHeight(), spotByName.getCotation(),
     * spotByName.getClimbing_type(), spotByName.getHolds_type(),
     * spotByName.getTracks_pract(), spotByName.isTag() );
     * 
     * return spot; }
     */

    @PostMapping( value = "/commentaire/{id}" )
    public String edit( @PathVariable Long id, Model model ) {
        model.addAttribute( "comment", commentServiceImpl.findById( id ) );

        return "comment-form";

    }

    @PostMapping( value = "/commentaire/modifier" )
    public ModelAndView update( @ModelAttribute( "comment" ) @Valid Comment comment, BindingResult result ) {
        ModelAndView modelAndView = new ModelAndView();
        if ( result.hasErrors() ) {

            modelAndView.setViewName( "comment-form" );
            return modelAndView;
        } else {

            commentServiceImpl.editComment( comment );

            modelAndView.addObject( "successMessage", "Votre commentaire a bien été modifié" );
            modelAndView.setViewName( "list-spots" );

            log.info( "Le commentaire est modifié" );

            return modelAndView;
        }
    }

    @PostMapping( value = "/supprimer-commentaire/{id}" )
    public String delete( @PathVariable Long id, Model model ) {
        commentServiceImpl.deleteComment( id );

        log.info( "Le commentaire est supprimé" );

        return "list-spots";
    }

    @RequestMapping( value = "/rechercher-spot", method = RequestMethod.GET )
    public ModelAndView searchPost( Model model,
            @ModelAttribute( "spot" ) Spot spot, @ModelAttribute( "local" ) Localisation local ) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(
                "search-results" );

        return modelAndView;
    }

    @RequestMapping( value = "/spot", method = RequestMethod.GET )
    public ModelAndView displaySpot(
            @RequestParam( value = "cotation" ) String cotation,
            @RequestParam( value = "region" ) String region,
            @RequestParam( value = "height" ) Integer height,
            @RequestParam( value = "climbingType" ) String climbingType,
            @ModelAttribute( "spot" ) Spot spot, @ModelAttribute( "local" ) Localisation local, Model model ) {
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute( "filterSpot",
                spotRepository.findSpotByRegionAndCotation( region, cotation, height, climbingType ) );
        modelAndView.setViewName( "search-results" );

        log.info( "Une nouvelle recherche est effectuée" );

        return modelAndView;
    }

}