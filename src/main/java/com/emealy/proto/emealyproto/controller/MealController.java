package com.emealy.proto.emealyproto.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MealController {

    @RequestMapping({"/", "home"})
    public String home(){
        return "home";
    }
}
