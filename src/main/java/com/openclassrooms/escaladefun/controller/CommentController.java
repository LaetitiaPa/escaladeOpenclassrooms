package com.openclassrooms.escaladefun.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.openclassrooms.escaladefun.repository.CommentRepository;
import com.openclassrooms.escaladefun.service.CommentServiceImpl;

public class CommentController {

    @Autowired
    CommentRepository  commentRepository;

    @Autowired
    CommentServiceImpl commentServiceImpl;

}
