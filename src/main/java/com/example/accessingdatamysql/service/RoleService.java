package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.entity.Role;
import com.example.accessingdatamysql.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j // 1 loại logger
public class RoleService {

    private final RoleRepository roleRepository;
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
