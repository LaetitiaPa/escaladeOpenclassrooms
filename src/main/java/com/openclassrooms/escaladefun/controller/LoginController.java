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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.openclassrooms.escaladefun.entity.User;
import com.openclassrooms.escaladefun.repository.SpotRepository;
import com.openclassrooms.escaladefun.service.UserServiceImpl;

@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger( LoginController.class );

    @Autowired
    private UserServiceImpl     userServiceImpl;

    @Autowired
    private SpotRepository      spotRepository;

    @RequestMapping( value = "/", method = RequestMethod.GET )
    public ModelAndView accueil( Model model ) {
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute( "lastSpots", spotRepository.findFirst3SpotByOrderByIdDesc() );

        modelAndView.setViewName( "accueil" );
        return modelAndView;
    }

    @RequestMapping( value = { "/login" }, method = RequestMethod.GET )
    public ModelAndView login( Model model ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName( "login" );
        return modelAndView;
    }

    @RequestMapping( value = "/registration", method = RequestMethod.GET )
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject( "user", user );
        modelAndView.setViewName( "registration" );
        return modelAndView;
    }

    @RequestMapping( value = "/registration", method = RequestMethod.POST )
    public ModelAndView createNewUser( @Valid User user, BindingResult bindingResult ) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userServiceImpl.findUserByEmail( user.getEmail() );
        if ( userExists != null ) {
            bindingResult
                    .rejectValue( "email", "error.user",
                            "Cet mail est déjà utilisé" );
        }
        if ( bindingResult.hasErrors() ) {
            modelAndView.setViewName( "registration" );
        } else {
            userServiceImpl.saveUser( user );
            modelAndView.addObject( "successMessage", "Vous êtes bien inscrit !" );
            modelAndView.addObject( "user", new User() );
            modelAndView.setViewName( "registration" );

            log.info( "Un nouvel utilisateur est créé: " + "Prénom :" + user.getName() + "Nom: " + user.getLastName() );

        }
        return modelAndView;
    }

    @RequestMapping( value = "/home", method = RequestMethod.GET )
    public ModelAndView home( HttpSession httpSession ) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userServiceImpl.findUserByEmail( auth.getName() );
        httpSession.setAttribute( "loggedUser", user );

        modelAndView.addObject( "userName", user.getName() );

        modelAndView.setViewName( "index" );

        return modelAndView;
    }

}
