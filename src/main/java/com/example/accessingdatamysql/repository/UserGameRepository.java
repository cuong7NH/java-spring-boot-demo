package com.example.accessingdatamysql.repository;

import com.example.accessingdatamysql.dto.response.UserGameResponse;
import com.example.accessingdatamysql.entity.Game;
import com.example.accessingdatamysql.entity.User;
import com.example.accessingdatamysql.entity.UserGame;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserGameRepository extends AbstractRepository<UserGame> {

    @Query("Select ug from UserGame ug "
            + "where (?1 is null OR ug.id = ?1  ) "
            + "and (?2 is null OR ug.user.id = ?2) "
            + "and (?3 is null OR ug.user.username = ?3) "
    )
    Page<UserGame> filter(Long id, Long userId, String username, Pageable pageable);

    boolean existsByUserAndGame(User user, Game game);


    @Transactional
    @Modifying
    @Query("update UserGame u set u.lastEventId = ?1, u.playTime = ?2 where u.id = ?3")
    int updateUserGame(Long lastEventId, Long playTime, Long id);

}
