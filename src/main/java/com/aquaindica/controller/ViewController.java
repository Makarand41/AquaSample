package com.aquaindica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping(value = {"/admin/**"})
    public String forwardAdminRoutes() {
        return "forward:/index.html";
    }
}
