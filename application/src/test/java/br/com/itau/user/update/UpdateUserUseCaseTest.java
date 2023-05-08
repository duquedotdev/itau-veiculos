package br.com.itau.user.update;

import br.com.itau.UseCaseTest;
import br.com.itau.application.user.update.DefaultUpdateUserUseCase;
import br.com.itau.application.user.update.UpdateUserCommand;
import br.com.itau.exceptions.NotFoundException;
import br.com.itau.user.User;
import br.com.itau.user.UserGateway;
import br.com.itau.user.UserID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class UpdateUserUseCaseTest extends UseCaseTest {

  @InjectMocks
  private DefaultUpdateUserUseCase useCase;

  @Mock
  private UserGateway gateway;

  @Override
  protected List<Object> getMocks() {
    return List.of(gateway);
  }

  @Test
  public void givenAValidCommand_whenCallsUpdateUser_shouldReturnUserId(){
    final var aUser = User.newUser("A User", "aemail@gmail.com", LocalDate.of(1993, 7, 8), "Rua Volkswagen", List.of("Spring Boot", "Spring Framework"), true);

    final var id = aUser.getId();
    final var name = "Felipe Duque";
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = true;

    final var aCommand = UpdateUserCommand.with(id.getValue(), name, email, birthdate, address, skills, isActive);

    when(gateway.findById(eq(id)))
      .thenReturn(Optional.of(User.with(aUser)));

    when(gateway.update(any()))
      .thenAnswer(returnsFirstArg());

    final var actualOutput = useCase.execute(aCommand).get();

    Assertions.assertNotNull(actualOutput);
    Assertions.assertNotNull(actualOutput.id());

    Mockito.verify(gateway, times(1)).findById(eq(id));

    Mockito.verify(gateway, Mockito.times(1)).update(argThat(anUpdatedUser ->
      Objects.equals(name, anUpdatedUser.getName())
        && Objects.equals(email, anUpdatedUser.getEmail())
        && Objects.equals(birthdate, anUpdatedUser.getBirthdate())
        && Objects.equals(address, anUpdatedUser.getAddress())
        && Objects.equals(skills, anUpdatedUser.getSkills())
        && Objects.equals(isActive, anUpdatedUser.getActive())
        && Objects.nonNull(anUpdatedUser.getId())
        && Objects.nonNull(anUpdatedUser.getCreatedAt())
        && aUser.getUpdatedAt().isBefore(anUpdatedUser.getUpdatedAt())
        && Objects.isNull(anUpdatedUser.getDeletedAt())
    ));

  }

  @Test
  public void givenAInvalidName_whenCallsUpdateUser_thenShouldReturnDomainException(){
    final var aUser = User.newUser("A User", "aemail@gmail.com", LocalDate.of(1993, 7, 8), "Rua Volkswagen", List.of("Spring Boot", "Spring Framework"), true);

    final var id = aUser.getId();
    final String name = null;
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = true;

    final var aCommand = UpdateUserCommand.with(id.getValue(), name, email, birthdate, address, skills, isActive);

    final var expectedErrorMessage = "'name' should not be null";
    final var expectedErrorCount = 1;

    when(gateway.findById(eq(id)))
      .thenReturn(Optional.of(User.with(aUser)));

    final var notification = useCase.execute(aCommand).getLeft();

    Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
    Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

    Mockito.verify(gateway, times(0)).update(any());
  }

  @Test
  public void givenAValidInactivateCommand_whenCallsUpdateUser_shouldReturnInactiveUserId(){
    final var aUser = User.newUser("A User", "aemail@gmail.com", LocalDate.of(1993, 7, 8), "Rua Volkswagen", List.of("Spring Boot", "Spring Framework"), true);

    final var id = aUser.getId();
    final var name = "Felipe Duque";
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = false;

    final var aCommand = UpdateUserCommand.with(id.getValue(), name, email, birthdate, address, skills, isActive);

    when(gateway.findById(eq(id)))
      .thenReturn(Optional.of(User.with(aUser)));

    when(gateway.update(any()))
      .thenAnswer(returnsFirstArg());

    final var actualOutput = useCase.execute(aCommand).get();

    Assertions.assertNotNull(actualOutput);
    Assertions.assertNotNull(actualOutput.id());

    Mockito.verify(gateway, times(1)).findById(eq(id));

    Mockito.verify(gateway, Mockito.times(1)).update(argThat(anUpdatedUser ->
      Objects.equals(name, anUpdatedUser.getName())
        && Objects.equals(email, anUpdatedUser.getEmail())
        && Objects.equals(birthdate, anUpdatedUser.getBirthdate())
        && Objects.equals(address, anUpdatedUser.getAddress())
        && Objects.equals(skills, anUpdatedUser.getSkills())
        && Objects.equals(isActive, anUpdatedUser.getActive())
        && Objects.nonNull(anUpdatedUser.getId())
        && Objects.nonNull(anUpdatedUser.getCreatedAt())
        && aUser.getUpdatedAt().isBefore(anUpdatedUser.getUpdatedAt())
        && Objects.nonNull(anUpdatedUser.getDeletedAt())
    ));
  }

  @Test
  public void givenAValidCommand_whenGatewayThrowsRandomException_shouldReturnAException(){
    final var aUser = User.newUser("A User", "aemail@gmail.com", LocalDate.of(1993, 7, 8), "Rua Volkswagen", List.of("Spring Boot", "Spring Framework"), true);

    final var id = aUser.getId();
    final var name = "Felipe Duque";
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = true;

    final var expectedErrorCount = 1;
    final var expectedErrorMessage = "Gateway error";

    final var aCommand = UpdateUserCommand.with(id.getValue(), name, email, birthdate, address, skills, isActive);

    when(gateway.findById(eq(id)))
      .thenReturn(Optional.of(User.with(aUser)));

    when(gateway.update(any()))
      .thenThrow(new IllegalStateException(expectedErrorMessage));

    final var notification = useCase.execute(aCommand).getLeft();;

    Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());

    Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

    Mockito.verify(gateway, times(1)).findById(eq(id));

    Mockito.verify(gateway, Mockito.times(1)).update(argThat(anUpdatedUser ->
      Objects.equals(name, anUpdatedUser.getName())
        && Objects.equals(email, anUpdatedUser.getEmail())
        && Objects.equals(birthdate, anUpdatedUser.getBirthdate())
        && Objects.equals(address, anUpdatedUser.getAddress())
        && Objects.equals(skills, anUpdatedUser.getSkills())
        && Objects.equals(isActive, anUpdatedUser.getActive())
        && Objects.nonNull(anUpdatedUser.getId())
        && Objects.nonNull(anUpdatedUser.getCreatedAt())
        && aUser.getUpdatedAt().isBefore(anUpdatedUser.getUpdatedAt())
        && Objects.isNull(anUpdatedUser.getDeletedAt())
    ));    
  }

  @Test
  public void givenACommandWithInvalidUserID_whenCallsUpdateUser_shouldReturnNotFoundException(){
    final var aUser = User.newUser("A User", "aemail@gmail.com", LocalDate.of(1993, 7, 8), "Rua Volkswagen", List.of("Spring Boot", "Spring Framework"), true);


    final var id = aUser.getId();
    final var name = "Felipe Duque";
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = true;

    final var expectedId = "123";
    final var expectedErrorMessage = "User with ID 123 was not found";

    final var aCommand = UpdateUserCommand.with("123", name, email, birthdate, address, skills, isActive);

    when(gateway.findById(eq(UserID.from(expectedId))))
      .thenReturn(Optional.empty());

    final var actualException =
      Assertions.assertThrows(NotFoundException.class, () -> useCase.execute(aCommand));

    Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());

    Mockito.verify(gateway, times(1)).findById(eq(UserID.from(expectedId)));

    Mockito.verify(gateway, times(0)).update(any());
  }



}
