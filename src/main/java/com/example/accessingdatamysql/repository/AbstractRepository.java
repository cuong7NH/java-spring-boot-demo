package com.example.accessingdatamysql.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Copyright 2021 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 */
@NoRepositoryBean
public interface AbstractRepository<T> extends JpaRepository<T, Long>,
                                               JpaSpecificationExecutor<T> {
}

