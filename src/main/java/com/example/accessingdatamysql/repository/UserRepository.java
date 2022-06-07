package com.example.accessingdatamysql.repository;

import com.example.accessingdatamysql.dto.response.UserCountEventResponse;
import com.example.accessingdatamysql.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository

public interface UserRepository extends AbstractRepository<User> {

    @Query("Select g from User g "
            + "WHERE (?1 is null OR g.id = ?1 ) "
    )

    Page<User> filter(Long id, Pageable pageable);
    @Query(value = "SELECT tbl_user.id, tbl_user.full_name as fullName, tbl_user.username, COUNT(tbl_event.id) AS countEvent"
            + " FROM  tbl_user"
            + " join tbl_event where tbl_user.id = tbl_event.user_id"
            + " GROUP BY tbl_user.id", nativeQuery = true)
    List<UserCountEventResponse> getUserListCountEvent();


    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);

}
