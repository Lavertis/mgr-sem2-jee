package com.pollub.lab_5.controllers;

import com.pollub.lab_5.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

@Controller
@RequestMapping("countries")
public class CountryController {
    @Autowired
    public CountryRepository countryRepository;

    @GetMapping
    @ResponseBody
    public String getCountries() {
        StringBuilder response = new StringBuilder();
        countryRepository
                .findAll()
                .forEach(i -> response.append(i).append("<br>"));
        return response.toString();
    }

    @GetMapping("continent/{continent}")
    @ResponseBody
    public String getCountriesByContinent(@PathVariable String continent) {
        StringBuilder response = new StringBuilder();
        countryRepository
                .findByContinentIgnoreCase(continent)
                .forEach(i -> response.append(i).append("<br>"));
        return response.toString();
    }

    @GetMapping("population/min/{minPopulation}/max/{maxPopulation}")
    @ResponseBody
    public String getCountriesByPopulationRange(
            @PathVariable Integer minPopulation,
            @PathVariable Integer maxPopulation) {
        StringBuilder response = new StringBuilder();
        countryRepository
                .findByPopulationBetween(minPopulation, maxPopulation)
                .forEach(i -> response.append(i).append("<br>"));
        return response.toString();
    }

    @GetMapping("/continent/{continent}/area/min/{minArea}/max/{maxArea}")
    @ResponseBody
    public String getCountriesByContinentAndAreaRange(
            @PathVariable String continent,
            @PathVariable BigDecimal minArea,
            @PathVariable BigDecimal maxArea) {
        StringBuilder response = new StringBuilder();
        countryRepository
                .findByContinentIgnoreCaseAndSurfaceAreaBetween(continent, minArea, maxArea)
                .forEach(i -> response.append(i).append("<br>"));
        return response.toString();
    }
}
