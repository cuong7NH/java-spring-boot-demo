


package com.example.accessingdatamysql.repository;

        import com.example.accessingdatamysql.dto.response.EventResponse2;
        import com.example.accessingdatamysql.entity.Event;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.Pageable;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends AbstractRepository<Event> {

@Query(value = "SELECT" +
        " tbl_event.id, tbl_event.status, tbl_event.time," +
        " tbl_user.username," +
        " tbl_game.name AS gameName " +
        " FROM tbl_event" +
        " join tbl_user_game" +
        " on tbl_user_game.id = tbl_event.user_game_id" +
        " join tbl_game" +
        " on tbl_game.id = tbl_user_game.game_id" +
        " join tbl_user" +
        " on tbl_user.id = tbl_user_game.user_id" +
        " where  :username is null or tbl_user.username = :username ", nativeQuery = true)
    Page<EventResponse2> filter(String username, Pageable pageable);

}
