package com.example.accessingdatamysql.api;

import javax.validation.Valid;

import com.example.accessingdatamysql.dto.request.LoginRequest;
import com.example.accessingdatamysql.dto.request.SignupRequest;
import com.example.accessingdatamysql.dto.response.JwtResponse;
import com.example.accessingdatamysql.dto.response.MessageResponse;
import com.example.accessingdatamysql.security.jwt.JwtUtils;
import com.example.accessingdatamysql.security.services.UserDetailsImpl;
import com.example.accessingdatamysql.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthenticationManager authenticationManager;
  private final UserService userService;

  private final JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    String role = String.valueOf(userDetails.getAuthorities());
    JwtResponse rs = new JwtResponse(jwt,
            "Bearer",
            userDetails.getId(),
            userDetails.getUsername(),
            role);
    return ResponseEntity.ok(rs);
  }

  @PostMapping("/signup")
  public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    return userService.registerUser(signUpRequest);
  }
}
