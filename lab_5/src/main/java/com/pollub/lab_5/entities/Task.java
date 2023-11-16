package com.pollub.lab_5.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "task")
@Data
public class Task {
    @GeneratedValue
    @Id
    private Long id;

    @Column
    private String name;

    @Column
    @Lob
    private String description;

    @Column
    private Double cost;

    @Column
    private Boolean isDone = false;

    public Task() {
        this.cost = 2000.0;
        this.name = "Task";
        this.description = "Task to be done";
    }
}
