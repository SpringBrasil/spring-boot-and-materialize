package com.crud.financeiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    private static final String VIEWER = "index";

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView(VIEWER);
    }

}
