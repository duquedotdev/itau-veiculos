package br.com.itau.user;

import br.com.itau.AggregateRoot;
import br.com.itau.utils.InstantUtils;
import br.com.itau.validation.ValidationHandler;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class User extends AggregateRoot<UserID> implements Cloneable {
  private String name;
  private String email;
  private LocalDate birthdate;
  private String address;
  private List<String> skills;
  private Boolean isActive;
  private Instant createdAt;
  private Instant updatedAt;
  private Instant deletedAt;

  private User(
    final UserID id,
    final String name,
    final String email,
    final LocalDate birthdate,
    final String address,
    final List<String> skills,
    final Boolean isActive,
    final Instant createdAt,
    final Instant updatedAt,
    final Instant deletedAt
  ) {
    super(id);
    this.name = name;
    this.email = email;
    this.birthdate = birthdate;
    this.address = address;
    this.skills = skills;
    this.isActive = Objects.requireNonNull(isActive, "'isActive' should not be null");
    this.createdAt = Objects.requireNonNull(createdAt, "'createdAt' should not be null");
    this.updatedAt = Objects.requireNonNull(updatedAt, "'updatedAt' should not be null");
    this.deletedAt = deletedAt;
  }

  public static User newUser(
    final String name,
    final String email,
    final LocalDate birthdate,
    final String address,
    final List<String> skills,
    final Boolean isActive    
  ) {
    final var id = UserID.unique();
    final var now = InstantUtils.now();
    final var deletedAt = isActive ? null : now;
    return new User(
      id,
      name,
      email,
      birthdate,
      address,
      skills,
      isActive,
      now,
      now,
      deletedAt
    );
  }

  public static User with(
    final UserID id,
    final String name,
    final String email,
    final LocalDate birthdate,
    final String address,
    final List<String> skills,
    final Boolean isActive,
    final Instant createdAt,
    final Instant updatedAt,
    final Instant deletedAt
  ) {
    return new User(
      id,
      name,
      email,
      birthdate,
      address,
      skills,
      isActive,
      createdAt,
      updatedAt,
      deletedAt
    );
  }

  public static User with(final User anUser
  ) {
    return with(
      anUser.getId(),
      anUser.name, 
      anUser.email,
      anUser.birthdate,
      anUser.address,
      anUser.skills,
      anUser.isActive,
      anUser.createdAt,
      anUser.updatedAt,
      anUser.deletedAt
    );
  }


  @Override
  public void validate(ValidationHandler handler) {
    new UserValidator(this, handler).validate();
  }

  public User activate() {
    this.deletedAt = null;
    this.isActive = true;
    this.updatedAt = InstantUtils.now();
    return this;
  }

  public User deactivate() {
    if (getDeletedAt() == null) {
      this.deletedAt = InstantUtils.now();
    }

    this.isActive = false;
    this.updatedAt = InstantUtils.now();
    return this;
  }

  public User update(
    final String name,
    final String email,
    final LocalDate birthdate,
    final String address,
    final List<String> skills,
    final Boolean isActive
  ) {
    if (isActive) {
      activate();
    } else {
      deactivate();
    }
    this.name = name;
    this.email = email;
    this.address = address;
    this.birthdate = birthdate;
    this.skills = skills;
    this.updatedAt = InstantUtils.now();
    return this;
  }

  public UserID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public LocalDate getBirthdate() {
    return birthdate;
  }

  public String getAddress() {
    return address;
  }

  public List<String> getSkills() {
    return skills;
  }

  public Boolean getActive() {
    return isActive;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public Instant getDeletedAt() {
    return deletedAt;
  }

  @Override
  public User clone() {
    try {
      return (User) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }
}
