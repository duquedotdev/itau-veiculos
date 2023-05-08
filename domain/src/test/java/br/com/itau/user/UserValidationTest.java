package br.com.itau.user;

import br.com.itau.UnitTest;
import br.com.itau.exceptions.DomainException;
import br.com.itau.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class UserValidationTest extends UnitTest {
  
  @Test
  public void givenNullName_WhenCallCreateUser_ThenShouldThrowAnException(){
    final String name = null;
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen, 1";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = true;
    
    final var expectedErrorMessage = "'name' should not be null";
    final var expectedErrorCount = 1;

    final var actualUser = User.newUser(
      name,
      email,
      birthdate,
      address,
      skills,
      isActive
    );

    final var actualException = Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowsValidationHandler()));
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }

  @Test
  public void givenEmptyName_WhenCallCreateUser_ThenShouldThrowAnException(){
    final String name = "   ";
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen 1";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = true;

    final var expectedErrorMessage = "'name' should not be empty";
    final var expectedErrorCount = 1;

    final var actualUser = User.newUser(
      name,
      email,
      birthdate,
      address,
      skills,
      isActive
    );

    final var actualException = Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowsValidationHandler()));
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }

  @Test
  public void givenLessThenThreeCharactersName_WhenCallCreateUser_ThenShouldThrowAnException(){
    final String name = " Na ";
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen, 1";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = true;

    final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
    final var expectedErrorCount = 1;

    final var actualUser = User.newUser(
      name,
      email,
      birthdate,
      address,
      skills,
      isActive
    );

    final var actualException = Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowsValidationHandler()));
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }

  @Test
  public void givenMoreThen255CharactersName_WhenCallCreateUser_ThenShouldThrowAnException(){
    final String name = """
      Gostaria de enfatizar que o consenso sobre a necessidade de qualificação auxilia a preparação e a
      composição das posturas dos órgãos dirigentes com relação às suas atribuições.
      Do mesmo modo, a estrutura atual da organização apresenta tendências no sentido de aprovar a
      manutenção das novas proposições.
      """; // 256 characters
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen, 1";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = true;

    final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
    final var expectedErrorCount = 1;

    final var actualUser = User.newUser(
      name,
      email,
      birthdate,
      address,
      skills,
      isActive
    );

    final var actualException = Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowsValidationHandler()));
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }
  @Test
  public void givenANameWithSymbols_WhenCallCreateUser_ThenShouldThrowAnException(){
    final var name = "Felipe Duque !";
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen, 1";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = true;

    final var expectedErrorMessage = "'name' should not contain symbols";
    final var expectedErrorCount = 1;

    final var actualUser = User.newUser(
      name,
      email,
      birthdate,
      address,
      skills,
      isActive
    );

    final var actualException = Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowsValidationHandler()));
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }
  
  // ================================= EMAIL =================================
  
  @Test
  public void givenNullEmail_WhenCallCreateUser_ThenShouldThrowAnException(){
    final var name = "Felipe Duque";
    final String email = null;
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen 1";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = true;
    
    final var expectedErrorMessage = "'email' should not be null";
    final var expectedErrorCount = 1;

    final var actualUser = User.newUser(
      name,
      email,
      birthdate,
      address,
      skills,
      isActive
    );

    final var actualException = Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowsValidationHandler()));
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }
  
  @Test
  public void givenBlankEmail_WhenCallCreateUser_ThenShouldThrowAnException(){
    final var name = "Felipe Duque";
    final var email = " ";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen 1";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = true;
    
    final var expectedErrorMessage = "'email' should not be empty";
    final var expectedErrorCount = 1;

    final var actualUser = User.newUser(
      name,
      email,
      birthdate,
      address,
      skills,
      isActive
    );

    final var actualException = Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowsValidationHandler()));
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }

  @Test
  public void givenAnyTextAsEmail_WhenCallCreateUser_ThenShouldThrowAnException(){
    final var name = "Felipe Duque";
    final var email = "any text";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen 1";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = true;

    final var expectedErrorMessage = "'email' format should be 'aaa@bbb.com'";
    final var expectedErrorCount = 1;

    final var actualUser = User.newUser(
      name,
      email,
      birthdate,
      address,
      skills,
      isActive
    );

    final var actualException = Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowsValidationHandler()));
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }
  
  @Test
  public void givenAnyOtherProvider_WhenCallCreateUser_ThenShouldThrowAnException(){
    final var name = "Felipe Duque";
    final var email = "felipe@duque.dev";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen 1";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = true;

    final var expectedErrorMessage = "'email' should be from gmail, hotmail, outlook or yahoo";
    final var expectedErrorCount = 1;

    final var actualUser = User.newUser(
      name,
      email,
      birthdate,
      address,
      skills,
      isActive
    );

    final var actualException = Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowsValidationHandler()));
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }
  
   // =============================== BIRTHDATE =====================================
  
  @Test
  public void givenNullBirthdate_WhenCallCreateUser_ThenShouldThrowAnException(){
    final var name = "Felipe Duque";
    final var email = "felipe@gmail.com";
    final LocalDate birthdate = null;
    final var address = "Rua Volkswagen, 1";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = true;

    final var expectedErrorMessage = "'birthdate' should not be null";
    final var expectedErrorCount = 1;

    final var actualUser = User.newUser(
      name,
      email,
      birthdate,
      address,
      skills,
      isActive
    );

    final var actualException = Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowsValidationHandler()));
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }
   
  @Test
  public void givenLessThen18YearsOldBirthdate_WhenCallCreateUser_ThenShouldThrowAnException(){
    final var name = "Felipe Duque";
    final var email = "felipe@gmail.com";
    final LocalDate birthdate = LocalDate.of(2020, 12,30);
    final var address = "Rua Volkswagen, 1";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = true;

    final var expectedErrorMessage = "'birthdate' should be hire then 18 years";
    final var expectedErrorCount = 1;

    final var actualUser = User.newUser(
      name,
      email,
      birthdate,
      address,
      skills,
      isActive
    );

    final var actualException = Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowsValidationHandler()));
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }
  
  // =============================== ADDRESS =====================================
  
  @Test
  public void givenNullAddress_WhenCallCreateUser_ThenShouldThrowAnException(){
    final var name = "Felipe Duque";
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final String address = null;
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = true;

    final var expectedErrorMessage = "'address' should not be null";
    final var expectedErrorCount = 1;

    final var actualUser = User.newUser(
      name,
      email,
      birthdate,
      address,
      skills,
      isActive
    );

    final var actualException = Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowsValidationHandler()));
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }
  
  @Test
  public void givenBlankAddress_WhenCallCreateUser_ThenShouldThrowAnException(){
    final var name = "Felipe Duque";
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final String address = "   ";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = true;

    final var expectedErrorMessage = "'address' should not be empty";
    final var expectedErrorCount = 1;

    final var actualUser = User.newUser(
      name,
      email,
      birthdate,
      address,
      skills,
      isActive
    );

    final var actualException = Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowsValidationHandler()));
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }

  @Test
  public void givenLessThenThreeCharactersAddress_WhenCallCreateUser_ThenShouldThrowAnException(){
    final var name = "Felipe Duque";
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final String address = "  Av  ";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = true;

    final var expectedErrorMessage = "'address' must be between 3 and 255 characters";
    final var expectedErrorCount = 1;

    final var actualUser = User.newUser(
      name,
      email,
      birthdate,
      address,
      skills,
      isActive
    );

    final var actualException = Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowsValidationHandler()));
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }

  @Test
  public void givenMoreThen255CharactersAddress_WhenCallCreateUser_ThenShouldThrowAnException(){
    final var name = "Felipe Duque";
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final String address = """
      Gostaria de enfatizar que o consenso sobre a necessidade de qualificação auxilia a preparação e a
      composição das posturas dos órgãos dirigentes com relação às suas atribuições.
      Do mesmo modo, a estrutura atual da organização apresenta tendências no sentido de aprovar a
      manutenção das novas proposições.
      """; // 256 characters;
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = true;

    final var expectedErrorMessage = "'address' must be between 3 and 255 characters";
    final var expectedErrorCount = 1;

    final var actualUser = User.newUser(
      name,
      email,
      birthdate,
      address,
      skills,
      isActive
    );

    final var actualException = Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowsValidationHandler()));
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }

  @Test
  public void givenAddressWithSymbols_WhenCallCreateUser_ThenShouldThrowAnException(){
    final var name = "Felipe Duque";
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Address with symbols # $ % & * ( )";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = true;

    final var expectedErrorMessage = "'address' should not contain symbols";
    final var expectedErrorCount = 1;

    final var actualUser = User.newUser(
      name,
      email,
      birthdate,
      address,
      skills,
      isActive
    );

    final var actualException = Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowsValidationHandler()));
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }
  
  // ===============================  SKILLS =====================================
  
  @Test
  public void givenNullSkills_WhenCallCreateUser_ThenShouldThrowAnException(){
    final String name = "Felipe Duque";
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen 1";
    final List<String> skills = null;
    final var isActive = true;

    final var expectedErrorMessage = "'skills' should not be null";
    final var expectedErrorCount = 1;

    final var actualUser = User.newUser(
      name,
      email,
      birthdate,
      address,
      skills,
      isActive
    );

    final var actualException = Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowsValidationHandler()));
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }

  @Test
  public void givenBlankSkills_WhenCallCreateUser_ThenShouldThrowAnException(){
    final String name = "Felipe Duque";
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen 1";
    final List<String> skills = List.of("  ");
    final var isActive = true;

    final var expectedErrorMessage = "'skills' should not be empty";
    final var expectedErrorCount = 1;

    final var actualUser = User.newUser(
      name,
      email,
      birthdate,
      address,
      skills,
      isActive
    );

    final var actualException = Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowsValidationHandler()));
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }
  
  @Test
  public void givenLessThenThreeCharactersSkills_WhenCallCreateUser_ThenShouldThrowAnException(){
    final String name = "Felipe Duque";
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen 1";
    final List<String> skills = List.of("a", "b");
    final var isActive = true;

    final var expectedErrorMessage = "'skills' must be between 3 and 255 characters";
    final var expectedErrorCount = 1;

    final var actualUser = User.newUser(
      name,
      email,
      birthdate,
      address,
      skills,
      isActive
    );

    final var actualException = Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowsValidationHandler()));
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }
  
  @Test
  public void givenMoreThen255CharactersSkills_WhenCallCreateUser_ThenShouldThrowAnException(){
    final String name = "Felipe Duque";
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen 1";
    final List<String> skills = List.of("""
      Gostaria de enfatizar que o consenso sobre a necessidade de qualificação auxilia a preparação e a
      composição das posturas dos órgãos dirigentes com relação às suas atribuições.
      Do mesmo modo, a estrutura atual da organização apresenta tendências no sentido de aprovar a
      manutenção das novas proposições.
      """, "b");
    final var isActive = true;

    final var expectedErrorMessage = "'skills' must be between 3 and 255 characters";
    final var expectedErrorCount = 1;

    final var actualUser = User.newUser(
      name,
      email,
      birthdate,
      address,
      skills,
      isActive
    );

    final var actualException = Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowsValidationHandler()));
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  }
  
  @Test
  public void givenSkillsWithSymbols_WhenCallCreateUser_ThenShouldThrowAnException(){
    final String name = "Felipe Duque";
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen 1";
    final List<String> skills = List.of("a", "!@#$%¨&*()_+");
    final var isActive = true;

    final var expectedErrorMessage = "'skills' must be between 3 and 255 characters";
    final var expectedErrorCount = 1;

    final var actualUser = User.newUser(
      name,
      email,
      birthdate,
      address,
      skills,
      isActive
    );

    final var actualException = Assertions.assertThrows(DomainException.class, () -> actualUser.validate(new ThrowsValidationHandler()));
    Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
  } 
  
}
