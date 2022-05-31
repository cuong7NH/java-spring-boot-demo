package com.example.accessingdatamysql.service;
import com.example.accessingdatamysql.dto.request.CreateUserRequest;
import com.example.accessingdatamysql.dto.request.UpdateUserRequest;
import com.example.accessingdatamysql.dto.request.UserRequest;
import com.example.accessingdatamysql.entity.User;
import com.example.accessingdatamysql.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> findById(Long Id) {
        return userRepository.findById(Id);
    }
    public Page<User> findUsers(UserRequest request) {
        var pageSize = PageRequest.of(request.getPage(), request.getSize());
        return userRepository.filter(request.getId(), pageSize);
    }

    @Transactional
    public User saveUser(CreateUserRequest createUserRequest) {
        User user = new User();
        user.setFullName(createUserRequest.getFullName());
        user.setUsername(createUserRequest.getUsername());
        user.setPassword(createUserRequest.getPassword());
        return userRepository.save(user);
    }
    @Transactional
    public User updateUser(Long id, UpdateUserRequest req) {
        RuntimeException Exception = null;
        var user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        if (user == null) throw new RuntimeException("sdf");
        user.setFullName(req.getFullName());
        user.setPassword(req.getPassword());
        return userRepository.save(user);
    }
    public Boolean deleteUser(Long id) {
        userRepository.deleteById(id);
        return null;
    }

}
