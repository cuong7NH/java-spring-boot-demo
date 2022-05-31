package com.example.accessingdatamysql.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.validation.constraints.*;
@Getter
@Setter
public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String username;
  @NotNull
  @NotEmpty
  private String fullName;
  private Set<String> role;
  @NotBlank
  @Size(min = 6, max = 40)
  private String password;

}
