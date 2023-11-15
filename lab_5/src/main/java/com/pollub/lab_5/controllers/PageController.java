package com.pollub.lab_5.controllers;

import com.pollub.lab_5.entities.Zadanie;
import com.pollub.lab_5.repositories.ZadanieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {
    @Autowired
    public ZadanieRepository rep;

    @RequestMapping("/")
    @ResponseBody
    public String mainPage() {
        return "Hello Spring Boot from mainPage() method!";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String pageTwo() {
        return "Hello Spring Boot from pageTwo() method!";
    }

    @RequestMapping("/listaZadan")
    @ResponseBody
    public String listaZadan() {
        StringBuilder odp = new StringBuilder();
        Zadanie zadanie = new Zadanie();
        rep.save(zadanie);
        for (Zadanie i : rep.findAll()) {
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }
}
