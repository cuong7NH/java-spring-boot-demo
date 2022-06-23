package com.example.eventuser.api;

import com.example.eventuser.dto.request.LoginRequest;
import com.example.eventuser.dto.request.SignupRequest;
import com.example.eventuser.dto.response.JwtResponse;
import com.example.eventuser.dto.response.MessageResponse;
import com.example.eventuser.security.jwt.JwtUtils;
import com.example.eventuser.security.services.UserDetailsImpl;
import com.example.eventuser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
