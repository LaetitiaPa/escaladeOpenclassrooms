package com.openclassrooms.escaladefun.service;

import java.util.List;

import com.openclassrooms.escaladefun.entity.Comment;

public interface CommentService {

    public Comment saveComment( Comment comment );

    public Boolean deleteComment( Long commentId );

    public Comment editComment( Comment comment );

    public List<Comment> getAllComments();

    Comment findById( Long id );

}
