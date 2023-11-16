package com.pollub.lab_5.repositories;

import com.pollub.lab_5.entities.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    Iterable<Task> findByIsDone(boolean wykonane);

    Iterable<Task> findByCostLessThan(double koszt);

    Iterable<Task> findByCostBetween(double koszt1, double koszt2);
}
