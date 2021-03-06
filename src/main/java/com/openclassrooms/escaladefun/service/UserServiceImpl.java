package com.openclassrooms.escaladefun.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.openclassrooms.escaladefun.entity.Role;
import com.openclassrooms.escaladefun.entity.User;
import com.openclassrooms.escaladefun.repository.UserRepository;

@Service( "userService" )
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository        userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findUserByEmail( String email ) {
        return userRepository.findByEmail( email );
    }

    public void saveUser( User user ) {
        user.setPassword( bCryptPasswordEncoder.encode( user.getPassword() ) );
        user.setActive( true );
        Role role = new Role();
        role.setRole( "USER" );
        user.setRole( role );
        userRepository.save( user );
    }

    /*
     * INSERT INTO `role` VALUES (1,'ADMIN');
     * 
     * @Override public void saveUser(User user) {
     * user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
     * user.setActive(1); Role userRole = roleRepository.findByRole("ADMIN");
     * user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
     * userRepository.save(user); }
     */

    @Transactional
    public UserDetails loadUserByUsername( String userName ) throws UsernameNotFoundException {
        User user = userRepository.findByEmail( userName );

        List<GrantedAuthority> authorities = getUserAuthority( user.getRole() );
        return buildUserForAuthentication( user, authorities );
    }

    private List<GrantedAuthority> getUserAuthority( Role role ) {
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();

        roles.add( new SimpleGrantedAuthority( role.getRole() ) );

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>( roles );
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication( User user, List<GrantedAuthority> authorities ) {
        return new org.springframework.security.core.userdetails.User( user.getEmail(), user.getPassword(),
                user.isActive(), true, true, true, authorities );
    }

    @Override
    public User findUserByName( String name ) {
        return userRepository.findByName( name );
    }
}