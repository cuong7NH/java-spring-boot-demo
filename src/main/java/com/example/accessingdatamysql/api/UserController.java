
package com.example.accessingdatamysql.api;

import com.example.accessingdatamysql.dto.PagingResponse;
import com.example.accessingdatamysql.dto.request.CreateUserRequest;
import com.example.accessingdatamysql.dto.request.UpdateUserRequest;
import com.example.accessingdatamysql.dto.request.UserRequest;
import com.example.accessingdatamysql.dto.response.UserResponse;
import com.example.accessingdatamysql.mapper.UserMapper;
import com.example.accessingdatamysql.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final UserMapper userMapper;

    @GetMapping("/users")
    public ResponseEntity<PagingResponse<UserResponse>> getUsers(UserRequest req) {
        var page = service.findUsers(req);
        var rs = new PagingResponse<UserResponse>()
                .setTotal((int) page.getTotalElements())
                .setData(userMapper.userToUserResponse(page.getContent()));
        return ResponseEntity.ok(rs);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getGModel(@PathVariable("id") Long id) {
        var rs = service.findById(id).orElseThrow(RuntimeException::new);
        return ResponseEntity.ok(userMapper.userToUserResponse(rs));
    }
    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest req) {
        UserResponse userResponse = userMapper.userToUserResponse(service.saveUser(req));
        return ResponseEntity.ok(userResponse);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UpdateUserRequest req) {
        UserResponse userResponse = userMapper.userToUserResponse(service.updateUser(id, req));
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteUser(id));
    }


}
