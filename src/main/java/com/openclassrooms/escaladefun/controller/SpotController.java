package com.openclassrooms.escaladefun.controller;

import javax.servlet.http.HttpSession;

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
import com.openclassrooms.escaladefun.repository.LocalisationRepository;
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
    LocalisationRepository      localRepository;

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
            local.setSpot( spot );
            spotServiceImpl.saveSpot( spot );
            localServiceImpl.saveLocal( local );
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
            Model model, HttpSession httpSession ) {
        ModelAndView modelAndView = new ModelAndView();

        if ( httpSession.getAttribute( "loggedUser" ) != null ) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userServiceImpl.findUserByEmail( auth.getName() );
            model.addAttribute( "currentUser", user );
            model.addAttribute( "role", user.getRole() );
        }

        model.addAttribute( "singleSpot", spotRepository.getSpotByName( name ) );
        Spot spot = spotRepository.findByName( name );
        String spotName = spot.getName();
        String spotImage = spot.getImage();
        model.addAttribute( "spotName", spotName );
        model.addAttribute( "spot", spot );
        model.addAttribute( "spotImage", spotImage );
        Long spotId = spot.getId();
        model.addAttribute( "comments", commentRepository.findAllBySpotId( spotId ) );
        modelAndView.addObject( "comment", new Comment() );
        modelAndView.setViewName( "details-spot" );

        log.info( "Le spot " + spot.getName() + "est affiché" );

        return modelAndView;

    }

    @PostMapping( value = "/afficher-un-spot{name}" )
    public ModelAndView saveComment(
            @PathVariable( "name" ) String pathName,
            @ModelAttribute( "comment" ) Comment comment,
            BindingResult resultComment, Model model,
            HttpSession httpSession ) {

        User user = (User) httpSession.getAttribute( "loggedUser" );
        Spot spot = spotRepository.findByName( pathName );
        ModelAndView modelAndView = new ModelAndView();
        if ( resultComment.hasErrors() ) {
            modelAndView.setViewName( "details-spot" );
        } else {
            comment.setUser( user );
            comment.setSpot( spot );
            commentServiceImpl.saveComment( comment );
            modelAndView.setViewName( "details-spot" );
            httpSession.setAttribute( "currentComment", comment );
            modelAndView.addObject( "successMessage", "Votre commentaire a bien été enregistré !" );
            modelAndView.addObject( "comment", new Comment() );
            model.addAttribute( "comments", commentRepository.findAllBySpotId( spot.getId() ) );
            log.info( "Le commentaire de l'utilisateur " + user.getName() + "est enregistré" );

        }
        return modelAndView;

    }

    @GetMapping( value = "/spot/{id}" )
    public String editSpot( @PathVariable Long id, Model model ) {
        Spot spot = spotServiceImpl.findById( id );
        model.addAttribute( "spot", spotServiceImpl.findById( id ) );
        model.addAttribute( "local", localRepository.findLocalisationBySpotId( spot.getId() ) );
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userServiceImpl.findUserByEmail( auth.getName() );
        model.addAttribute( "role", user.getRole() );

        return "update-spot";

    }

    @PostMapping( value = "/spot/{id}" )
    public ModelAndView updateSpot( @PathVariable Long id, @ModelAttribute Spot spot,
            @ModelAttribute Localisation local ) {
        ModelAndView modelAndView = new ModelAndView();

        User userSpot = spot.getUser();
        String userImage = spot.getImage();
        String spotRemarks = spot.getRemarks();
        spot.setRemarks( spotRemarks );
        spot.setImage( userImage );
        spot.setUser( userSpot );
        spotServiceImpl.editSpot( spot );

        modelAndView.addObject( "successMessage", "Vos modifications ont bien été apportées" );
        modelAndView.setViewName( "redirect:/spots" );

        log.info( "Le spot est modifié" );

        return modelAndView;
    }

    @PostMapping( value = "/supprimer-spot/{id}" )
    public String deleteSpot( @PathVariable Long id, Model model ) {
        spotServiceImpl.deleteSpot( id );

        log.info( "Le commentaire est supprimé" );

        return "redirect:/dashboard";
    }

    @GetMapping( value = "/commentaire/{id}" )
    public String edit( @PathVariable Long id, Model model ) {
        model.addAttribute( "comment", commentServiceImpl.findById( id ) );

        return "update-comment";

    }

    @PostMapping( value = "/modifier/{id}" )
    public ModelAndView updateComment( @PathVariable Long id, @ModelAttribute( "comment" ) Comment comment,
            BindingResult result, Model model ) {

        model.addAttribute( "comment", commentServiceImpl.findById( id ) );

        Comment currentComment = commentServiceImpl.findById( id );
        Spot spot = currentComment.getSpot();
        User user = currentComment.getUser();
        model.addAttribute( "user", currentComment.getUser() );
        ModelAndView modelAndView = new ModelAndView();

        if ( result.hasErrors() ) {

            modelAndView.setViewName( "update-comment" );
            return modelAndView;
        } else {

            comment.setSpot( spot );
            comment.setUser( user );
            commentServiceImpl.editComment( comment );

            modelAndView.addObject( "successMessage", "Le commentaire a bien été modifié" );
            modelAndView.setViewName( "update-comment" );

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
                "search-form" );

        return modelAndView;
    }

    @RequestMapping( value = "/spot", method = RequestMethod.GET )
    public ModelAndView displaySpot( String cotation, String region, Integer height, String climbingType,
            String holdsType, Integer trackNumber, String tracksPract, @ModelAttribute( "spot" ) Spot spot,
            @ModelAttribute( "local" ) Localisation local,
            Model model ) {
        ModelAndView modelAndView = new ModelAndView();

        if ( !region.isEmpty() && cotation.isEmpty() && climbingType.isEmpty() && holdsType.isEmpty() && height == null
                && trackNumber == null && tracksPract.isEmpty() ) {

            model.addAttribute( "filterSpot", spotRepository.findSpotsByRegion( region ) );
            modelAndView.setViewName( "search-form" );

        } else if ( !region.isEmpty() && !cotation.isEmpty() && climbingType.isEmpty() && holdsType.isEmpty()
                && height == null && trackNumber == null && tracksPract.isEmpty() ) {

            model.addAttribute( "filterSpot", spotRepository.findSpotByRegionAndCotation( region, cotation ) );
            modelAndView.setViewName( "search-form" );

        } else if ( height != null && trackNumber != null && region.isEmpty() && cotation.isEmpty()
                && climbingType.isEmpty() && holdsType.isEmpty()
                && tracksPract.isEmpty() ) {
            model.addAttribute( "filterSpot", spotRepository.findSpotByHeightAndTrackNumber( height, trackNumber ) );
            modelAndView.setViewName( "search-form" );

        } else if ( region.isEmpty() && cotation.isEmpty() && climbingType.isEmpty() && holdsType.isEmpty()
                && height == null && trackNumber == null && tracksPract.isEmpty() ) {
            model.addAttribute( "filterSpot", spotRepository.getSpots() );
            modelAndView.setViewName( "search-form" );
        } else {
            modelAndView.addObject( "failedMessage", "Aucun résultat n'a été trouvé" );

        }
        log.info( "Une nouvelle recherche est effectuée" );
        return modelAndView;
    }

}