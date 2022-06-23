
package com.example.eventuser.api;

import com.example.eventuser.dto.PagingResponse;
import com.example.eventuser.dto.request.UpdateUserRequest;
import com.example.eventuser.dto.request.UserRequest;
import com.example.eventuser.dto.response.ResponseObject;
import com.example.eventuser.dto.response.UserResponse;
import com.example.eventuser.entity.User;
import com.example.eventuser.exception.NotFoundRecordException;
import com.example.eventuser.mapper.UserMapper;
import com.example.eventuser.security.jwt.AuthTokenFilter;
import com.example.eventuser.security.jwt.JwtUtils;
import com.example.eventuser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final UserMapper userMapper;
    private  final AuthTokenFilter authTokenFilter;
    private final JwtUtils jwtUtils;
    @GetMapping("")
    public ResponseEntity<PagingResponse<UserResponse>> getUsers(UserRequest req) {
        var page = service.findUsers(req);
        var rs = new PagingResponse<UserResponse>()
                .setTotal((int) page.getTotalElements())
                .setData(userMapper.userToUserResponse(page.getContent()));
        return ResponseEntity.ok(rs);
    }
    @GetMapping("/notfoundexception")
    public String getResourceThatThrowsNotFoundException() {
        throw new NotFoundRecordException("Not found thrown by getResourceThatThrowsNotFoundException");
    }
    @GetMapping("/responsestatusexception")
    public String getResourceThatThrowsResponseStatusException() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found thrown by getResourceThatThrowsResponseStatusException");
    }
    @GetMapping("/test")
    public String tesst() throws BindException {
        throw new BindException(new ResponseObject("fail", "sdf", ""), "test");
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getUserById(@PathVariable("id") Long id) {
        Optional<User> foundUser = service.findById(id);
        return foundUser.map(user -> ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Query user successfully", userMapper.userToUserResponse(user))
        )).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("false", "Cant not found user with id = " + id, "")
        ));
    }
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMe(HttpServletRequest request) {
        String jwt = authTokenFilter.parseJwt(request);
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        var rs = service.findByUsername(username).orElseThrow(RuntimeException::new);
        return ResponseEntity.ok(userMapper.userToUserResponse(rs));
    }
    @PutMapping("/me")
    public ResponseEntity<UserResponse> updateMe(HttpServletRequest request, @Valid @RequestBody UpdateUserRequest req) {
        String jwt = authTokenFilter.parseJwt(request);
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        User user = service.findByUsername(username).orElseThrow(RuntimeException::new);
        var rs = service.updateUser(user.getId(), req);
        return ResponseEntity.ok(userMapper.userToUserResponse(rs));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUserById(@PathVariable("id") Long id, @Valid @RequestBody UpdateUserRequest req) {
        Optional<User> foundUser = service.findById(id);
        UserResponse userResponse = userMapper.userToUserResponse(service.updateUser(id, req));
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteUser(id));
    }


}
