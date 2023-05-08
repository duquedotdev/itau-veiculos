package br.com.itau.application.user.retrieve.list;

import br.com.itau.user.User;
import br.com.itau.user.UserID;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public record UsersListOutput(UserID id,
                              String name,
                              String email,
                              LocalDate birthdate,
                              String address,
                              List<String> skills,
                              Boolean isActive,
                              Instant createdAt,
                              Instant updatedAt,
                              Instant deletedAt) {
  public static UsersListOutput from(final User aUser) {
    return new UsersListOutput(
      aUser.getId(),
      aUser.getName(),
      aUser.getEmail(),
      aUser.getBirthdate(),
      aUser.getAddress(),
      aUser.getSkills(),
      aUser.getActive(),
      aUser.getCreatedAt(),
      aUser.getUpdatedAt(),
      aUser.getDeletedAt());
  }
}
