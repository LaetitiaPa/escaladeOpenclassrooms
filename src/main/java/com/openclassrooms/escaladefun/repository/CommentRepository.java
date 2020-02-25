package com.openclassrooms.escaladefun.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.escaladefun.entity.Comment;
import com.openclassrooms.escaladefun.entity.User;

@Repository( "commentRepository" )
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllBySpotId( Long spotId );

    Comment save( User user );

    Optional<Comment> findById( Long id );

}
