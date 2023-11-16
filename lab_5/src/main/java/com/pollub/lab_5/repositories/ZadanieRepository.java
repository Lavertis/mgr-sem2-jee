package com.pollub.lab_5.repositories;

import com.pollub.lab_5.entities.Zadanie;
import org.springframework.data.repository.CrudRepository;

public interface ZadanieRepository extends CrudRepository<Zadanie, Long> {
    Iterable<Zadanie> findByWykonane(boolean wykonane);

    Iterable<Zadanie> findByKosztLessThan(double koszt);

    Iterable<Zadanie> findByKosztBetween(double koszt1, double koszt2);
}
