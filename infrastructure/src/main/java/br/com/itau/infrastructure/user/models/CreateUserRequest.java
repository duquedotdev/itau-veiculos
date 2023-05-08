package br.com.itau.infrastructure.user.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public record CreateUserRequest(
  @JsonProperty("name") String name,
  @JsonProperty("email") String email,
  @JsonProperty("birthdate") LocalDate birthdate,
  @JsonProperty("address") String address,
  @JsonProperty("skills") List<String> skills,
  @JsonProperty("is_active") Boolean isActive
) {
}
