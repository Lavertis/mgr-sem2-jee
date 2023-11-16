package com.pollub.lab_5.controllers;

import com.pollub.lab_5.entities.Zadanie;
import com.pollub.lab_5.repositories.ZadanieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("seedZadania")
    @ResponseBody
    public String seedZadania() {
        Zadanie z;
        double k = 1000;
        boolean wyk = false;
        for (int i = 1; i <= 10; i++) {
            z = new Zadanie();
            z.setNazwa("zadanie " + i);
            z.setOpis("Opis czynności do wykonania w zadaniu " + i);
            z.setKoszt(k);
            z.setWykonane(wyk);
            wyk = !wyk;
            k += 200.50;
            rep.save(z);
        }
        return "Zadania zostały zapisane";
    }

    @RequestMapping("/listaZadan")
    @ResponseBody
    public String listaZadan() {
        StringBuilder odp = new StringBuilder();
        for (Zadanie i : rep.findAll()) {
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }

    @RequestMapping("/listaZadan/wykonane/{wykonane}")
    @ResponseBody
    public String listaZadanByWykonane(@PathVariable boolean wykonane) {
        StringBuilder odp = new StringBuilder();
        for (Zadanie i : rep.findByWykonane(wykonane)) {
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }

    @RequestMapping("/listaZadan/koszt/lessThan/{koszt}")
    @ResponseBody
    public String listaZadanByKosztLessThan(@PathVariable double koszt) {
        StringBuilder odp = new StringBuilder();
        for (Zadanie i : rep.findByKosztLessThan(koszt)) {
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }

    @RequestMapping("/listaZadan/koszt/between/{koszt1}/{koszt2}")
    @ResponseBody
    public String listaZadanByKosztBetween(@PathVariable double koszt1, @PathVariable double koszt2) {
        StringBuilder odp = new StringBuilder();
        for (Zadanie i : rep.findByKosztBetween(koszt1, koszt2)) {
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable Long id) {
        rep.deleteById(id);
        return "Usunięto zadanie o id = " + id;
    }
}
