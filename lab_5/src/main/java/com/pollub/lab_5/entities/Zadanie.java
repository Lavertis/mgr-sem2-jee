package com.pollub.lab_5.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Zadanie {
    @GeneratedValue
    @Id
    private Long id;

    @Column
    private String nazwa;

    @Column
    @Lob
    private String opis;

    @Column
    private Double koszt;

    @Column
    private Boolean wykonane = false;

    public Zadanie() {
        this.koszt = 2000.0;
        this.nazwa = "Zadanie";
        this.opis = "Zadanie do wykonania";
    }
}
