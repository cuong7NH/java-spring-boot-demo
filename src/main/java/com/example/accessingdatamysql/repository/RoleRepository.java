package com.example.accessingdatamysql.repository;
import com.example.accessingdatamysql.constant.ERole;
import com.example.accessingdatamysql.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface RoleRepository extends AbstractRepository<Role>{
    Optional<Role> findByName(ERole name);

}
