package org.pollub.lab_10.repositories;

import org.pollub.lab_10.models.UserDao;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDao, Long> {
    UserDao findByUsername(String username);
}
