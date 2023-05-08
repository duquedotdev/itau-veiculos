package br.com.itau.infrastructure.user.persistence;

import br.com.itau.user.User;
import br.com.itau.user.UserID;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
public class UserJpaEntity implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  private String id;
  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "email", nullable = false)
  private String email;
  
  @Column(name = "birthdate", nullable = false)
  private LocalDate birthdate;
  
  @Column(name = "address", nullable = false)
  private String address;

  @ElementCollection(fetch = FetchType.EAGER)
  @Column(name = "skills", nullable = false)
  private List<String> skills;
  @Column(name="is_active", nullable = false)
  private Boolean isActive;
  
  @Column(name="created_at", nullable = false)
  private Instant createdAt;
  
  @Column(name="updated_at", nullable = false)
  private Instant updatedAt;
  
  @Column(name="deleted_at")
  private Instant deletedAt;
  
  public UserJpaEntity() {}

  private UserJpaEntity(
    final String id, 
    final String name, 
    final String email,
    final LocalDate birthdate,
    final String address,
    final List<String> skills,
    final Boolean isActive, 
    final Instant createdAt, 
    final Instant updatedAt, 
    final Instant deletedAt) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.birthdate = birthdate;
    this.address = address;
    this.skills = skills;
    this.isActive = isActive;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.deletedAt = deletedAt;
  }
  
  public static UserJpaEntity from(final User aUser){
    return new UserJpaEntity(
      aUser.getId().getValue(),
      aUser.getName(),
      aUser.getEmail(),
      aUser.getBirthdate(),
      aUser.getAddress(),
      aUser.getSkills(),
      aUser.getActive(),
      aUser.getCreatedAt(),
      aUser.getUpdatedAt(),
      aUser.getDeletedAt()
    );    
  }
  
  public User toAggregate(){
    return User.with(
      UserID.from(getId()),
      getName(),
      getEmail(),
      getBirthdate(),
      getAddress(),
      getSkills(),
      getActive(),
      getCreatedAt(),
      getUpdatedAt(),
      getDeletedAt()
    );
  }


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDate getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(LocalDate birthdate) {
    this.birthdate = birthdate;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public List<String> getSkills() {
    return skills;
  }

  public void setSkills(List<String> skills) {
    this.skills = skills;
  }

  public Boolean getActive() {
    return isActive;
  }

  public void setActive(Boolean active) {
    isActive = active;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Instant updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Instant getDeletedAt() {
    return deletedAt;
  }

  public void setDeletedAt(Instant deletedAt) {
    this.deletedAt = deletedAt;
  }
}
