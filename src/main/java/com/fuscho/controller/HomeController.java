package com.fuscho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Créer par mchoraine le 26/02/2016.
 */
@Controller
public class HomeController {

    @RequestMapping("/auth")
    public ModelAndView authentification() {
        return new ModelAndView("auth");
    }

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView home = new ModelAndView("room");
        return home;
    }

    @RequestMapping("/game")
    public ModelAndView game() {
        ModelAndView home = new ModelAndView("home");
        home.addObject("user", "JB");
        return home;
    }
}
