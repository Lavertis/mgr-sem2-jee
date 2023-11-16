package com.pollub.lab_5.repositories;

import com.pollub.lab_5.entities.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface CountryRepository extends CrudRepository<Country, String> {
    Iterable<Country> findByContinentIgnoreCase(String continent);

    Iterable<Country> findByPopulationBetween(Integer minPopulation, Integer maxPopulation);

    Iterable<Country> findByContinentIgnoreCaseAndSurfaceAreaBetween(String continent, BigDecimal minArea, BigDecimal maxArea);
}
