package com.openclassrooms.escaladefun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.escaladefun.entity.Comment;
import com.openclassrooms.escaladefun.repository.CommentRepository;

@Service( "commentService" )
public class CommentServiceImpl implements CommentService {

    @Autowired
    protected CommentRepository commentRepository;

    @Override
    public Comment saveComment( Comment comment ) {
        return commentRepository.save( comment );
    }

    @Override
    public Comment findById( Long id ) {
        Comment comment = commentRepository.getOne( id );

        return comment;
    }

    public Boolean deleteComment( Long commentId ) {
        Comment comment = commentRepository.getOne( commentId );
        if ( comment != null ) {
            commentRepository.delete( comment );
            return true;
        }
        return false;
    }

    @Override
    public Comment editComment( Comment comment ) {
        return commentRepository.save( comment );
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

}
