package com.example.eventuser.service;
import com.example.eventuser.dto.request.SignupRequest;
import com.example.eventuser.dto.request.UpdateUserRequest;
import com.example.eventuser.dto.request.UserRequest;
import com.example.eventuser.dto.response.MessageResponse;
import com.example.eventuser.dto.response.UserCountEventResponse;
import com.example.eventuser.entity.User;
import com.example.eventuser.exception.MyResourceNotFoundException;
import com.example.eventuser.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public Optional<User> findById(Long Id) {
        return userRepository.findById(Id);
    }
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Page<User> findUsers(UserRequest request) {
        var pageSize = PageRequest.of(request.getPage(), request.getSize());
        return userRepository.filter(request.getId(), pageSize);
    }
    public List<UserCountEventResponse> getUserListCountEvent(){
        return  userRepository.getUserListCountEvent();
    };

    public ResponseEntity<MessageResponse> registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username đã tồn tại!"));
        }
        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()));
        user.setFullName(signUpRequest.getFullName());
        userRepository.save(user);
        return ResponseEntity.ok()
                .body(new MessageResponse("User registered successfully!"));
    }

    @Transactional
    public User updateUser(Long id, UpdateUserRequest req) {
        var user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setFullName(req.getFullName());
            return userRepository.save(user.get());
        } else {
            MyResourceNotFoundException ex = new MyResourceNotFoundException();
                        throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "User Not Found: " + id, ex);
        }
    }
    public Boolean deleteUser(Long id) {
        userRepository.deleteById(id);
        return null;
    }

}
