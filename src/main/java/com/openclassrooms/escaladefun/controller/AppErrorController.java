/*
 * package com.openclassrooms.escaladefun.controller;
 * 
 * import org.springframework.boot.web.servlet.error.ErrorController; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.web.bind.annotation.RequestMapping;
 * 
 * @Controller( "error" ) public class AppErrorController implements
 * ErrorController {
 * 
 * private static final String PATH = "/403";
 * 
 * @Override public String getErrorPath() {
 * 
 * return PATH; }
 * 
 * @RequestMapping( value = PATH ) public String accessDenied() {
 * 
 * return "redirect:/login"; }
 * 
 * }
 */