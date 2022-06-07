package com.example.accessingdatamysql.repository;
import com.example.accessingdatamysql.dto.response.GameCountEventResponse;
import com.example.accessingdatamysql.dto.response.GameResponse;
import com.example.accessingdatamysql.dto.response.GameResponse2;
import com.example.accessingdatamysql.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends AbstractRepository<Game> {
    @Query(value = "SELECT tbl_game.id, tbl_game.name, count(game_id) AS numOfPlayer" +
            " FROM abc.tbl_game" +
            " join abc.tbl_user_game" +
            " on tbl_user_game.game_id = tbl_game.id" +
            " GROUP BY tbl_game.id", nativeQuery = true)
    Page<GameResponse2> filter(Long id, Pageable pageable);
    @Query(value = "SELECT tbl_game.id, tbl_game.name, COUNT(tbl_game.id) AS countEvent FROM abc.tbl_event"
            + " join tbl_game where tbl_event.game_id = tbl_game.id "
            + " group by tbl_game.id", nativeQuery = true)
    List<GameCountEventResponse> getGameListCountEvent();
}
