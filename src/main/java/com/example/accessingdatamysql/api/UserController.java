
package com.example.accessingdatamysql.api;

import com.example.accessingdatamysql.dto.PagingResponse;
import com.example.accessingdatamysql.dto.request.CreateUserRequest;
import com.example.accessingdatamysql.dto.request.UpdateUserRequest;
import com.example.accessingdatamysql.dto.request.UserRequest;
import com.example.accessingdatamysql.dto.response.UserCountEventResponse;
import com.example.accessingdatamysql.dto.response.UserResponse;
import com.example.accessingdatamysql.mapper.UserMapper;
import com.example.accessingdatamysql.security.jwt.AuthTokenFilter;
import com.example.accessingdatamysql.security.jwt.JwtUtils;
import com.example.accessingdatamysql.security.services.UserDetailsImpl;
import com.example.accessingdatamysql.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final UserMapper userMapper;

    private  final AuthTokenFilter authTokenFilter;
    private final JwtUtils jwtUtils;
    @GetMapping("/users")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<PagingResponse<UserResponse>> getUsers(UserRequest req) {
        var page = service.findUsers(req);
        var rs = new PagingResponse<UserResponse>()
                .setTotal((int) page.getTotalElements())
                .setData(userMapper.userToUserResponse(page.getContent()));
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/users/count-event")
    ResponseEntity<List<UserCountEventResponse>> getUserListCountEvent(){
        return new ResponseEntity<>(service.getUserListCountEvent(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id) {
        var rs = service.findById(id).orElseThrow(RuntimeException::new);
        return ResponseEntity.ok(userMapper.userToUserResponse(rs));
    }
    @GetMapping("/users/me")
    public ResponseEntity<UserResponse> getMe(HttpServletRequest request) {
        String jwt = authTokenFilter.parseJwt(request);
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        var rs = service.findByUsername(username).orElseThrow(RuntimeException::new);
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
