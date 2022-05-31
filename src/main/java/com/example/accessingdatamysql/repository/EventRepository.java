


package com.example.accessingdatamysql.repository;

        import com.example.accessingdatamysql.entity.Event;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.Pageable;
        import org.springframework.data.jpa.repository.Query;

        import java.time.LocalDateTime;


public interface EventRepository extends AbstractRepository<Event> {

    @Query("Select g from Event g "
            + "WHERE (?1 is null OR g.id = ?1  ) "
            + "and (?2 is null OR g.game.id = ?2)"
            + "and (?3 is null OR g.time >= ?2)"
            + "and (?4 is null OR g.time <= ?4)"
    )
    Page<Event> filter(Long id, Long gameId, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
