package com.openclassrooms.escaladefun.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal( AuthenticationManagerBuilder auth )
            throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser( "user" ).password( passwordEncoder().encode( "password" ) ).roles( "USER" )
                .and()
                .withUser( "admin" ).password( passwordEncoder().encode( "admin" ) ).roles( "ADMIN" );
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http

                .authorizeRequests()
                .antMatchers( "/**/*.js", "/**/*.css", "/img/**", "/**/*.scss" ).permitAll()
                .antMatchers( "/" ).permitAll()
                .antMatchers( "/rechercher-spot" ).permitAll()
                .antMatchers( "/login" ).permitAll()
                .antMatchers( "/registration" ).permitAll()
                .antMatchers( "/spots" ).permitAll()
                .antMatchers( "/spot" ).permitAll()
                .antMatchers( "/reservation" ).permitAll()
                .antMatchers( "/afficher-un-spot" ).permitAll()
                .antMatchers( "/registration" ).permitAll()
                .antMatchers( "/admin/**", "/h2web/**" ).hasAuthority( "ADMIN" )
                .anyRequest().authenticated().and().csrf().disable().formLogin()
                .loginPage( "/login" ).failureUrl( "/login?error=true" )
                .defaultSuccessUrl( "/home" ).usernameParameter( "email" )
                .passwordParameter( "password" ).and()
                .headers().frameOptions().disable().and().logout()
                .logoutRequestMatcher( new AntPathRequestMatcher( "/logout" ) )
                .logoutSuccessUrl( "/home" ).and().exceptionHandling()
                .accessDeniedPage( "/403" );

    }

    @Override
    protected void configure( AuthenticationManagerBuilder auth )
            throws Exception {
        auth
                .userDetailsService( userDetailsService )
                .passwordEncoder( passwordEncoder() );
    }

    @Override
    public void configure( WebSecurity web ) throws Exception {
        web
                .ignoring()
                .antMatchers( "/resources/**", "/static/**", "/css/**", "/js/**", "/images/**" );
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

}