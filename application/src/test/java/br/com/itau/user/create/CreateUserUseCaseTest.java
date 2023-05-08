package br.com.itau.user.create;

import br.com.itau.UseCaseTest;
import br.com.itau.application.user.create.CreateUserCommand;
import br.com.itau.application.user.create.DefaultCreateUserUseCase;
import br.com.itau.user.UserGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class CreateUserUseCaseTest extends UseCaseTest {
  @InjectMocks
  private DefaultCreateUserUseCase useCase;
  @Mock
  private UserGateway gateway;
  @Override
  protected List<Object> getMocks() {
    return List.of(gateway);
  }

  @Test
  public void givenAValidCommand_whenCallsCreateUser_shouldReturnUserId(){
    final var name = "Felipe Duque";
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = true;
    
    final var aCommand = CreateUserCommand.with(name, email, birthdate, address, skills, isActive);

    when(gateway.create(any()))
      .thenAnswer(returnsFirstArg());

    final var actualOutput = useCase.execute(aCommand).get();

    Assertions.assertNotNull(actualOutput);
    Assertions.assertNotNull(actualOutput.id());

    Mockito.verify(gateway, times(1)).create(argThat(aUser -> 
      Objects.equals(name, aUser.getName())
      && Objects.equals(email, aUser.getEmail())
      && Objects.equals(birthdate, aUser.getBirthdate())
      && Objects.equals(address, aUser.getAddress())
      && Objects.equals(skills, aUser.getSkills())
      && Objects.equals(isActive, aUser.getActive())
      && Objects.nonNull(aUser.getId())
      && Objects.nonNull(aUser.getCreatedAt())
      && Objects.nonNull(aUser.getUpdatedAt())
      && Objects.isNull(aUser.getDeletedAt())
    ));
  };

  @Test
  public void givenAInvalidName_whenCallsCreateUser_thenShouldReturnDomainException(){
    final String name = null;
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = true;

    final var expectedErrorMessage = "'name' should not be null";
    final var expectedErrorCount = 1;

    final var aCommand = CreateUserCommand.with(name, email, birthdate, address, skills, isActive);

    final var notification = useCase.execute(aCommand).getLeft();

    Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

    Mockito.verify(gateway, times(0)).create(any());
  }
  
  @Test
  public void givenAValidCommand_whenCallsCreateAInactiveUser_shouldReturnUserId() {
    final var name = "Felipe Duque";
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = false;

    final var aCommand = CreateUserCommand.with(name, email, birthdate, address, skills, isActive);

    when(gateway.create(any()))
      .thenAnswer(returnsFirstArg());

    final var actualOutput = useCase.execute(aCommand).get();

    Assertions.assertNotNull(actualOutput);
    Assertions.assertNotNull(actualOutput.id());

    Mockito.verify(gateway, times(1)).create(argThat(aUser ->
      Objects.equals(name, aUser.getName())
        && Objects.equals(email, aUser.getEmail())
        && Objects.equals(birthdate, aUser.getBirthdate())
        && Objects.equals(address, aUser.getAddress())
        && Objects.equals(skills, aUser.getSkills())
        && Objects.equals(isActive, aUser.getActive())
        && Objects.nonNull(aUser.getId())
        && Objects.nonNull(aUser.getCreatedAt())
        && Objects.nonNull(aUser.getUpdatedAt())
        && Objects.nonNull(aUser.getDeletedAt())
    ));   
  }

  @Test
  public void givenAValidCommand_whenGatewayThrowsRandomException_shouldReturnAException(){
    final var name = "Felipe Duque";
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = true;;

    final var expectedErrorCount = 1;
    final var expectedErrorMessage = "Gateway error";

    final var aCommand = CreateUserCommand.with(name, email, birthdate, address, skills, isActive);

    when(gateway.create(any()))
      .thenThrow(new IllegalStateException(expectedErrorMessage));

    final var notification = useCase.execute(aCommand).getLeft();

    // Then

    Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

    Mockito.verify(gateway, times(1)).create(argThat(aUser ->
      Objects.equals(name, aUser.getName())
        && Objects.equals(email, aUser.getEmail())
        && Objects.equals(birthdate, aUser.getBirthdate())
        && Objects.equals(address, aUser.getAddress())
        && Objects.equals(skills, aUser.getSkills())
        && Objects.equals(isActive, aUser.getActive())
        && Objects.nonNull(aUser.getId())
        && Objects.nonNull(aUser.getCreatedAt())
        && Objects.nonNull(aUser.getUpdatedAt())
        && Objects.isNull(aUser.getDeletedAt())
    ));
  }
  
  
  
}
