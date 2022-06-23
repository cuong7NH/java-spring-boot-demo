package com.example.eventuser.repository;

import com.example.eventuser.entity.Game;
import com.example.eventuser.entity.User;
import com.example.eventuser.entity.UserGame;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Modifying
    @Query("update UserGame u set u.playPoint = ?1 where u.id = ?2")
    int updatePlayPointById(Long playPoint, Long id);

}
