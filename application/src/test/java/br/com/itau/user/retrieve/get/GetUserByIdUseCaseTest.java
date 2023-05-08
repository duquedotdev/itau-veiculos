package br.com.itau.user.retrieve.get;

import br.com.itau.UseCaseTest;
import br.com.itau.application.user.retrieve.get.DefaultGetUserByIdUseCase;
import br.com.itau.exceptions.NotFoundException;
import br.com.itau.user.User;
import br.com.itau.user.UserGateway;
import br.com.itau.user.UserID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class GetUserByIdUseCaseTest extends UseCaseTest {

  @InjectMocks
  private DefaultGetUserByIdUseCase useCase;

  @Mock
  private UserGateway gateway;

  @Override
  protected List<Object> getMocks() {
    return List.of(gateway);
  }

  @Test
  public void givenAValidUserId_whenCallsGetUser_shouldReturnUser() {
    final var name = "Felipe Duque";
    final var email = "felipe@gmail.com";
    final var birthdate = LocalDate.of(1993, 7, 8);
    final var address = "Rua Volkswagen";
    final var skills = List.of("Spring Boot", "Spring Framework");
    final var isActive = true;

    final var aUser = User.newUser(name, email, birthdate, address, skills, isActive);

    final var expectedId = aUser.getId();

    // when
    when(gateway.findById(eq(expectedId))).thenReturn(Optional.of(aUser.clone()));

    // then
    final var actualUser = useCase.execute(expectedId.getValue());

    Assertions.assertNotNull(actualUser);
    Assertions.assertEquals(expectedId, actualUser.id());
    Assertions.assertEquals(name, actualUser.name());
    Assertions.assertEquals(email, actualUser.email());
    Assertions.assertEquals(birthdate, actualUser.birthdate());
    Assertions.assertEquals(address, actualUser.address());
    Assertions.assertEquals(skills, actualUser.skills());
    Assertions.assertEquals(isActive, actualUser.isActive());
    Assertions.assertEquals(aUser.getCreatedAt(), actualUser.createdAt());
    Assertions.assertEquals(aUser.getUpdatedAt(), actualUser.updatedAt());
    Assertions.assertEquals(aUser.getDeletedAt(), actualUser.deletedAt());    
  }

  @Test
  public void givenAInvalidUserId_whenCallsGetUser_shouldReturnNotFound() {
    final var expectedErrorMessage = "User with ID 123 was not found";
    final var expectedId = UserID.from("123");

    when(gateway.findById(eq(expectedId)))
      .thenReturn(Optional.empty());

    final var actualException = Assertions.assertThrows(
      NotFoundException.class,
      () -> useCase.execute(expectedId.getValue())
    );

    Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
  }

  @Test
  public void givenAValidUserId_whenGatewayThrowsException_shouldReturnException() {
    final var expectedErrorMessage = "Gateway error";
    final var expectedId = UserID.from("123");

    when(gateway.findById(eq(expectedId)))
      .thenThrow(new IllegalStateException(expectedErrorMessage));

    final var actualException = Assertions.assertThrows(
      IllegalStateException.class,
      () -> useCase.execute(expectedId.getValue())
    );

    Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
  }
}
