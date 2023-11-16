package com.pollub.lab_5.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Country {
    @Id
    private String code;
    private String continent;
    private String name;
    private BigDecimal surfaceArea;
    private Integer population;
}
