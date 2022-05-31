package com.example.accessingdatamysql.repository;

import com.example.accessingdatamysql.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends AbstractRepository<User> {

    @Query("Select g from User g "
            + "WHERE (?1 is null OR g.id = ?1 ) "
    )
    Page<User> filter(Long id, Pageable pageable);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);

}
