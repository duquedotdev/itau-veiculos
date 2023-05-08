package br.com.itau.user;

import br.com.itau.UnitTest;
import br.com.itau.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class UserTest extends UnitTest {
  
  @Test
  public void givenAValidUser_WhenCallNewUser_ThenShouldInstantiateANewUser(){
    
    final var name = "Felipe Duque";
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = true;
    
    final var actualUser = User.newUser(
      name,
      email,
      birthdate,
      address,
      skills,
      isActive
    );

    Assertions.assertDoesNotThrow(() -> actualUser.validate(new ThrowsValidationHandler()));

    Assertions.assertNotNull(actualUser);
    Assertions.assertNotNull(actualUser.getId());
    Assertions.assertEquals(name, actualUser.getName());
    Assertions.assertEquals(email, actualUser.getEmail());
    Assertions.assertEquals(birthdate, actualUser.getBirthdate());
    Assertions.assertEquals(address, actualUser.getAddress());
    Assertions.assertEquals(skills, actualUser.getSkills());
    Assertions.assertTrue(actualUser.getActive());
    Assertions.assertNotNull(actualUser.getCreatedAt());
    Assertions.assertNotNull(actualUser.getUpdatedAt());
    Assertions.assertNull(actualUser.getDeletedAt());   
    
  }

  @Test
  public void givenAValidUserAndInactive_WhenCallNewUser_ThenShouldInstantiateTheNewUser(){

    final var name = "Felipe Duque";
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = false;

    final var actualUser = User.newUser(
      name,
      email,
      birthdate,
      address,
      skills,
      isActive
    );

    Assertions.assertDoesNotThrow(() -> actualUser.validate(new ThrowsValidationHandler()));

    Assertions.assertNotNull(actualUser);
    Assertions.assertNotNull(actualUser.getId());
    Assertions.assertEquals(name, actualUser.getName());
    Assertions.assertEquals(email, actualUser.getEmail());
    Assertions.assertEquals(birthdate, actualUser.getBirthdate());
    Assertions.assertEquals(address, actualUser.getAddress());
    Assertions.assertEquals(skills, actualUser.getSkills());
    Assertions.assertFalse(actualUser.getActive());
    Assertions.assertNotNull(actualUser.getCreatedAt());
    Assertions.assertNotNull(actualUser.getUpdatedAt());
    Assertions.assertNotNull(actualUser.getDeletedAt());

  }

  @Test
  public void givenAValidUser_WhenCallDeactivate_ThenShouldDeactivateTheUser(){

    final var name = "Felipe Duque";
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = true;

    final var actualUser = User.newUser(
      name,
      email,
      birthdate,
      address,
      skills,
      isActive
    );

    Assertions.assertDoesNotThrow(() -> actualUser.validate(new ThrowsValidationHandler()));

    Assertions.assertNotNull(actualUser);
    Assertions.assertNull(actualUser.getDeletedAt());

    //When
    actualUser.deactivate();
    
    //Then
    Assertions.assertFalse(actualUser.getActive());
    Assertions.assertNotNull(actualUser.getDeletedAt());
    
  }
  
  @Test
  public void givenAValidAndInactivatedUser_WhenCallActive_ThenShouldReactiveTheUser(){

    final var name = "Felipe Duque";
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = false;

    final var actualUser = User.newUser(
      name,
      email,
      birthdate,
      address,
      skills,
      isActive
    );

    Assertions.assertDoesNotThrow(() -> actualUser.validate(new ThrowsValidationHandler()));

    Assertions.assertNotNull(actualUser);
    Assertions.assertNotNull(actualUser.getDeletedAt());
    
    //When
    actualUser.activate();
    
    //Then
    Assertions.assertTrue(actualUser.getActive());
    Assertions.assertNull(actualUser.getDeletedAt());
  }

  @Test
  public void givenAValidAndInactivatedUser_WhenCallDeactive_ThenShouldKeepAsAnInactiveUser(){

    final var name = "Felipe Duque";
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = false;

    final var actualUser = User.newUser(
      name,
      email,
      birthdate,
      address,
      skills,
      isActive
    );

    Assertions.assertDoesNotThrow(() -> actualUser.validate(new ThrowsValidationHandler()));

    Assertions.assertNotNull(actualUser);
    Assertions.assertNotNull(actualUser.getDeletedAt());

    //When
    actualUser.deactivate();

    //Then
    Assertions.assertFalse(actualUser.getActive());
    Assertions.assertNotNull(actualUser.getDeletedAt());
  }
  
  @Test
  public void givenAValidUser_WhenCallUpdateUser_ThenShouldUpdateTheUser(){
    
    final var someUser = User.newUser(
      "Random", 
      "random@gmail.com", 
      LocalDate.of(1993, 7, 8),
      "Some Adress",
      List.of("Spring Boot", "Spring Framework"),
      true
    );

    Assertions.assertDoesNotThrow(() -> someUser.validate(new ThrowsValidationHandler()));
    Assertions.assertNotNull(someUser);
    Assertions.assertNull(someUser.getDeletedAt());

    final var name = "Felipe Duque";
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = true;

    final var actualUser = someUser.update(
      name,
      email,
      birthdate,
      address,
      skills,
      isActive
    );

    Assertions.assertDoesNotThrow(() -> actualUser.validate(new ThrowsValidationHandler()));

    Assertions.assertNotNull(actualUser);
    Assertions.assertNotNull(actualUser.getId());
    Assertions.assertEquals(name, actualUser.getName());
    Assertions.assertEquals(email, actualUser.getEmail());
    Assertions.assertEquals(birthdate, actualUser.getBirthdate());
    Assertions.assertEquals(address, actualUser.getAddress());
    Assertions.assertEquals(skills, actualUser.getSkills());
    Assertions.assertTrue(actualUser.getActive());
    Assertions.assertNotNull(actualUser.getCreatedAt());
    Assertions.assertNotNull(actualUser.getUpdatedAt());
    Assertions.assertNull(actualUser.getDeletedAt());

  }

}
