package com.openclassrooms.escaladefun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EscladeFunApplication {

    private static final Logger log = LoggerFactory.getLogger( EscladeFunApplication.class );

    /*
     * @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
     */

    public static void main( String[] args ) {
        log.info( "Démarrage application *** Escalade Fun" );
        SpringApplication.run( EscladeFunApplication.class, args );

    }

}
