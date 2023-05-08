package br.com.itau.infrastructure.user.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public record UserResponse(
  @JsonProperty("id") String id,
  @JsonProperty("name") String name,
  @JsonProperty("email") String email,
  @JsonProperty("birthdate") LocalDate birthdate,
  @JsonProperty("address") String address,
  @JsonProperty("skills") List<String> skills,
  @JsonProperty("is_active") Boolean isActive,
  @JsonProperty("created_at") Instant createdAt,  
  @JsonProperty("updated_at") Instant updatedAt,
  @JsonProperty("deleted_at") Instant deletedAt) {
}
