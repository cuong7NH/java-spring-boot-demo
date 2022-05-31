package com.example.accessingdatamysql.repository;

import com.example.accessingdatamysql.entity.Game;
import com.example.accessingdatamysql.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;


public interface GameRepository extends AbstractRepository<Game> {
    @Query("Select g from Game g "
            + "WHERE (?1 is null OR g.id = ?1 ) "
    )
    Page<Game> filter(Long id, Pageable pageable);

}
