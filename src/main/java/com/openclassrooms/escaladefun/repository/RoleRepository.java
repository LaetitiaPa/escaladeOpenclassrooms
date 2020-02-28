package com.openclassrooms.escaladefun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.escaladefun.entity.Role;

@Repository( "roleRepository" )
public interface RoleRepository extends JpaRepository<Role, Long> {

}
