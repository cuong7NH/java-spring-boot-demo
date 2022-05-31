package com.example.accessingdatamysql.repository;
import com.example.accessingdatamysql.constant.ERole;
import com.example.accessingdatamysql.entity.Role;
import java.util.Optional;

public interface RoleRepository extends AbstractRepository<Role>{
    Optional<Role> findByName(ERole name);

}
